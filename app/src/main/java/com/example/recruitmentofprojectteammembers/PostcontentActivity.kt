package com.example.recruitmentofprojectteammembers

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.recruitmentofprojectteammembers.databinding.ActivityPostcontentBinding
import data.PostModel
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

        binding = ActivityPostcontentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 게시물 등록 버튼 클릭 동작
        binding.pcBtnPost.setOnClickListener(){
            postTitle = binding.pcEdtTitle.text.toString()
            postContent = binding.pcEdtContent.text.toString()

            // 서버와 통신하여 게시물 등록
//            retrofitService.requestPosting(postTitle, postContent).enqueue(object : Callback<PostModel>{
//                override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
//                    Toast.makeText(this@PostcontentActivity, "게시물 등록 완료!!", Toast.LENGTH_LONG).show()
//                    finish()
//                }
//
//                override fun onFailure(call: Call<PostModel>, t: Throwable) {
//                    Toast.makeText(this@PostcontentActivity, "에러! 다시 시도 ㄱㄱ", Toast.LENGTH_LONG).show()
//                }
//
//            })

            Toast.makeText(this@PostcontentActivity, "게시물 등록 완료!!", Toast.LENGTH_LONG).show()

            val returnIntent = Intent()
            returnIntent.putExtra("postTitle", postTitle)
            returnIntent.putExtra("postContent", postContent)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}