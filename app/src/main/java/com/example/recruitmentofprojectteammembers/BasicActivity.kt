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

        // 사용자 이름 받아옴
        var usrID = intent.getStringExtra("id");

        var searchCont : String

        // 리사이클러뷰 어댑터 선언
        binding.bsRecyclerPost.layoutManager = LinearLayoutManager(this@BasicActivity)
        val recyclerAdapter = RecyclerAdapterBS()
        binding.bsRecyclerPost.adapter = recyclerAdapter
        // 리사이클러뷰 아이템 공백 설정 클래스 적용
        binding.bsRecyclerPost.addItemDecoration(recyclerDecoration(40))

        // 리사이클러뷰에 출력할 리스트를 어댑터로 전송
        recyclerAdapter.submitList(getBSPostItemList())

        // 내가 쓴 글 버튼 클릭 동작
        binding.bsCheckMyPost.setOnClickListener(){

            val intent = Intent(this, MypostActivity::class.java)
            startActivity(intent)

        }

        // + 버튼 클릭 동작
        binding.bsPostingBtn.setOnClickListener(){

            val intent = Intent(this, PostcontentActivity::class.java)
            startActivity(intent)

        }

        // 검색 버튼 클릭 동작
        binding.bsSearchBtn.setOnClickListener(){

            searchCont = binding.bsSearchEdt.text.toString()
            val intent = Intent(this, SearchResActivity::class.java)
            intent.putExtra("search", searchCont)
            startActivity(intent)

        }

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