package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.recruitmentofprojectteammembers.databinding.ActivityPostcontentBinding

private lateinit var binding: ActivityPostcontentBinding

class PostcontentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postcontent)

        binding = ActivityPostcontentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pcBtnPost.setOnClickListener(){
            Toast.makeText(this@PostcontentActivity, "게시물 등록 완료!!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}