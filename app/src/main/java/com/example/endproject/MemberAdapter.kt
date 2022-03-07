package com.example.endproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.endproject.data.Member

class MemberAdapter(private val context: Context, private val listener: OnItemClickListener) : androidx.recyclerview.widget.ListAdapter<Member, MemberAdapter.ViewHolder>(PlayerDiffCallback()) {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
    View.OnClickListener{
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.memberName).text = getItem(position).first
        holder.itemView.findViewById<TextView>(R.id.memberLastName).text = getItem(position).last


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