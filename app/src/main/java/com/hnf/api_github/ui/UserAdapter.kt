package com.hnf.api_github.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hnf.api_github.data.response.ItemsItem

class UserAdapter : ListAdapter<ItemsItem, UserAdapter.MyViewHolder>(){
    class MyViewHolder(val binding: ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }


}