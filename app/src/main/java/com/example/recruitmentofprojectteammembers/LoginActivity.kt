package com.example.recruitmentofprojectteammembers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.recruitmentofprojectteammembers.databinding.ActivityLoginBinding
import data.Login
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityLoginBinding

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

            // 레트로핏 사용 로그인 성공 여부 판단
//            retrofitService.requestLogin(usrID, usrPw).enqueue(object : Callback<Login>{
//                override fun onResponse(call: Call<Login>, response: Response<Login>) {
//                    // 로그인 성공하면 화면 전환
//                    val intent = Intent(this@LoginActivity, BasicActivity::class.java)
//                    // 아이디 전달
//                    intent.putExtra("id", usrID)
//                    startActivity(intent)
//                }
//
//                override fun onFailure(call: Call<Login>, t: Throwable) {
//                    var dialog = AlertDialog.Builder(this@LoginActivity)
//                    dialog.setTitle("로그인 실패")
//                    dialog.setMessage("아이디나 비밀번호를 다시 확인하세요!")
//                    dialog.show()
//                }
//
//            })

            val intent = Intent(this, BasicActivity::class.java)
            // 아이디 전달
            intent.putExtra("id", usrID)
            startActivity(intent)
        }
    }
}