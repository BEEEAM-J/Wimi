package com.example.recruitmentofprojectteammembers

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recruitmentofprojectteammembers.databinding.PostReplyBinding
import com.example.recruitmentofprojectteammembers.databinding.PostTitleBinding
import data.PostModel
import data.Reply

class RecyclerAdapterDP : ListAdapter<Reply, RecyclerAdapterDP.ViewHolder>(diffUtil){

    inner class ViewHolder(var binding: PostReplyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Reply) {
            binding.apply {
                replyName.text = item.name
                replyContent.text = item.content
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
        val diffUtil = object : DiffUtil.ItemCallback<Reply>() {

            //            두 아이템이 동일한 아이템인지 확인
            override fun areItemsTheSame(oldItem: Reply, newItem: Reply): Boolean {
                return oldItem.content == newItem.content
            }

            //            두 아이템이 동일한 내용을 가지고 있는지 확인
            override fun areContentsTheSame(oldItem: Reply, newItem: Reply): Boolean {
                return oldItem == newItem
            }
        }
    }
}