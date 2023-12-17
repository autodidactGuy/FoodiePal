package edu.miu.cs473.foodiepal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cs473.foodiepal.R
import edu.miu.cs473.foodiepal.adapter.MealListAdapter
import edu.miu.cs473.foodiepal.model.Meal
import edu.miu.cs473.foodiepal.util.Helper

class MealPlannerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_mealplanner, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.mealPlannerList)

        var meals = Helper.getSavedMealPlanIfAny(context).toMutableList()
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = getAboutMeAdapter(meals)
        return view
    }

    private fun getAboutMeAdapter(meals: List<Meal>): MealListAdapter {
        return MealListAdapter(meals)
    }
}