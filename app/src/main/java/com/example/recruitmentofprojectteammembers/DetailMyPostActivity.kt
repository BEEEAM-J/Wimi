package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.recruitmentofprojectteammembers.databinding.ActivityDetailMyPostBinding

private lateinit var bindng : ActivityDetailMyPostBinding

class DetailMyPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_my_post)

        bindng = ActivityDetailMyPostBinding.inflate(layoutInflater)
        setContentView(bindng.root)

        // 수정 버튼 눌렀을 때
        bindng.dmpBtnChange.setOnClickListener(){
            Toast.makeText(this@DetailMyPostActivity, "게시물이 수정되었습니다.", Toast.LENGTH_LONG).show()
            finish()
        }

        // 삭제 버튼 눌렀을 때
        bindng.dmpBtnRemove.setOnClickListener(){
            Toast.makeText(this@DetailMyPostActivity, "게시물이 삭제되었습니다.", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}