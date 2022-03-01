package com.example.endproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.endproject.data.Member

class MemberAdapter(private val context: Context) : androidx.recyclerview.widget.ListAdapter<Member, MemberAdapter.ViewHolder>(PlayerDiffCallback()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.memberName).text = getItem(position).first
        holder.itemView.findViewById<TextView>(R.id.memberLastName).text = getItem(position).last

        //glide for image not working yet
        val img: ImageView = holder.itemView.findViewById<ImageView>(R.id.memberImage)
        val url: String = getItem(position).picture

        Glide.with(context).load(url).into(img)
    }

}

class PlayerDiffCallback: DiffUtil.ItemCallback<Member>() {
    override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem.personNumber == newItem.personNumber
    }
    override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem.first == newItem.first && oldItem.last == newItem.last
    }
}