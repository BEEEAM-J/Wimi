package com.example.recruitmentofprojectteammembers

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recruitmentofprojectteammembers.databinding.ActivityMypostBinding
import data.PostModel
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private lateinit var binding : ActivityMypostBinding
var MyPostList : PostModel = PostModel()
private lateinit var recyclerAdapterMP: RecyclerAdapterMP

class MypostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypost)

        binding = ActivityMypostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리사이클러뷰 어댑터 선언
        binding.mpRecyclerPost.layoutManager = LinearLayoutManager(this@MypostActivity)
        recyclerAdapterMP = RecyclerAdapterMP()
        binding.mpRecyclerPost.adapter = recyclerAdapterMP
        // 리사이클러뷰 아이템 공백 설정 클래스 적용
        binding.mpRecyclerPost.addItemDecoration(recyclerDecoration(40))



//        binding.mpRecyclerPost.setOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                if (!binding.mpRecyclerPost.canScrollVertically(-1)) {
//                    Log.i("태그", "Top of list")
//                    Toast.makeText(this@MypostActivity, "맨 위!", Toast.LENGTH_SHORT).show()
//                } else if (!binding.mpRecyclerPost.canScrollVertically(1)) {
//                    Log.i("태그", "End of list")
//                    Toast.makeText(this@MypostActivity, "맨 끝!", Toast.LENGTH_SHORT).show()
//                } else {
//                    Log.i("태그", "idle")
//                }
//            }
//        })

    }

    override fun onResume() {
        super.onResume()

        // 내가 쓴 글 불러오기
        MyPostList.clear()
        retrofitService.requestMyPost(loginResponse.member_id).enqueue(object : Callback<PostModel>{
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                response.body()?.let { MyPostList.addAll(it) }
//                response.body()?.javaClass?.let { Log.d("tag", it.name) }
                Log.d("tag112", MyPostList.toString())
                recyclerAdapterMP.submitList(MyPostList.toList())
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}