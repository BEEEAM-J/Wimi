package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.recruitmentofprojectteammembers.databinding.ActivityDetailMyPostBinding
import data.Posting
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

        bindng.dmpEdtTitle.setText(beforeTitle)
        bindng.dmpEdtContent.setText(beforeContent)


        // 수정 버튼 눌렀을 때
        bindng.dmpBtnChange.setOnClickListener(){
            postTitle = bindng.dmpEdtTitle.text.toString()
            postContent = bindng.dmpEdtContent.text.toString()

            // 변경된 내용을 서버로 전송 (post_id 변수 추가해야함)
//            retrofitService.requestRevise(Posting(postTitle, postContent)).enqueue(object : Callback<Posting>{
//                override fun onResponse(call: Call<Posting>, response: Response<Posting>) {
//                    if(response.body() == Posting("success")){
//                        Toast.makeText(this@DetailMyPostActivity, "게시물이 수정되었습니다.", Toast.LENGTH_SHORT).show()
//                        finish()
//                    }
//                    else if (response.body() == Posting("error")){
//                        Toast.makeText(this@DetailMyPostActivity, "다시 시도해보세요.", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<Posting>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })

            Toast.makeText(this@DetailMyPostActivity, "게시물이 수정되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        // 삭제 버튼 눌렀을 때
        bindng.dmpBtnRemove.setOnClickListener(){

//            삭제할 post_id를 서버로 전송 (post_id 변수 추가해야함)
//            retrofitService.requestDelete().enqueue(object : Callback<Posting>{
//                override fun onResponse(call: Call<Posting>, response: Response<Posting>) {
//                    if(response.body() == Posting("success")){
//                        Toast.makeText(this@DetailMyPostActivity, "게시물이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
//                        finish()
//                    }
//                    else if (response.body() == Posting("error")){
//                        Toast.makeText(this@DetailMyPostActivity, "다시 시도해보세요", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<Posting>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })

            Toast.makeText(this@DetailMyPostActivity, "게시물이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}