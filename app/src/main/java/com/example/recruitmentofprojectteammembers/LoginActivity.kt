package com.example.recruitmentofprojectteammembers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.recruitmentofprojectteammembers.databinding.ActivityLoginBinding
import data.Login
import data.LoginData
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityLoginBinding
lateinit var loginResponse : Login

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var usrID : String
        var usrPw : String


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


       // 로그인 버튼 눌렀을 때
        binding.lgCheckLogin.setOnClickListener(){
            usrID = binding.lgEdtId.text.toString()
            usrPw = binding.lgEdtPassword.text.toString()

            //로그인 성공 여부 판단
            retrofitService.requestLogin(LoginData(usrID, usrPw)).enqueue(object : Callback<Login>{
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    loginResponse = response.body()!!
                   // 없는 회원인 경우
                    if(loginResponse.login_status == "not exist"){
                        var dialog = AlertDialog.Builder(this@LoginActivity)
                        dialog.setTitle("존재하지 않는 회원입니다.")
                        dialog.setMessage("")
                        dialog.show()
                    }
                   // 비밀번호가 틀린 경우
                    else if(loginResponse.login_status == "pw mismatch"){
                        var dialog = AlertDialog.Builder(this@LoginActivity)
                        dialog.setTitle("비밀번호가 틀립니다.")
                        dialog.setMessage("")
                        dialog.show()
                    }
                    // 로그인 성공
                    else if (loginResponse.login_status == "login success"){
                        // 로그인 성공하면 화면 전환
                        val intent = Intent(this@LoginActivity, BasicActivity::class.java)
                        // member_id 전달
                        intent.putExtra("member_id", loginResponse.member_id)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "다시 시도 해보세요", Toast.LENGTH_SHORT).show()
                }

            })

        }
    }
}