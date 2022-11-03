package com.example.recruitmentofprojectteammembers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.recruitmentofprojectteammembers.databinding.ActivitySignupBinding
import data.Login
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var signUsrId : String
        var signUsrPw : String

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 회원가입 버튼 눌렀을 때
       binding.suCheckSignup.setOnClickListener(){
           signUsrId = binding.suEdtId.text.toString()
           signUsrPw = binding.suEdtPassword.text.toString()

           // 서버와 통신하여 회원가입 동작 실행
//           retrofitService.requestSignup(signUsrId, signUsrPw).enqueue(object : Callback<Login>{
//               override fun onResponse(call: Call<Login>, response: Response<Login>) {
//                   Toast.makeText(this@SignupActivity, "${binding.suEdtId.text}님 회원가입이 완료 되었습니다!!", Toast.LENGTH_LONG).show()
//                   finish()
//               }
//
//               override fun onFailure(call: Call<Login>, t: Throwable) {
//                   var dialog = AlertDialog.Builder(this@SignupActivity)
//                   dialog.setTitle("에러!")
//                   dialog.setMessage("다시 시도 해보세요")
//                   dialog.show()
//               }
//
//           })

            Toast.makeText(this@SignupActivity, "${binding.suEdtId.text}님 회원가입이 완료 되었습니다!!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}