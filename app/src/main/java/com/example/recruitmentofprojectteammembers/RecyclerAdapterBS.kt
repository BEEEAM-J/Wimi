package com.example.recruitmentofprojectteammembers

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recruitmentofprojectteammembers.databinding.PostTitleBinding
import data.PostModel
import data.PostModelItem


class RecyclerAdapterBS : ListAdapter<PostModelItem, RecyclerAdapterBS.ViewHolder>(diffUtil){

    inner class ViewHolder(var binding: PostTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(item: PostModelItem) {
            binding.apply {
                postTitle.text = item.title
                postContent.text = item.content
//                for(i in 0.. 3){
//                    postTitle.text = item[i].title
//                    postContent.text = item[i].content
//                    Log.d("tag111", "for문 돈다!!!")
//                }
            }
            // 리사이클러뷰 아이템 클릭 이벤트 설정
            itemView.setOnClickListener(){
                val intent = Intent(context, DetailPostActivity::class.java)
                intent.putExtra("title", item.title)
                intent.putExtra("content", item.content)
                intent.putExtra("post_id", item.post_id)
                intent.putExtra("create_member_id", item.create_member_id)
                Log.d("포스트 아이디 확인", item.post_id.toString())
                intent.run { context.startActivity(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PostTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        // currentList: 해당 Adapter에 "submitList()"를 통해 삽입한 아이템 리스트
        holder.bind(currentList[p1])
    }

    companion object {
        // diffUtil: currentList에 있는 각 아이템들을 비교하여 최신 상태를 유지하도록 한다.
        val diffUtil = object : DiffUtil.ItemCallback<PostModelItem>() {

            //            두 아이템이 동일한 아이템인지 확인
            override fun areItemsTheSame(oldItem: PostModelItem, newItem: PostModelItem): Boolean {
                return oldItem == newItem
            }

            //            두 아이템이 동일한 내용을 가지고 있는지 확인
            override fun areContentsTheSame(oldItem: PostModelItem, newItem: PostModelItem): Boolean {
                return oldItem == newItem
            }
        }
    }


}