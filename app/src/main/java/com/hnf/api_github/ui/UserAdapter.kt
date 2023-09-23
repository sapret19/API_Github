package com.hnf.api_github.ui

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
    val dataList: List<ItemsItem>
): RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    class MyViewHolder (view: View): RecyclerView.ViewHolder(view){
        val tvAvatar: CircleImageView = view.findViewById<CircleImageView>(R.id.img_user)
        val tvName: TextView = view.findViewById<TextView>(R.id.textUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = dataList?.get(position)?.login
        Glide.with(holder.tvAvatar)
            .load(dataList?.get(position)?.avatarUrl)
            .error(R.drawable.ic_launcher_background)
            .into(holder.tvAvatar)

        holder.itemView.setOnClickListener{
            val name = dataList?.get(position)?.login
            Toast.makeText(holder.itemView.context, "${name}", Toast.LENGTH_SHORT).show()
        }
    }



}