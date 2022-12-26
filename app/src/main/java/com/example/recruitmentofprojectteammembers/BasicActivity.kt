package com.example.recruitmentofprojectteammembers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recruitmentofprojectteammembers.databinding.ActivityBasicBinding
import data.PostModel
import data.PostModelItem
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityBasicBinding
private lateinit var recyclerAdapterBS : RecyclerAdapterBS
var postList : PostModel = PostModel()
var backKeyPressTime : Long = 0

@Suppress("DEPRECATION")
class BasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)

        var searchCont : String


        binding = ActivityBasicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리사이클러뷰 역순 설정
        val manager = LinearLayoutManager(this@BasicActivity)
//        manager.reverseLayout = true
//        manager.stackFromEnd = true

        // 리사이클러뷰 어댑터 선언
        binding.bsRecyclerPost.layoutManager = manager
        recyclerAdapterBS = RecyclerAdapterBS()
        binding.bsRecyclerPost.adapter = recyclerAdapterBS
        // 리사이클러뷰 아이템 공백 설정 클래스 적용
        binding.bsRecyclerPost.addItemDecoration(recyclerDecoration(40))


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

    override fun onResume() {
        super.onResume()

        var page : Int = 1
        var limit : Int = 7
        postList.clear()

        // 처음 게시물 불러오기
       retrofitService.requestPostList(page, limit).enqueue(object : Callback<PostModel>{
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                response.body()?.let { postList.addAll(it) }
//                response.body()?.javaClass?.let { Log.d("tag", it.name) }
                recyclerAdapterBS.submitList(postList.toList())
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

//        binding.bsRecyclerPost.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                Toast.makeText(this@BasicActivity, "끝!", Toast.LENGTH_SHORT).show()
//            }
//        })

        // 무한 스크롤
        binding.bsRecyclerPost.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!binding.bsRecyclerPost.canScrollVertically(1)) {
                    Log.i("태그", "End of list")
//                    Toast.makeText(this@BasicActivity, "맨 끝!", Toast.LENGTH_SHORT).show()

                    page += 1

                    // RecyclerView의 끝에 도달하면 다음 게시물들을 불러옴
                   retrofitService.requestPostList(page, limit).enqueue(object : Callback<PostModel>{
                        override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                            response.body()?.let { postList.addAll(it) }
//                response.body()?.javaClass?.let { Log.d("tag", it.name) }
                            recyclerAdapterBS.submitList(postList.toList())
                        }

                        override fun onFailure(call: Call<PostModel>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
                } else {
                    Log.i("태그", "idle")
                }
            }
        })

    }

    // 로그아웃 처리
    override fun onBackPressed() {
        if(System.currentTimeMillis() > (backKeyPressTime + 2500)){
            backKeyPressTime = System.currentTimeMillis()
            Toast.makeText(this@BasicActivity, "한번 더 누르면 로그아웃 됩니다.", Toast.LENGTH_SHORT).show()
            return
        }
        else if(System.currentTimeMillis() <= (backKeyPressTime + 2500)){
            finish()
        }
    }

}