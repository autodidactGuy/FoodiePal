package edu.miu.cs473.foodiepal.util

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.miu.cs473.foodiepal.R
import edu.miu.cs473.foodiepal.model.Blog
import edu.miu.cs473.foodiepal.model.Meal
import edu.miu.cs473.foodiepal.model.Recipe
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class Helper {
    companion object {
        fun getSavedRecipesIfAny(context: Context?): List<Recipe> {
            val recipes = mutableListOf(
                Recipe("Biryani",
                    "60 minutes",
                    "Biryani is a delicious rice dish peppered with scrumptious spices like saffron and cumin and further layered with spiced meat or veggie protein. Typically, this layered rice dish is enriched with added yogurt or ghee, a clarified butter, for an extra boost of luxurious flavor.",
                    "5",
                    R.drawable.steak),
                Recipe("Karahi",
                    "55 minutes",
                    "The dish is prepared in a karahi (a type of wok) and can take between 30 and 50 minutes to prepare and cook the dish. Cumin, green chilis, ginger, garlic, tomatoes and coriander are key to the flavor of the dish.",
                    "4",
                    R.drawable.fish),
                Recipe("Mix Vegetable",
                    "30 minutes",
                    "The dish is prepared in a karahi (a type of wok) and can take between 30 and 50 minutes to prepare and cook the dish. Cumin, green chilis, ginger, garlic, tomatoes and coriander are key to the flavor of the dish.",
                    "4",
                    R.drawable.carrot),
                Recipe("Palao",
                    "50 minutes",
                    "The dish is prepared in a karahi (a type of wok) and can take between 30 and 50 minutes to prepare and cook the dish. Cumin, green chilis, ginger, garlic, tomatoes and coriander are key to the flavor of the dish.",
                    "4",
                    R.drawable.mobile_steak),
                Recipe("Daal Chawal",
                    "30 minutes",
                    "The dish is prepared in a karahi (a type of wok) and can take between 30 and 50 minutes to prepare and cook the dish. Cumin, green chilis, ginger, garlic, tomatoes and coriander are key to the flavor of the dish.",
                    "4",
                    R.drawable.tomato),
                Recipe("Cake",
                    "55 minutes",
                    "The dish is prepared in a karahi (a type of wok) and can take between 30 and 50 minutes to prepare and cook the dish. Cumin, green chilis, ginger, garlic, tomatoes and coriander are key to the flavor of the dish.",
                    "4",
                    R.drawable.cake),
                Recipe("Cupcake",
                    "45 minutes",
                    "The dish is prepared in a karahi (a type of wok) and can take between 30 and 50 minutes to prepare and cook the dish. Cumin, green chilis, ginger, garlic, tomatoes and coriander are key to the flavor of the dish.",
                    "4",
                    R.drawable.mobile_cupcake)
            )
            val myPrefs = context?.getSharedPreferences(context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            if (myPrefs != null) {
                if (myPrefs.contains("recipes")) {
                    var savedRecipes = mutableListOf<Recipe>()
                    val recipesJson: String? = myPrefs.getString("recipes", null)
                    if (recipesJson != null) {
                        val type: Type = object : TypeToken<List<Recipe?>?>() {}.type
                        savedRecipes = Gson().fromJson(recipesJson, type);
                    }
                    recipes.addAll(savedRecipes)
                }
            }
            return recipes;
        }

        fun saveRecipe(context: Context?, recipe: Recipe) {
            val myPrefs = context?.getSharedPreferences(context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            var recipes = mutableListOf(recipe)
            if (myPrefs != null) {
                val editor = myPrefs.edit()
                if (myPrefs.contains("recipes")) {
                    var savedRecipes = mutableListOf<Recipe>()
                    val recipesJson: String? = myPrefs.getString("recipes", null)
                    if (recipesJson != null) {
                        val type: Type = object : TypeToken<List<Recipe?>?>() {}.type
                        savedRecipes = Gson().fromJson(recipesJson, type);
                    }
                    recipes.addAll(savedRecipes)
                }
                editor.putString("recipes", Gson().toJson(recipes))
                editor.apply()
            }
        }

        fun getSavedBlogsIfAny(context: Context?): List<Blog> {
            val blogs = mutableListOf(
                Blog("Biryani",
                    "Biryani is a delicious rice dish peppered with scrumptious spices like saffron and cumin and further layered with spiced meat or veggie protein. Typically, this layered rice dish is enriched with added yogurt or ghee, a clarified butter, for an extra boost of luxurious flavor.",),
                Blog("Karahi",
                    "The dish is prepared in a karahi (a type of wok) and can take between 30 and 50 minutes to prepare and cook the dish. Cumin, green chilis, ginger, garlic, tomatoes and coriander are key to the flavor of the dish.",)
            )
            val myPrefs = context?.getSharedPreferences(context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            if (myPrefs != null) {
                if (myPrefs.contains("blogs")) {
                    var savedBlogs = mutableListOf<Blog>()
                    val blogsJson: String? = myPrefs.getString("blogs", null)
                    if (blogsJson != null) {
                        val type: Type = object : TypeToken<List<Blog?>?>() {}.type
                        savedBlogs = Gson().fromJson(blogsJson, type);
                    }
                    Log.d("blogs", savedBlogs.toString())
                    blogs.addAll(savedBlogs)
                }
            }
            return blogs;
        }

        fun saveBlog(context: Context?, blog: Blog) {
            val myPrefs = context?.getSharedPreferences(context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            var blogs = mutableListOf(blog)
            if (myPrefs != null) {
                val editor = myPrefs.edit()
                if (myPrefs.contains("blogs")) {
                    var savedBlog = mutableListOf<Blog>()
                    val blogJson: String? = myPrefs.getString("blogs", null)
                    if (blogJson != null) {
                        val type: Type = object : TypeToken<List<Blog?>?>() {}.type
                        savedBlog = Gson().fromJson(blogJson, type);
                    }
                    blogs.addAll(savedBlog)
                }
                editor.putString("blogs", Gson().toJson(blogs))
                editor.apply()
            }
        }

        fun getAboutMeIfAny(context: Context?):String {
            val myPrefs = context?.getSharedPreferences(context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            if (myPrefs != null) {
                return myPrefs.getString("aboutme", "")?: ""
            }
            return ""
        }

        fun saveAboutMe(context: Context?, aboutme: String) {
            val myPrefs = context?.getSharedPreferences(context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            if (myPrefs != null) {
                val editor = myPrefs.edit()
                editor.putString("aboutme", aboutme)
                editor.apply()
            }
        }

        fun getSavedMealPlanIfAny(context: Context?): Map<String, Recipe> {
            val meals = mutableMapOf<String, Recipe>()
            val recipes = getSavedRecipesIfAny(context)
            val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
            days.forEach {
                meals[it] = recipes.random()
            }
            val myPrefs = context?.getSharedPreferences(context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            if (myPrefs != null) {
                if (myPrefs.contains("mealPlan")) {
                    var savedMealPlan = mutableListOf<Meal>()
                    val mealJson: String? = myPrefs.getString("mealPlan", null)
                    if (mealJson != null) {
                        val type: Type = object : TypeToken<List<Meal?>?>() {}.type
                        savedMealPlan = Gson().fromJson(mealJson, type);
                    }
                    savedMealPlan.forEach{
                        meals[it.day] = it.recipe
                    }
                }
                val editor = myPrefs.edit()
                val mealPlan = meals.map{
                    Meal(it.key, it.value)
                }
                editor.putString("mealPlan", Gson().toJson(mealPlan))
                editor.apply()
            }
            return meals;
        }

        fun saveMeal(context: Context?, meal: Meal) {
            val meals = getSavedMealPlanIfAny(context).toMutableMap()
            if (meals.containsKey(meal.day)) {
                meals[meal.day] = meal.recipe
            }
            val mealPlan = meals.map{
                Meal(it.key, it.value)
            }
            val myPrefs = context?.getSharedPreferences(context.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
            if (myPrefs != null) {
                val editor = myPrefs.edit()
                editor.putString("mealPlan", Gson().toJson(mealPlan))
                editor.apply()
            }
        }

        fun getDayOfWeek(date: LocalDate): String {
            val formatter = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault())
            return date.format(formatter)
        }
    }
}