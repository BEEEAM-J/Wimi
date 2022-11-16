package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import com.example.recruitmentofprojectteammembers.databinding.ActivitySignupBinding
import data.SignupData
import data.Signup
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
        var signUsrEmail : String

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 회원가입 버튼 눌렀을 때
       binding.suCheckSignup.setOnClickListener(){

           binding.progressSignUp.visibility

           signUsrId = binding.suEdtId.text.toString()
           signUsrPw = binding.suEdtPassword.text.toString()
           signUsrEmail = binding.suEdtEmail.text.toString()

           // 서버와 통신하여 회원가입 동작 실행
           retrofitService.requestSignup(SignupData(signUsrId, signUsrPw, signUsrEmail)).enqueue(object : Callback<Signup>{
               override fun onResponse(call: Call<Signup>, response: Response<Signup>) {
//                   if (response.isSuccessful){
//                       Toast.makeText(this@SignupActivity, "${binding.suEdtId.text}님 회원가입이 완료 되었습니다!!", Toast.LENGTH_LONG).show()
//                       finish()
//                   }
//                   아이디 중복 외의 이유 모를 오류인 경우
                   if (response.body() == Signup("unknownError")){
                       binding.progressSignUp.isInvisible
                       Toast.makeText(this@SignupActivity, "다시 시도 해보세요!", Toast.LENGTH_LONG).show()
                   }
//                   중복된 아이디가 입력된 경우
                   else if(response.body() == Signup("overlap")){
                       binding.progressSignUp.isInvisible
                       Toast.makeText(this@SignupActivity, "이미 존재하는 아이디입니다.", Toast.LENGTH_LONG).show()
                   }
                   else{
                       binding.progressSignUp.isInvisible
                       Toast.makeText(this@SignupActivity, "${binding.suEdtId.text}님 회원가입이 완료 되었습니다!!", Toast.LENGTH_LONG).show()
                       finish()
                   }
               }

               override fun onFailure(call: Call<Signup>, t: Throwable) {
                   var dialog = AlertDialog.Builder(this@SignupActivity)
                   dialog.setTitle("에러!")
                   dialog.setMessage("다시 시도 해보세요")
                   dialog.show()
               }

           })

//            Toast.makeText(this@SignupActivity, "${binding.suEdtId.text}님 회원가입이 완료 되었습니다!!", Toast.LENGTH_LONG).show()
//            finish()
        }
    }
}