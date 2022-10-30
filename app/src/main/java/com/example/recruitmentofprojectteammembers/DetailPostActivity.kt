package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recruitmentofprojectteammembers.databinding.ActivityDetailPostBinding

private lateinit var binding: ActivityDetailPostBinding

class DetailPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)

        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}