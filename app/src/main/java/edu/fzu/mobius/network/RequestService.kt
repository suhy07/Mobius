package edu.fzu.mobius.network

import ToastMsg
import android.widget.Space
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RequestService {

    @POST(value = "user/login")
    fun logInByPassword(
       @Body loginPasswordForm: Any
    ): Call<LogInBackData>

    @POST(value = "/user/phoneIn")
    fun logInByCode(
        @Body loginCodeForm: Any
    ): Call<LogInBackData>

    @POST(value = "/code/login")
    fun loginSendVerificationCode(
        @Body verificationCodeForm: Any
    ): Call<LogInBackData>

    @POST(value = "/code/reg")
    fun registerSendVerificationCode(
        @Body verificationCodeForm: Any
    ): Call<LogInBackData>

    @POST(value = "/user/register")
    fun register(
        @Body registerForm: Any
    ): Call<LogInBackData>

    @POST(value = "user/setName")
    fun setNickname(
        @Body setNicknameForm: Any
    ): Call<LogInBackData>
}

class Network {
    companion object {
        @JvmStatic
        var token = ""
        //创建拦截器
        private val interceptor = Interceptor { chain ->
            val request = chain.request()
            val requestBuilder = request.newBuilder()
            val url = request.url()
            val builder = url.newBuilder()
            requestBuilder.url(builder.build())
                .method(request.method(), request.body())
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer $token")
            chain.proceed(requestBuilder.build())
        }

        //创建OKhttp
        private val client: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)


        private var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://101.132.99.44:8998")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()

        var service: RequestService = retrofit.create(RequestService::class.java)

        fun networkThread(
            requestService: (Any)->Call<LogInBackData>,
            body: Any,
            code200: (LogInBackData)->Unit = {
                PopWindows.postValue(
                    ToastMsg(
                        value = it.code.toString() + " " + it.message,
                        type = ToastType.SUCCESS
                    )
                )
            },
            router: (LogInBackData)->Unit = {},
            codeElse: (LogInBackData)->Unit = {
                PopWindows.postValue(
                    ToastMsg(
                        value = it.code.toString()+" "+it.message,
                        type = ToastType.ERROR
                    )
                )
            },
            fail: (Throwable)->Unit = {
                PopWindows.postValue(
                    ToastMsg(
                        value = it.localizedMessage,
                        type = ToastType.ERROR
                    )
                )
            },
            other: (LogInBackData)->Unit = {}
        ){
            Thread{
                requestService(body).enqueue(object : Callback<LogInBackData> {
                    override fun onResponse(
                        call: Call<LogInBackData>,
                        response: Response<LogInBackData>
                    ) {
                        response.body()?.let { it ->
                            other(it)
                            when (it.code) {
                                200 -> {
                                    code200(it)
                                    router(it)
                                }
                                else -> {
                                    codeElse(it)
                                }
                            }
                        }
                    }
                    override fun onFailure(call: Call<LogInBackData>, t: Throwable) {
                        fail(t)
                    }
                })
            }.start()
        }
    }
}

data class LogInBackData(
    val message: String,
    val code: Int,
    val data: Map<String, Any>
)

data class LogInBackDataString(
    val message: String,
    val code: Int,
    val data: Map<String, Any>
)

data class LoginPasswordForm(
    val phone: String,
    val password: String
)

data class LoginCodeForm(
    val phone: String,
    val code: String
)

data class VerificationCodeForm(
    val phone: String
)

data class RegisterForm(
    val phone: String,
    val code: String,
    val password: String
)

data class SetNicknameForm(
    val nickName: String,
    val space: String = ""
)