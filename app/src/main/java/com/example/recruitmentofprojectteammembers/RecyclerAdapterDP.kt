package com.example.recruitmentofprojectteammembers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recruitmentofprojectteammembers.databinding.ActivityDetailPostBinding
import com.example.recruitmentofprojectteammembers.databinding.PostReplyBinding
import data.*
import network.RetrofitClient.retrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding1: ActivityDetailPostBinding

class RecyclerAdapterDP : ListAdapter<ReplyItem, RecyclerAdapterDP.ViewHolder>(diffUtil){

    inner class ViewHolder(var binding: PostReplyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ReplyItem) {
            binding.apply {
                replyName.text = "개발자 ${item.comment_member_id.toString()}"
                replyContentTv.text = item.comment
                replyContentEdt.setText(item.comment)

//                binding1 = ActivityDetailPostBinding.inflate(layoutInflater)

                // 리사이클러뷰 어댑터 선언
//                binding1.dpReplyRecycler.layoutManager = LinearLayoutManager(DetailPostActivity)
//                val recyclerAdapter = RecyclerAdapterDP()
//                binding1.dpReplyRecycler.adapter = recyclerAdapter

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

                    // 취소를 누른 경우
                    replyReviseCancel.setOnClickListener(){
                        replyContentTv.visibility = View.VISIBLE
                        replyContentEdt.visibility = View.INVISIBLE

                        Log.d("CancelTag", "취소버튼 눌림")

                        OkLayout.visibility = View.INVISIBLE
                        reviseLayout.visibility = View.VISIBLE
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
                                    Log.d("댓글 태그1", "1")

                                    val reviseItem : Int = currentList.indexOf(item)
                                    Log.d("댓글 수정 아이템", reviseItem.toString())
                                    val tempList = mutableListOf<ReplyItem>()
                                    tempList.addAll(currentList)
                                    tempList[reviseItem].comment = replyContentEdt.text.toString()
                                    Log.d("댓글 수정 아이템", tempList[reviseItem].comment.toString())
                                    Log.d("수정된 댓글 아이템", tempList.toString())
                                    Log.d("기존 댓글 아이템", currentList.toString())
                                    submitList(tempList)

                                }
                            }

                            override fun onFailure(call: Call<ReplyUpdateStatus>, t: Throwable, ) {
                                TODO("Not yet implemented")
                            }
                        })
                    }

                    // 댓글 삭제 누른 경우
                    replyDelete.setOnClickListener(){
                        retrofitService.requestReplyDelete(item.comment_id).enqueue(object : Callback<CommentDeleteStatus>{
                            override fun onResponse(
                                call: Call<CommentDeleteStatus>,
                                response: Response<CommentDeleteStatus>,
                            ) {
                                Log.d("댓글 삭제 태그", "@@@@@@@")

                                val deleteItem = currentList.find { it.comment_id == item.comment_id }
                                val tempList = mutableListOf<ReplyItem>()
                                tempList.addAll(currentList)
                                tempList.remove(deleteItem)
                                submitList(tempList)

                            }

                            override fun onFailure(call: Call<CommentDeleteStatus>, t: Throwable) {
                                TODO("Not yet implemented")
                            }

                        })
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