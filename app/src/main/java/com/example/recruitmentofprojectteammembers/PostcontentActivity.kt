package com.example.recruitmentofprojectteammembers

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.recruitmentofprojectteammembers.databinding.ActivityPostcontentBinding
import data.PostData
import data.PostModel
import data.Posting
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityPostcontentBinding

class PostcontentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postcontent)

        var postTitle : String
        var postContent : String
        val member_id = intent.getIntExtra("member_id", 0)

        binding = ActivityPostcontentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 게시물 등록 버튼 클릭 동작
        binding.pcBtnPost.setOnClickListener(){
            postTitle = binding.pcEdtTitle.text.toString()
            postContent = binding.pcEdtContent.text.toString()

            val returnIntent = Intent()
            returnIntent.putExtra("postTitle", postTitle)
            returnIntent.putExtra("postContent", postContent)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()

////            제목을 입력하지 않은 경우
//            if (postTitle == ""){
//                Toast.makeText(this@PostcontentActivity, "제목을 입력하세요!", Toast.LENGTH_SHORT).show()
//            }
////            내용을 입력하지 않은 경우
//            else if(postContent == ""){
//                Toast.makeText(this@PostcontentActivity, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                // 서버와 통신하여 게시물 등록
//                retrofitService.requestPosting(PostData(member_id, postTitle, postContent)).enqueue(object : Callback<Posting>{
//                    override fun onResponse(call: Call<Posting>, response: Response<Posting>) {
////                    오류가 생겼을 경우
//                        if (response.body() == Posting("error")){
//                            var dialog = AlertDialog.Builder(this@PostcontentActivity)
//                            dialog.setTitle("다시 시도해주세요.")
//                            dialog.setMessage("")
//                            dialog.show()
//                        }
////                    정상적으로 등록된 경우
//                        else if (response.body() == Posting("success")){
//                            val returnIntent = Intent()
//                            returnIntent.putExtra("postTitle", postTitle)
//                            returnIntent.putExtra("postContent", postContent)
//                            setResult(Activity.RESULT_OK, returnIntent)
//                            finish()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<Posting>, t: Throwable) {
//                        Toast.makeText(this@PostcontentActivity, "에러! 다시 시도 ㄱㄱ", Toast.LENGTH_LONG).show()
//                    }
//
//                })
//            }
        }
    }
}