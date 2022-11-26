package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruitmentofprojectteammembers.databinding.ActivityMypostBinding
import data.PostModel
import data.PostModelItem

private lateinit var binding : ActivityMypostBinding

class MypostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypost)

        binding = ActivityMypostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리사이클러뷰 어댑터 선언
        binding.mpRecyclerPost.layoutManager = LinearLayoutManager(this@MypostActivity)
        val recycleradapter = RecyclerAdapterMP()
        binding.mpRecyclerPost.adapter = recycleradapter
        // 리사이클러뷰 아이템 공백 설정 클래스 적용
        binding.mpRecyclerPost.addItemDecoration(recyclerDecoration(40))

        recycleradapter.submitList(getMPPostItemList())
    }

    private fun getMPPostItemList(): ArrayList<PostModel>{
        var resultList = arrayListOf<PostModel>()
        var cnt = 0
        while (cnt++ < 100) {
            val title = "제목: ${cnt}"
            val content = "내용: ${cnt}"

            val product = PostModel(1, 1, title, content)
            resultList.add(product)
        }
        return resultList
    }
}