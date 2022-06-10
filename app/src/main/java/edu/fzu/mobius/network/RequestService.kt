package edu.fzu.mobius.network

import ToastMsg
import edu.fzu.mobius.entity.TestData
import edu.fzu.mobius.global.GlobalMem
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

    @GET(value = "/anon/{anonId}")
    fun getAnonValue(
        @Query ("anonId") anonId: Any
    ):Call<LogInBackData>

    @GET(value = "/anon/random/"+GlobalMem.ANON_NUM)
    fun randomAnonList(
        @Query ("") empty: Any
    ):Call<LogInBackDataArray>

    @GET(value = "/anon/getCurrentPossessAnons")
    fun getAnonList(
        @Query ("") empty: Any
    ):Call<LogInBackDataArray>

    @POST(value = "/anon/save")
    fun postAnonLetter(
        @Body postAnonForm: Any
    ): Call<LogInBackData>

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

    @POST(value = "/code/rePassword")
    fun changePasswordSendVerificationCode(
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

    @POST(value = "/user/rePassword")
    fun change(
        @Body changePasswordForm: Any
    ): Call<LogInBackDataString>

    @POST(value = "/user/register")
    fun register(
        @Body registerForm: Any
    ): Call<LogInBackData>

    @POST(value = "/user/setName")
    fun setNickname(
        @Body setNicknameForm: Any
    ): Call<LogInBackDataString>

    @GET(value = "/ums/friend/list/")
    fun setFriendlist(
        @Query("nickname") nickname: Any,
    ): Call<LogInBackData>

    @GET(value = "/ums/friend/apply")
    fun applyFriend(
        @Body applyFriendForm: Any
    ): Call<LogInBackData>

    @POST(value = "/capsule")
    fun sendCapsule(
        @Body sendCapsuleForm: Any
    ): Call<LogInBackDataString>

    @GET(value = "/draft/list")
    fun getDraftList(
        @Query ("pageNum") pageNum: Int,
//        @Query ("pageSize") pageSize: Int = 100
    ): Call<LogInBackData>

    @GET(value = "/draft/{contentId}")
    fun getDraftValue(
        @Query ("contentId") contentId: Int,
    ): Call<LogInBackData>

    @POST(value = "/draft/save")
    fun saveDraft(
        @Body saveDraftForm: Any
    ): Call<LogInBackDataString>

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
            .baseUrl(GlobalMem.BASE_URL)
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

        fun networkThreadArray(
            requestService: (Any)->Call<LogInBackDataArray>,
            body: Any,
            code200: (LogInBackDataArray)->Unit = {
                PopWindows.postValue(
                    ToastMsg(
                        value = it.code.toString() + " " + it.message,
                        type = ToastType.SUCCESS
                    )
                )
            },
            router: (LogInBackDataArray)->Unit = {},
            codeElse: (LogInBackDataArray)->Unit = {
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
            other: (LogInBackDataArray)->Unit = {}
        ){
            Thread{
                requestService(body).enqueue(object : Callback<LogInBackDataArray> {
                    override fun onResponse(
                        call: Call<LogInBackDataArray>,
                        response: Response<LogInBackDataArray>
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
                    override fun onFailure(call: Call<LogInBackDataArray>, t: Throwable) {
                        fail(t)
                    }
                })
            }.start()
        }

        fun networkThreadGet(
            requestService: (Any)->Call<LogInBackData>,
            param: Any,
            code200:(LogInBackData)->Unit = {},
            codeElse:(LogInBackData)->Unit = {},
            fail: (Throwable)->Unit = {},
            other: (LogInBackData)->Unit = {}
        ){
            Thread{
                requestService(param).enqueue(object : Callback<LogInBackData> {
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

data class LogInBackDataArray(
    val message: String,
    val code: Int,
    val data: Array<Map<String, Any>>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LogInBackDataArray

        if (message != other.message) return false
        if (code != other.code) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = message.hashCode()
        result = 31 * result + code
        result = 31 * result + data.contentHashCode()
        return result
    }
}

data class SendCapsuleForm(
    val arriveTime:String,
    val content:String,
    val receiverId:Int,
    val title:String
)

data class applyFriendFrom(
    val applyUserId: Int,
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

data class ChangePasswordForm(
    val code: String,
    val password: String,
    val phone: String
)

data class PostAnonForm(
    val content: String,
    val contentId: Int = 0,
    val moodLevel: Int,
)

data class SaveDraftForm(
    val content: String ,
    val title: String,
    val contentId: Nothing? = null,
)