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

class AddAboutMeFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_about_me, container, false)
        val moreDetailsBtn = view.findViewById<Button>(R.id.addMoreDetailsBtn)
        val aboutMeDetails = view.findViewById<EditText>(R.id.aboutMeDetailsInput)
        moreDetailsBtn.setOnClickListener {
            if (aboutMeDetails.text.isNotEmpty()) {
                Helper.saveAboutMe(context, aboutMeDetails.text.toString())
                val bundle = Bundle()
                bundle.putSerializable("aboutMe", Gson().toJson(aboutMeDetails.text.toString()))
                setFragmentResult("addAboutMeSuccess", bundle)
                dismissAllowingStateLoss()
            }
        }
        return view
    }

}