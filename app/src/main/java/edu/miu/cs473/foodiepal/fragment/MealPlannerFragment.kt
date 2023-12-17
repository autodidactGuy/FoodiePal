package edu.miu.cs473.foodiepal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import edu.miu.cs473.foodiepal.R
import edu.miu.cs473.foodiepal.adapter.MealListAdapter
import edu.miu.cs473.foodiepal.model.Blog
import edu.miu.cs473.foodiepal.model.Meal
import edu.miu.cs473.foodiepal.model.Recipe
import edu.miu.cs473.foodiepal.util.Helper
import java.time.LocalDate
import java.util.Calendar

class MealPlannerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_mealplanner, container, false)
        val calendar = view.findViewById<CalendarView>(R.id.calendarView)
        val todayMealTitle = view.findViewById<TextView>(R.id.todaysMealTitle)
        val todayMeal = view.findViewById<FrameLayout>(R.id.todaysMeal)
        val addMealPlannerBtn = view.findViewById<FloatingActionButton>(R.id.addMealPlannerBtn)

        var meals = Helper.getSavedMealPlanIfAny(context)
        todayMealTitle.text = getString(R.string.todays_meal)
        calendar.setDate(Calendar.getInstance().timeInMillis,false,true)
        updateTodayMeal(meals, LocalDate.now(), inflater, todayMeal)

        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            updateTodayMeal(meals, LocalDate.of(year, month + 1, dayOfMonth), inflater, todayMeal)
        }

        addMealPlannerBtn.setOnClickListener{
            val addMealFragment = AddMealPlanner()
            addMealFragment.show(childFragmentManager, "Add Meal")
            addMealFragment.setFragmentResultListener("addMealPlanSuccess") { requestKey: String, bundle: Bundle? ->
                if(requestKey == "addMealPlanSuccess") {
                    run {
                        meals = Helper.getSavedMealPlanIfAny(context)
                        calendar.setDate(Calendar.getInstance().timeInMillis,false,true)
                        updateTodayMeal(meals, LocalDate.now(), inflater, todayMeal)
                        val overridenMeal = Gson().fromJson(bundle?.getString("newMeal"), Meal::class.java)
                        Toast.makeText(context, "Meal Plan for ${overridenMeal.day} updated to ${overridenMeal.recipe.title}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        return view
    }

    private fun updateTodayMeal(meals: Map<String, Recipe>, localDate: LocalDate, inflater: LayoutInflater, parent: FrameLayout) {
        val todayRecipe = meals[Helper.getDayOfWeek(localDate)]
        val todayRecipeView = inflater.inflate(R.layout.item_recipe,parent, false)
        todayRecipeView.findViewById<TextView>(R.id.recipeTitle).text = todayRecipe?.title
        todayRecipeView.findViewById<TextView>(R.id.recipeDesc).text = todayRecipe?.description
        todayRecipeView.findViewById<TextView>(R.id.cookingTime).text = todayRecipe?.cookingTime
        todayRecipeView.findViewById<RatingBar>(R.id.recipeStars).rating = todayRecipe?.stars?.toFloat()?:0f
        todayRecipeView.findViewById<ImageView>(R.id.recipeImage).setImageResource(todayRecipe?.recipeImage?:R.drawable.baseline_fastfood_24)
        parent.addView(todayRecipeView)
    }

    private fun getAboutMeAdapter(meals: List<Meal>): MealListAdapter {
        return MealListAdapter(meals)
    }
}