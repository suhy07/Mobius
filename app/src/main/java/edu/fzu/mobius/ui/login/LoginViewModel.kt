package edu.fzu.mobius.ui.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import edu.fzu.mobius.network.LogInBackData
import edu.fzu.mobius.network.Network
import edu.fzu.mobius.network.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel:ViewModel() {
    var phoneNumber = mutableStateOf("")
    var verificationCode = mutableStateOf("")
    var password = mutableStateOf("")

    private var _todoItems1 = MutableLiveData(listOf<LogInBackData>())
    val todoItems1: LiveData<List<LogInBackData>> = _todoItems1

    fun login(navController: NavController){
        Thread(){
            Network.service.logIn(User(phone = phoneNumber.value, password = password.value))
                .enqueue(object : Callback<LogInBackData> {
                    override fun onResponse(
                        call: Call<LogInBackData>,
                        response: Response<LogInBackData>
                    ) {
                        response.body()?.let { it ->
                            _todoItems1.value = mutableListOf(it)
                            POPWindows.postValue("警告:${it.message}")
                            println("logloglog${it}")
                        }
                    }

                    override fun onFailure(call: Call<LogInBackData>, t: Throwable) {
                        t.printStackTrace()
                        println("request wrong1：$t")
                    }
                })
        }.start()
        sendVerificationCode()
    }

    fun getLog() {


    }
    fun sendVerificationCode(){
    }
}
