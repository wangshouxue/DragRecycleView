package com.shouxue.dragrecycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author:wangshouxue
 * @date:2022/3/16 下午3:02
 * @description:类作用
 */
class RvAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private var mContext: Context
    private var list:MutableList<String>
    constructor(mContext: Context,list:MutableList<String>){
        this.mContext=mContext
        this.list=list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemHold(LayoutInflater.from(mContext).inflate(R.layout.item_rv,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHold:ItemHold= holder as ItemHold
        itemHold.tv.text=list.get(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: AppCompatTextView
        init {
            tv=itemView.findViewById<AppCompatTextView>(R.id.tv)
        }
    }
}