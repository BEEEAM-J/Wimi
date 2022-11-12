package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.recruitmentofprojectteammembers.databinding.ActivityDetailMyPostBinding
import data.PostModel
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var bindng : ActivityDetailMyPostBinding

class DetailMyPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_my_post)

        var beforeTitle = intent.getStringExtra("title")
        var beforeContent = intent.getStringExtra("content")

        var postTitle : String
        var postContent : String

        bindng = ActivityDetailMyPostBinding.inflate(layoutInflater)
        setContentView(bindng.root)

//        bindng.dmpEdtTitle.text = beforeTitle


        // 수정 버튼 눌렀을 때
        bindng.dmpBtnChange.setOnClickListener(){
            postTitle = bindng.dmpEdtTitle.text.toString()
            postContent = bindng.dmpEdtContent.text.toString()

            // 변경된 내용을 서버로 전송
//            retrofitService.requestRevise(postTitle, postContent).enqueue(object : Callback<PostModel>{
//                override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
//                    Toast.makeText(this@DetailMyPostActivity, "게시물이 수정되었습니다.", Toast.LENGTH_SHORT).show()
//                    finish()
//                }
//
//                override fun onFailure(call: Call<PostModel>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })

            Toast.makeText(this@DetailMyPostActivity, "게시물이 수정되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        // 삭제 버튼 눌렀을 때
        bindng.dmpBtnRemove.setOnClickListener(){
            Toast.makeText(this@DetailMyPostActivity, "게시물이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}