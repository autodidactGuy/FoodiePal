package edu.miu.cs473.foodiepal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cs473.foodiepal.R
import edu.miu.cs473.foodiepal.model.Blog

class BlogListAdapter(private val blogs: List<Blog>): RecyclerView.Adapter<BlogListAdapter.MyViewHolder>() {
    var onItemClick: ((Blog) -> Unit)? = null
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val blogDesc: TextView = itemView.findViewById(R.id.blogDesc)
        val blogTitle: TextView = itemView.findViewById(R.id.blogTitle)
        init{
            itemView.setOnClickListener {
                onItemClick?.invoke(blogs[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return blogs.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.blogTitle.text = blogs[position].title
        holder.blogDesc.text = blogs[position].description
    }
}