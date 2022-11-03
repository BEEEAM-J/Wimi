package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruitmentofprojectteammembers.databinding.ActivityMypostBinding
import data.PostModel

private lateinit var binding : ActivityMypostBinding

class MypostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypost)

        binding = ActivityMypostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mpRecyclerPost.layoutManager = LinearLayoutManager(this@MypostActivity)
        val recycleradapter = RecyclerAdapterMP()
        binding.mpRecyclerPost.adapter = recycleradapter

        recycleradapter.submitList(getMPPostItemList())
    }

    private fun getMPPostItemList(): ArrayList<PostModel>{
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