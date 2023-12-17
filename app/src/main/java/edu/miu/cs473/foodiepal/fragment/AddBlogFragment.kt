package edu.miu.cs473.foodiepal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.google.gson.Gson
import edu.miu.cs473.foodiepal.R
import edu.miu.cs473.foodiepal.model.Blog
import edu.miu.cs473.foodiepal.util.Helper

class AddBlogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_blog, container, false)
        val addBlogButton = view.findViewById<Button>(R.id.addBlogButton)
        val blogTitle = view.findViewById<EditText>(R.id.blogTitleInput)
        val blogDesc = view.findViewById<EditText>(R.id.blogDescInput)
        addBlogButton.setOnClickListener {
            if (blogTitle.text.isNotEmpty() && blogDesc.text.isNotEmpty()) {
                val newBlog = Blog(blogTitle.text.toString(), blogDesc.text.toString())
                Helper.saveBlog(context, newBlog)
                val bundle = Bundle()
                bundle.putSerializable("newBlog", Gson().toJson(newBlog))
                setFragmentResult("addBlogSuccess", bundle)
                dismissAllowingStateLoss()
            }
        }
        return view
    }

}