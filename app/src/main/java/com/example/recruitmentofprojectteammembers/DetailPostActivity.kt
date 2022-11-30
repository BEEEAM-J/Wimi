package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruitmentofprojectteammembers.databinding.ActivityDetailPostBinding
import data.*
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityDetailPostBinding
var resultList : Reply = Reply()

class DetailPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)

        var postTitle = intent.getStringExtra("title")
        var postContent = intent.getStringExtra("content")
        var postId = intent.getStringExtra("post_id")

        var replyContent : String


        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dpTitle.text = postTitle
        binding.dpContent.text = postContent

        // 리사이클러뷰 어댑터 선언
        binding.dpReplyRecycler.layoutManager = LinearLayoutManager(this@DetailPostActivity)
        val recyclerAdapter = RecyclerAdapterDP()
        binding.dpReplyRecycler.adapter = recyclerAdapter
        // 리사이클러뷰 아이템 공백 설정 클래스 적용
        binding.dpReplyRecycler.addItemDecoration(recyclerDecoration(40))

        Log.d("tag113", "댓글 동작 확인")
        Log.d("tag113", postId.toString())

        // 댓글 리스트 받아오기
        if (postId != null) {
            retrofitService.requestReplyList(postId).enqueue(object : Callback<Reply>{
                override fun onResponse(call: Call<Reply>, response: Response<Reply>) {
                    response.body()?.let { resultList.addAll(it) }
                    recyclerAdapter.submitList(resultList)
                }

                override fun onFailure(call: Call<Reply>, t: Throwable) {
                    Log.d("empty", "Empty")
                }

            })
        }

//        댓글 등록 버튼 클릭한 경우
        binding.dpReplyBtn.setOnClickListener(){
            replyContent = binding.dpReplyEdt.text.toString()

//            빈 값으로 댓글 등록을 시도하는 경우
            if(binding.dpReplyEdt.text.toString() == ""){
                var dialog = AlertDialog.Builder(this@DetailPostActivity)
                dialog.setTitle("내용을 입력하세요.")
                dialog.setMessage("")
                dialog.show()
            }
            else{

                //  댓글 달기
//            retrofitService.requestReply(ReplyData(loginResponse.member_id, /* 게시물 아이디 postid */ , replyContent)).enqueue(object : Callback<CommentPostStatus>{
//                override fun onResponse(call: Call<CommentPostStatus>, response: Response<CommentPostStatus>) {
//
//                    // 댓글 달기 성공한 경우
//                    if(response.body() == CommentPostStatus("success")){
//                        // 댓글 리스트 받아오기
//                        retrofitService.requestReplyList( /* 게시물 아이디 */).enqueue(object : Callback<Reply>{
//                            override fun onResponse(call: Call<Reply>, response: Response<Reply>) {
//                                resultList.addAll(response.body() as List<Reply>)
//                                recyclerAdapter.submitList(resultList.toList())
//                            }
//
//                            override fun onFailure(call: Call<Reply>, t: Throwable) {
//                                TODO("Not yet implemented")
//                            }
//
//                        })
//                        binding.dpReplyEdt.setText("")
//                    }
//                    else{
//                        var dialog = AlertDialog.Builder(this@DetailPostActivity)
//                        dialog.setTitle("오류")
//                        dialog.setMessage("")
//                        dialog.show()
//                    }
//                }
//
//                override fun onFailure(call: Call<CommentPostStatus>, t: Throwable) {
//                    var dialog = AlertDialog.Builder(this@DetailPostActivity)
//                    dialog.setTitle("오류")
//                    dialog.setMessage("")
//                    dialog.show()
//                }
//
//            })
                // 리사이클러뷰에 출력할 리스트를 어댑터로 전송 (댓글 추가)
//                resultList.add(Reply(loginResponse.member_name,replyContent))
//                recyclerAdapter.submitList(resultList.toList())
//
//                binding.dpReplyEdt.setText("")
            }

        }
    }

}