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
        var postId = intent.getIntExtra("post_id", -1)
        var usrId = intent.getIntExtra("create_member_id", -1)

        var replyContent : String

        // 댓글 리스트 초기화 하기
        resultList.clear()

        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dpTitle.text = postTitle
        binding.dpContent.text = postContent
        binding.dpTvusrname.text = "개발자 ${usrId}"

        // 리사이클러뷰 어댑터 선언
        binding.dpReplyRecycler.layoutManager = LinearLayoutManager(this@DetailPostActivity)
        val recyclerAdapter = RecyclerAdapterDP()
        binding.dpReplyRecycler.adapter = recyclerAdapter
        // 리사이클러뷰 아이템 공백 설정 클래스 적용
        binding.dpReplyRecycler.addItemDecoration(recyclerDecoration(40))

        Log.d("tag113", "댓글 동작 확인")
        Log.d("tag113", postId.toString())

        // 처음에 댓글 리스트 받아오기
        if (postId != null) {
            retrofitService.requestReplyList(postId).enqueue(object : Callback<Reply>{
                override fun onResponse(call: Call<Reply>, response: Response<Reply>) {
                    response.body()?.let { resultList.addAll(it) }
                    recyclerAdapter.submitList(resultList.toList())
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
                if(postId != null){
                    retrofitService.requestReply(ReplyData(loginResponse.member_id, postId, replyContent), postId).enqueue(object : Callback<CommentPostStatus> {
                        override fun onResponse(call: Call<CommentPostStatus>, response: Response<CommentPostStatus>, ) {

                            // 댓글 달기 성공한 경우
                            if (response.body() == CommentPostStatus("success")) {
                                // 댓글 리스트 초기화 하기
                                resultList.clear()

                                // 댓글 리스트 받아오기
                                retrofitService.requestReplyList(postId).enqueue(object : Callback<Reply>{
                                    override fun onResponse(call: Call<Reply>, response: Response<Reply>) {
                                        response.body()?.let { resultList.addAll(it) }
                                        recyclerAdapter.submitList(resultList.toList())
                                    }

                                    override fun onFailure(call: Call<Reply>, t: Throwable) {
                                        Log.d("empty", "Empty")
                                    }

                                })
                                binding.dpReplyEdt.setText("")
                            } else {
                                printErrorMsg()
                            }
                        }

                        override fun onFailure(call: Call<CommentPostStatus>, t: Throwable) {
                            printErrorMsg()
                        }

                    })
                }
                // 리사이클러뷰에 출력할 리스트를 어댑터로 전송 (댓글 추가)
//                resultList.add(Reply(loginResponse.member_name,replyContent))
//                recyclerAdapter.submitList(resultList.toList())
//
//                binding.dpReplyEdt.setText("")
            }

        }
    }

    fun printErrorMsg(){
        var dialog = AlertDialog.Builder(this@DetailPostActivity)
        dialog.setTitle("오류")
        dialog.setMessage("")
        dialog.show()
    }

}