package edu.miu.cs473.foodiepal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.google.gson.Gson
import edu.miu.cs473.foodiepal.R
import edu.miu.cs473.foodiepal.model.Recipe
import edu.miu.cs473.foodiepal.util.Helper

class AddRecipeFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_recipe, container, false)
        val addRecipeButton = view.findViewById<Button>(R.id.addRecipeButton)
        val recipeTitle = view.findViewById<EditText>(R.id.recipeTitleInput)
        val recipeCookingTime = view.findViewById<EditText>(R.id.cookingTimeInput)
        val recipeStars = view.findViewById<RatingBar>(R.id.recipeRatingInput)
        val recipeDesc = view.findViewById<EditText>(R.id.recipeDescInput)
        addRecipeButton.setOnClickListener {
            if (recipeTitle.text.isNotEmpty() && recipeStars.rating > 0 && recipeCookingTime.text.isNotEmpty() && recipeDesc.text.isNotEmpty()) {
                val newRecipe = Recipe(recipeTitle.text.toString(), "${recipeCookingTime.text} minutes", recipeDesc.text.toString(), recipeStars.rating.toString())
                Helper.saveRecipe(context, newRecipe)
                val bundle = Bundle()
                bundle.putSerializable("newRecipe", Gson().toJson(newRecipe))
                setFragmentResult("addRecipeSuccess", bundle)
                dismissAllowingStateLoss()
            }
        }
        return view
    }

}