package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruitmentofprojectteammembers.databinding.ActivitySearchResBinding
import data.PostModel

private lateinit var binding : ActivitySearchResBinding

class SearchResActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_res)

        binding = ActivitySearchResBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리사이클러뷰 어댑터 선언
        binding.srRecyclerPost.layoutManager = LinearLayoutManager(this@SearchResActivity)
        val recyclerAdapter = RecyclerAdapterBS()
        binding.srRecyclerPost.adapter = recyclerAdapter

        // 리사이클러뷰에 출력할 리스트를 어댑터로 전송
        recyclerAdapter.submitList(getBSPostItemList())
    }

    private fun getBSPostItemList(): ArrayList<PostModel>{
        var resultList = arrayListOf<PostModel>()
        var cnt = 0
        while (cnt++ < 30) {
            val title = "제목: ${cnt}"
            val content = "내용: ${cnt}"

            val product = PostModel(title, content)
            resultList.add(product)
        }
        return resultList
    }
}