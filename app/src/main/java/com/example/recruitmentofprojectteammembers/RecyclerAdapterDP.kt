package com.example.recruitmentofprojectteammembers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recruitmentofprojectteammembers.databinding.PostReplyBinding
import data.*
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerAdapterDP : ListAdapter<ReplyItem, RecyclerAdapterDP.ViewHolder>(diffUtil){

    inner class ViewHolder(var binding: PostReplyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ReplyItem) {
            binding.apply {
                replyName.text = "개발자 ${item.comment_member_id.toString()}"
                replyContentTv.text = item.comment
                replyContentEdt.setText(item.comment)

                val recyclerAdapter = RecyclerAdapterDP()

                if(loginResponse.member_id == item.comment_member_id){

                    reviseLayout.visibility = View.VISIBLE

                    // 댓글 수정 버튼이 눌린 경우
                    replyRevise.setOnClickListener(){
                        replyContentTv.visibility = View.INVISIBLE
                        replyContentEdt.visibility = View.VISIBLE

                        OkLayout.visibility = View.VISIBLE
                        reviseLayout.visibility = View.INVISIBLE

                        Log.d("ReviseTag", "수정버튼 눌림")
                    }

                    // 댓글 수정 확인 클릭한 경우
                    replyReviseOk.setOnClickListener(){
                        retrofitService.requestReplyRevise(ReplyReviseData(replyContentEdt.text.toString()), item.comment_id).enqueue(object : Callback<ReplyUpdateStatus>{
                            override fun onResponse(call: Call<ReplyUpdateStatus>, response: Response<ReplyUpdateStatus>, ) {
                                // 수정이 성공적으로 된 경우
                                if (response.body() == ReplyUpdateStatus("success")){
                                    replyContentTv.visibility = View.VISIBLE
                                    replyContentEdt.visibility = View.INVISIBLE

                                    OkLayout.visibility = View.INVISIBLE
                                    reviseLayout.visibility = View.VISIBLE

                                    // 업데이트된 댓글 리스트 받아오고, 출력
                                    resultList.clear()

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
                                }
                            }

                            override fun onFailure(call: Call<ReplyUpdateStatus>, t: Throwable, ) {
                                TODO("Not yet implemented")
                            }
                        })
                    }

                    // 취소를 누른 경우
                    replyReviseCancel.setOnClickListener(){
                        replyContentTv.visibility = View.VISIBLE
                        replyContentEdt.visibility = View.INVISIBLE

                        Log.d("CancelTag", "취소버튼 눌림")

                        OkLayout.visibility = View.INVISIBLE
                        reviseLayout.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PostReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // currentList: 해당 Adapter에 "submitList()"를 통해 삽입한 아이템 리스트
        holder.bind(currentList[position])
    }

    companion object {
        // diffUtil: currentList에 있는 각 아이템들을 비교하여 최신 상태를 유지하도록 한다.
        val diffUtil = object : DiffUtil.ItemCallback<ReplyItem>() {

            //            두 아이템이 동일한 아이템인지 확인
            override fun areItemsTheSame(oldItem: ReplyItem, newItem: ReplyItem): Boolean {
                return oldItem == newItem
            }

            //            두 아이템이 동일한 내용을 가지고 있는지 확인
            override fun areContentsTheSame(oldItem: ReplyItem, newItem: ReplyItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}