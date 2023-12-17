package edu.miu.cs473.foodiepal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.miu.cs473.foodiepal.R
import edu.miu.cs473.foodiepal.util.Helper

class AboutMeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_aboutme, container, false)
        val addAboutMeButton = view.findViewById<FloatingActionButton>(R.id.addAboutMeBtn)
        val aboutMeDetails = view.findViewById<TextView>(R.id.customDetails)
        aboutMeDetails.text = Helper.getAboutMeIfAny(context)
        addAboutMeButton.setOnClickListener{
            val addAboutMeFragment = AddAboutMeFragment()
            addAboutMeFragment.show(childFragmentManager, "Add About Me")
            addAboutMeFragment.setFragmentResultListener("addAboutMeSuccess") { requestKey: String, bundle: Bundle? ->
                if(requestKey == "addAboutMeSuccess") {
                    run {
                        aboutMeDetails.text = bundle?.getString("aboutMe")
                    }
                }
            }
        }
        return view
    }
}