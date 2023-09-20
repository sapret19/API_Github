package com.hnf.api_github.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hnf.api_github.R
import com.hnf.api_github.data.response.GithubResponse
import com.hnf.api_github.data.response.ItemsItem
import com.hnf.api_github.databinding.ItemUserBinding
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(
    private val context: Context,
    private val dataList: ArrayList<GithubResponse>
): RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    class MyViewHolder (val view: View): RecyclerView.ViewHolder(view){
        val tvAvatar = view.findViewById<CircleImageView>(R.id.img_user)
        val tvName = view.findViewById<TextView>(R.id.textUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_user, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = dataList.get(position).items?.get(position)?.login
        Glide.with(this)
            .load(dataList.get(position).items?.get(position)?.avatarUrl)
            .into(binding)
    }

}