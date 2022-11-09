package com.example.recruitmentofprojectteammembers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

// 리사이클러뷰 아이템 사이 공백 생성

class recyclerDecoration(private val blankHeight : Int) : RecyclerView.ItemDecoration(){

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = blankHeight
    }
}