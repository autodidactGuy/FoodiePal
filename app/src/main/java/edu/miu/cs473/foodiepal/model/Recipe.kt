package edu.miu.cs473.foodiepal.model

import edu.miu.cs473.foodiepal.R

data class Recipe(val title: String, val cookingTime: String, val description: String, val stars: String, val recipeImage: Int = R.drawable.baseline_fastfood_24)