package edu.miu.cs473.foodiepal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cs473.foodiepal.R
import edu.miu.cs473.foodiepal.model.Meal
import edu.miu.cs473.foodiepal.model.Recipe

class MealListAdapter(private val meals: List<Meal>): RecyclerView.Adapter<MealListAdapter.MyViewHolder>() {
    var onItemClick: ((Meal) -> Unit)? = null
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mealDay: TextView = itemView.findViewById(R.id.mealDay)
        val mealRecipe: TextView = itemView.findViewById(R.id.mealRecipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_meal_plan, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return meals.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mealRecipe.text = meals[position].recipe
        holder.mealDay.text = meals[position].day
    }
}