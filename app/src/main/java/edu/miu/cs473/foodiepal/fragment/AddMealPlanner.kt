package edu.miu.cs473.foodiepal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.google.gson.Gson
import edu.miu.cs473.foodiepal.R
import edu.miu.cs473.foodiepal.model.Meal
import edu.miu.cs473.foodiepal.util.Helper

class AddMealPlanner : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_meal_planner, container, false)
        val saveMealPlanBtn = view.findViewById<Button>(R.id.saveMealPlanBtn)
        val daySelect = view.findViewById<Spinner>(R.id.dayChooser)
        val recipeSelect = view.findViewById<Spinner>(R.id.mealChooser)

        val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        daySelect.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, days)

        val recipes = Helper.getSavedRecipesIfAny(context)
        val recipeNameList = recipes.map{
            it.title
        }
        recipeSelect.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, recipeNameList)

        saveMealPlanBtn.setOnClickListener {
            if (daySelect.selectedItem.toString().isNotEmpty() && recipeSelect.selectedItem.toString().isNotEmpty()) {
                val recipe = recipes.find{
                    it.title == recipeSelect.selectedItem.toString()
                }
                if (recipe != null) {
                    val newMeal = Meal(daySelect.selectedItem.toString(), recipe)
                    Helper.saveMeal(context, newMeal)
                    val bundle = Bundle()
                    bundle.putSerializable("newMeal", Gson().toJson(newMeal))
                    setFragmentResult("addMealPlanSuccess", bundle)
                    dismissAllowingStateLoss()
                }
            }
        }
        return view
    }

}