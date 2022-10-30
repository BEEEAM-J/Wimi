package com.example.recruitmentofprojectteammembers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

        // 리사이클러뷰 어댑터 선언
        binding.bsRecyclerPost.layoutManager = LinearLayoutManager(this@BasicActivity)
        val recyclerAdapter = RecyclerAdapterBS()
        binding.bsRecyclerPost.adapter = recyclerAdapter

        // 리사이클러뷰에 출력할 리스트를 어댑터로 전송
        recyclerAdapter.submitList(getBSPostItemList())


        binding.bsCheckMyPost.setOnClickListener(){

            val intent = Intent(this, MypostActivity::class.java)
            startActivity(intent)

        }

        binding.bsPostingBtn.setOnClickListener(){

            val intent = Intent(this, PostcontentActivity::class.java)
            startActivity(intent)

        }


    }

    private fun getBSPostItemList(): ArrayList<PostModel>{
        var resultList = arrayListOf<PostModel>()
        var cnt = 0
        while (cnt++ < 100) {
            val title = "제목: ${cnt}"
            val content = "내용: ${cnt}"

            val product = PostModel(title, content)
            resultList.add(product)
        }
        return resultList
    }


}