package com.example.recruitmentofprojectteammembers

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruitmentofprojectteammembers.databinding.ActivityBasicBinding
import com.example.recruitmentofprojectteammembers.databinding.ActivityMainBinding
import data.PostModel
import data.Reply

private lateinit var binding: ActivityBasicBinding

class BasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)

        Log.d("1", "Log ----- 3")

        binding = ActivityBasicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var postList = arrayListOf<PostModel>()

        // 사용자 이름 받아옴
        var usrID = intent.getStringExtra("id");

        var searchCont : String

        // 리사이클러뷰 역순 설정
        val manager = LinearLayoutManager(this@BasicActivity)
        manager.reverseLayout = true
        manager.stackFromEnd = true

        // 리사이클러뷰 어댑터 선언
        binding.bsRecyclerPost.layoutManager = manager
        val recyclerAdapter = RecyclerAdapterBS()
        binding.bsRecyclerPost.adapter = recyclerAdapter
        // 리사이클러뷰 아이템 공백 설정 클래스 적용
        binding.bsRecyclerPost.addItemDecoration(recyclerDecoration(40))

        // 게시물 등록시 해당 게시물의 제목 받아오기
        val startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val postTitle = result.data?.getStringExtra("postTitle").toString()
                val postContent = result.data?.getStringExtra("postContent").toString()
                postList.add(PostModel(postTitle, postContent))
                recyclerAdapter.submitList(postList)
                recyclerAdapter.notifyDataSetChanged()
            }
        }


        // 내가 쓴 글 버튼 클릭 동작
        binding.bsCheckMyPost.setOnClickListener(){

            val intent = Intent(this, MypostActivity::class.java)
            startActivity(intent)

        }

        // + 버튼 클릭 동작
        binding.bsPostingBtn.setOnClickListener(){

            val intent = Intent(this, PostcontentActivity::class.java)
            startForResult.launch(intent)

        }

        // 검색 버튼 클릭 동작
        binding.bsSearchBtn.setOnClickListener(){

            searchCont = binding.bsSearchEdt.text.toString()
            val intent = Intent(this, SearchResActivity::class.java)
            intent.putExtra("search", searchCont)
            startActivity(intent)

        }

    }

}