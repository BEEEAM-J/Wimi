package com.example.recruitmentofprojectteammembers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruitmentofprojectteammembers.databinding.ActivityBasicBinding
import data.PostModel
import data.PostModelItem
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityBasicBinding
var postList : PostModel = PostModel()

class BasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)

        var searchCont : String

        binding = ActivityBasicBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        // 전체 게시글 불러오기
        retrofitService.requestPostList().enqueue(object : Callback<PostModel>{
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                response.body()?.let { postList.addAll(it) }
//                response.body()?.javaClass?.let { Log.d("tag", it.name) }
                Log.d("tag112", postList.toString())
                recyclerAdapter.submitList(postList.toList())
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        // 게시물 등록시 해당 게시물의 제목 받아오기
//        val startForResult = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { result: ActivityResult ->
//            if (result.resultCode == RESULT_OK) {
//                val postTitle = result.data?.getStringExtra("postTitle").toString()
//                val postContent = result.data?.getStringExtra("postContent").toString()
//                postList.add(PostModel(1, 1, postTitle, postContent))
//                recyclerAdapter.submitList(postList.toList())
//            }
//        }

        // 내가 쓴 글 버튼 클릭 동작
        binding.bsCheckMyPost.setOnClickListener(){

            val intent = Intent(this, MypostActivity::class.java)
            startActivity(intent)

        }

        // + 버튼 클릭 동작
        binding.bsPostingBtn.setOnClickListener(){

            val intent = Intent(this, PostcontentActivity::class.java)
            startActivity(intent)
//            startForResult.launch(intent)

        }

        // 검색 버튼 클릭 동작
        binding.bsSearchBtn.setOnClickListener(){

            searchCont = binding.bsSearchEdt.text.toString()
            val intent = Intent(this, SearchResActivity::class.java)
            intent.putExtra("search", searchCont)
            startActivity(intent)

        }


    }

//    override fun onResume() {
//        super.onResume()
//        setContentView(R.layout.activity_basic)
//
//        var searchCont : String
//
//        // 전체 게시물 불러오기
//       retrofitService.requestPostList().enqueue(object : Callback<PostModel>{
//            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
//                response.body()?.let { postList.addAll(it) }
////                response.body()?.javaClass?.let { Log.d("tag", it.name) }
//                Log.d("tag112", postList.toString())
//                val recyclerAdapter = RecyclerAdapterBS()
//                recyclerAdapter.submitList(postList.toList())
//            }
//
//            override fun onFailure(call: Call<PostModel>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//
//        // 내가 쓴 글 버튼 클릭 동작
//        binding.bsCheckMyPost.setOnClickListener(){
//
//            val intent = Intent(this, MypostActivity::class.java)
//            startActivity(intent)
//
//        }
//
//        // + 버튼 클릭 동작
//        binding.bsPostingBtn.setOnClickListener(){
//
//            val intent = Intent(this, PostcontentActivity::class.java)
//            startActivity(intent)
////            startForResult.launch(intent)
//
//        }
//
//        // 검색 버튼 클릭 동작
//        binding.bsSearchBtn.setOnClickListener(){
//
//            searchCont = binding.bsSearchEdt.text.toString()
//            val intent = Intent(this, SearchResActivity::class.java)
//            intent.putExtra("search", searchCont)
//            startActivity(intent)
//
//        }
//    }

}