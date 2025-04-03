package app.delish.data.models

import com.elbehiry.model.RecipesItem

data class RecipeSearchResponse(
    val results: List<RecipesItem>,
    val totalResults: Int
)

data class Recipe(
    val id: Int,
    val title: String,
    val image: String
)
