package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.recruitmentofprojectteammembers.databinding.ActivityDetailMyPostBinding

private lateinit var binding : ActivityDetailMyPostBinding

class DetailMyPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_my_post)

        var beforeTitle = intent.getStringExtra("title")
        var beforeContent = intent.getStringExtra("content")

        var postTitle : String
        var postContent : String

        binding = ActivityDetailMyPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dmpEdtTitle.setText(beforeTitle)
        binding.dmpEdtTitle.textSize = 20F
        binding.dmpEdtContent.setText(beforeContent)


        // 수정 버튼 눌렀을 때
        binding.dmpBtnChange.setOnClickListener(){
            postTitle = binding.dmpEdtTitle.text.toString()
            postContent = binding.dmpEdtContent.text.toString()

            // 변경된 내용을 서버로 전송 (post_id 변수 추가해야함)
//            retrofitService.requestRevise(PostUpdateData(postTitle, postContent)).enqueue(object : Callback<ReviseStatus> {
//                override fun onResponse(call: Call<ReviseStatus>, response: Response<ReviseStatus>) {
//                    if(response.body() == ReviseStatus("success")){
//                        Toast.makeText(this@DetailMyPostActivity, "게시물이 수정되었습니다.", Toast.LENGTH_SHORT).show()
//                        finish()
//                    }
//                    else if (response.body() == ReviseStatus("error")){
//                        Toast.makeText(this@DetailMyPostActivity, "다시 시도해보세요.", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<ReviseStatus>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })

            Toast.makeText(this@DetailMyPostActivity, "게시물이 수정되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        // 삭제 버튼 눌렀을 때
        binding.dmpBtnRemove.setOnClickListener(){

//            삭제할 post_id를 서버로 전송 (post_id 변수 추가해야함)
//            retrofitService.requestDelete().enqueue(object : Callback<DeleteStatus>{
//                override fun onResponse(call: Call<DeleteStatus>, response: Response<DeleteStatus>) {
//                    if(response.body() == DeleteStatus("success")){
//                        Toast.makeText(this@DetailMyPostActivity, "게시물이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
//                        finish()
//                    }
//                    else if (response.body() == DeleteStatus("error")){
//                        Toast.makeText(this@DetailMyPostActivity, "다시 시도해보세요", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<DeleteStatus>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })

            Toast.makeText(this@DetailMyPostActivity, "게시물이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}