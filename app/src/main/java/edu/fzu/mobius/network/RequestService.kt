package edu.fzu.mobius.network

import ToastMsg
import edu.fzu.mobius.entity.TestData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit
import kotlin.reflect.KFunction1

interface RequestService {

    @POST(value = "/user/login")
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
    ): Call<LogInBackDataString>

    @POST(value = "/code/reg")
    fun registerSendVerificationCode(
        @Body verificationCodeForm: Any
    ): Call<LogInBackDataString>

    @GET(value = "/user/info")
    fun getUserInfo(
        @Query ("") empty: Any
    ):Call<LogInBackData>

    @POST(value = "/user/register")
    fun register(
        @Body registerForm: Any
    ): Call<LogInBackData>

    @POST(value = "/user/setName")
    fun setNickname(
        @Body setNicknameForm: Any
    ): Call<LogInBackDataString>

    @GET(value = "ums/friend/list/")
    fun setFriendlist(
        @Query("nickname") nickname: Any,
    ): Call<LogInBackData>

    @GET(value = "ums/friend/apply")
    fun applyFriend(
        @Body applyfriendForm: Any
    ): Call<LogInBackData>

    @POST(value = "lms/capsule")
    fun sendCapsule(
        @Body sendCapsuleForm: Any
    ): Call<LogInBackData>

}

class Network {
    companion object {
        @JvmStatic
        var token = ""

        var friendlist = mutableListOf<TestData.Data.Project>()
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

        fun networkThreadString(
            requestService: (Any)->Call<LogInBackDataString>,
            body: Any,
            code200: (LogInBackDataString)->Unit = {
                PopWindows.postValue(
                    ToastMsg(
                        value = it.code.toString() + " " + it.message,
                        type = ToastType.SUCCESS
                    )
                )
            },
            router: (LogInBackDataString)->Unit = {},
            codeElse: (LogInBackDataString)->Unit = {
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
            other: (LogInBackDataString)->Unit = {}
        ){
            Thread{
                requestService(body).enqueue(object : Callback<LogInBackDataString> {
                    override fun onResponse(
                        call: Call<LogInBackDataString>,
                        response: Response<LogInBackDataString>
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
                    override fun onFailure(call: Call<LogInBackDataString>, t: Throwable) {
                        fail(t)
                    }
                })
            }.start()
        }

        fun networkThreadget(
            requestService: (Any)->Call<LogInBackData>,
            param: Any,
            code200:(LogInBackData)->Unit = {},
            codeElse:(LogInBackData)->Unit = {},
            fail: (Throwable)->Unit = {},
            other: (LogInBackData)->Unit = {}
        ){
            Thread{
                requestService(param as String).enqueue(object : Callback<LogInBackData> {
                    override fun onResponse(
                        call: Call<LogInBackData>,
                        response: Response<LogInBackData>
                    ) {
                        response.body()?.let { it ->
                            other(it)
                            when (it.code) {
                                200 -> {
                                    code200(it)
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
data class SendCapsuleForm(
    val arriveTime:String,
    val content:String,
    val contentId:Int,
    val receiverId:Int,
    val title:String
)

data class ApplyFriendFrom(
    val applyUserId: Int,
)
data class LogInBackData(
    val message: String,
    val code: Int,
    val data: Map<String, Any>
)

data class LogInBackDataString(
    val message: String,
    val code: Int,
    val data: String
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
)