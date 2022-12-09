package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruitmentofprojectteammembers.databinding.ActivitySearchResBinding
import data.PostModel
import data.SrhPostModel
import network.RetrofitClient
import network.RetrofitClient.retrofitService
import network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding : ActivitySearchResBinding
var srhPostList : SrhPostModel = SrhPostModel()

class SearchResActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_res)

        var searchData = intent.getStringExtra("search")
        srhPostList.clear()

        binding = ActivitySearchResBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리사이클러뷰 어댑터 선언
        binding.srRecyclerPost.layoutManager = LinearLayoutManager(this@SearchResActivity)
        val recyclerAdapter = RecyclerAdapterSR()
        binding.srRecyclerPost.adapter = recyclerAdapter
        // 리사이클러뷰 아이템 공백 설정 클래스 적용
        binding.srRecyclerPost.addItemDecoration(recyclerDecoration(40))

        // 검색어가 입력되었으면 데이터 검색, 검색결과 받아오기
       if (searchData != null) {
            retrofitService.requestSearch(searchData).enqueue(object : Callback<SrhPostModel>{
                override fun onResponse(call: Call<SrhPostModel>, response: Response<SrhPostModel>, ) {
                    response.body()?.let { srhPostList.addAll(it) }
//                response.body()?.javaClass?.let { Log.d("tag", it.name) }
                    Log.d("tag112", srhPostList.toString())
                    recyclerAdapter.submitList(srhPostList.toList())
                }

                override fun onFailure(call: Call<SrhPostModel>, t: Throwable) {
                    var dialog = AlertDialog.Builder(this@SearchResActivity)
                    dialog.setTitle("검색결과 없음")
                    dialog.setMessage("")
                    dialog.show()
                }

            })
        }
    }


}