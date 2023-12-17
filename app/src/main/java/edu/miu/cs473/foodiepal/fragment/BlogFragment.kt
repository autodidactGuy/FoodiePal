package edu.miu.cs473.foodiepal.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import edu.miu.cs473.foodiepal.R
import edu.miu.cs473.foodiepal.adapter.BlogListAdapter
import edu.miu.cs473.foodiepal.model.Blog
import edu.miu.cs473.foodiepal.util.Helper

class BlogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blog, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.blogList)

        var blogs = Helper.getSavedBlogsIfAny(context).toMutableList()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = getBlogListAdapter(blogs)

        val addRecipeBtn = view.findViewById<FloatingActionButton>(R.id.addBlogBtn)
        addRecipeBtn.setOnClickListener {
            val addBlogFragment = AddBlogFragment()
            addBlogFragment.show(childFragmentManager, "Add Blog")
            addBlogFragment.setFragmentResultListener("addBlogSuccess") { requestKey: String, bundle: Bundle? ->
                if(requestKey == "addBlogSuccess") {
                    run {
                        blogs.add(Gson().fromJson(bundle?.getString("newBlog"), Blog::class.java))
                        recyclerView.adapter = getBlogListAdapter(blogs)
                    }
                }
            }
        }
        return view
    }

    private fun getBlogListAdapter(blogs: List<Blog>):BlogListAdapter {
        val adapter = BlogListAdapter(blogs)
        adapter.onItemClick = { blog ->
            val share = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                type= "text/plain"
                putExtra(Intent.EXTRA_TEXT, blog.title + "\n"+ blog.description)
                putExtra(Intent.EXTRA_TITLE, blog.title)
            }, "Share this amazing Blog to your friends!")
            startActivity(share)
        }
        return adapter
    }
}