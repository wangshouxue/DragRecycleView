package com.shouxue.dragrecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager

import androidx.annotation.NonNull

import androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags

import androidx.recyclerview.widget.ItemTouchHelper
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView=findViewById<RecyclerView>(R.id.rv)

        val mAttaches= mutableListOf<String>()
        mAttaches.add("我是1")
        mAttaches.add("我是2")
        mAttaches.add("我是3")
        mAttaches.add("我是4")

        val attachAdapter=RvAdapter(this,mAttaches)

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder): Int {
                val dragFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                val swipeFlags = 0
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                if (fromPosition < toPosition) {
                    for (i in fromPosition until toPosition) {
                        Collections.swap(mAttaches, i, i + 1)
                    }
                } else {
                    for (i in fromPosition downTo toPosition + 1) {
                        Collections.swap(mAttaches, i, i - 1)
                    }
                }
                attachAdapter.notifyItemMoved(fromPosition, toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
        })

        recyclerView.setLayoutManager(LinearLayoutManager(this,RecyclerView.HORIZONTAL,false))
        recyclerView.setAdapter(attachAdapter)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}