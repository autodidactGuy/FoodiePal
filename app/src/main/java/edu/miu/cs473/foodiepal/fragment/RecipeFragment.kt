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
import edu.miu.cs473.foodiepal.adapter.RecipeListAdapter
import edu.miu.cs473.foodiepal.model.Recipe
import edu.miu.cs473.foodiepal.util.Helper

class RecipeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recipeList)

        var recipes = Helper.getSavedRecipesIfAny(context).toMutableList()
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = getRecipeAdapter(recipes)

        val addRecipeBtn = view.findViewById<FloatingActionButton>(R.id.addRecipeBtn)
        addRecipeBtn.setOnClickListener {
            val addRecipeFragment = AddRecipeFragment()
            addRecipeFragment.show(childFragmentManager, "Add Recipe")
            addRecipeFragment.setFragmentResultListener("addRecipeSuccess") { requestKey: String, bundle: Bundle? ->
                if(requestKey == "addRecipeSuccess") {
                    run {
                        recipes.add(Gson().fromJson(bundle?.getString("newRecipe"), Recipe::class.java))
                        recyclerView.adapter = getRecipeAdapter(recipes)
                    }
                }
            }
        }
        return view
    }

    private fun getRecipeAdapter(recipes: List<Recipe>):RecipeListAdapter {
        val adapter = RecipeListAdapter(recipes)
        adapter.onItemClick = { recipe ->
            val share = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                type= "text/plain"
                putExtra(Intent.EXTRA_TEXT, recipe.title + "\n" + recipe.cookingTime + "\n"+ recipe.description)
                putExtra(Intent.EXTRA_TITLE, recipe.title)
            }, "Share this amazing Recipe to your friends!")
            startActivity(share)
        }
        return adapter
    }


}

