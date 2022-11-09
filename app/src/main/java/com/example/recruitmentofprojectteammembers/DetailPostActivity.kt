package com.example.recruitmentofprojectteammembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruitmentofprojectteammembers.databinding.ActivityDetailPostBinding
import data.PostModel
import data.Reply
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityDetailPostBinding

class DetailPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)

        var replyContent : String
        var resultList = arrayListOf<Reply>()

        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리사이클러뷰 어댑터 선언
        binding.dpReplyRecycler.layoutManager = LinearLayoutManager(this@DetailPostActivity)
        val recyclerAdapter = RecyclerAdapterDP()
        binding.dpReplyRecycler.adapter = recyclerAdapter
        // 리사이클러뷰 아이템 공백 설정 클래스 적용
        binding.dpReplyRecycler.addItemDecoration(recyclerDecoration(40))



        binding.dpReplyBtn.setOnClickListener(){
            replyContent = binding.dpReplyEdt.text.toString()

            // 레트로핏 이용 댓글 달기
//            retrofitService.requestReply(replyContent).enqueue(object : Callback<Reply>{
//                override fun onResponse(call: Call<Reply>, response: Response<Reply>) {
//                    // 리사이클러뷰에 출력할 리스트를 어댑터로 전송
//                    recyclerAdapter.submitList(getDPReplyItemList(replyContent))
//                }
//
//                override fun onFailure(call: Call<Reply>, t: Throwable) {
//                    var dialog = AlertDialog.Builder(this@DetailPostActivity)
//                    dialog.setTitle("에러")
//                    dialog.setMessage("다시 시도 해보세요")
//                    dialog.show()
//                }
//
//            })

            // 리사이클러뷰에 출력할 리스트를 어댑터로 전송 (댓글 추가)
            resultList.add(Reply(replyContent))
            recyclerAdapter.submitList(resultList)
            // 리사이클러뷰 업데이트
            recyclerAdapter.notifyDataSetChanged()

//            var dialog = AlertDialog.Builder(this@DetailPostActivity)
//            dialog.setTitle("댓글 작성 완료")
//            dialog.setMessage("${replyContent}")
//            dialog.show()
        }
    }

}