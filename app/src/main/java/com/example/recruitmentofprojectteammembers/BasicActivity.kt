package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recruitmentofprojectteammembers.databinding.ActivityBasicBinding
import com.example.recruitmentofprojectteammembers.databinding.ActivityMainBinding
import data.PostModel

private lateinit var binding: ActivityBasicBinding

class BasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)

        binding = ActivityBasicBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }


}