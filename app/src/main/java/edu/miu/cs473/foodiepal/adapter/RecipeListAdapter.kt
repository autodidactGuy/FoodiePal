package edu.miu.cs473.foodiepal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.miu.cs473.foodiepal.R
import edu.miu.cs473.foodiepal.model.Recipe

class RecipeListAdapter(private val recipes: List<Recipe>): RecyclerView.Adapter<RecipeListAdapter.MyViewHolder>() {
    var onItemClick: ((Recipe) -> Unit)? = null
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)
        val recipeCookingTime: TextView = itemView.findViewById(R.id.cookingTime)
        val recipeStars: RatingBar = itemView.findViewById(R.id.recipeStars)
        val recipeDesc: TextView = itemView.findViewById(R.id.recipeDesc)
        val recipeTitle: TextView = itemView.findViewById(R.id.recipeTitle)
        init{
            itemView.setOnClickListener {
                onItemClick?.invoke(recipes[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipes.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.recipeImage.setImageResource(recipes[position].recipeImage)
        holder.recipeTitle.text = recipes[position].title
        holder.recipeDesc.text = recipes[position].description
        holder.recipeStars.rating = recipes[position].stars.toFloat()
        holder.recipeCookingTime.text = recipes[position].cookingTime
    }
}