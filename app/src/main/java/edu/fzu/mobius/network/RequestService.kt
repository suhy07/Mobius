package edu.fzu.mobius.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RequestService {

    @POST(value = "user/login")
    fun logInByPassword(
       @Body loginPasswordForm: LoginPasswordForm
    ): Call<LogInBackData>

    @POST(value = "/user/phoneIn")
    fun logInByCode(
        @Body loginCodeForm: LoginCodeForm
    ): Call<LogInBackData>

    @POST(value = "/code/login")
    fun loginSendVerificationCode(
        @Body verificationCodeForm: VerificationCodeForm
    ): Call<LogInBackData>

    @POST(value = "/code/reg")
    fun registerSendVerificationCode(
        @Body verificationCodeForm: VerificationCodeForm
    ): Call<LogInBackData>

    @POST(value = "/user/register")
    fun register(
        @Body registerForm: RegisterForm
    ): Call<LogInBackData>
}

class Network {
    companion object {
        //创建拦截器
        private val interceptor = Interceptor { chain ->
            val request = chain.request()
            val requestBuilder = request.newBuilder()
            val url = request.url()
            val builder = url.newBuilder()
            requestBuilder.url(builder.build())
                .method(request.method(), request.body())
                .addHeader("Content-Type", "application/json")
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

    }
}

data class LogInBackData(
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