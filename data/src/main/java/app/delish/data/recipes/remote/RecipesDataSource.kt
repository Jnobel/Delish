package app.delish.data.recipes.remote

import com.elbehiry.model.Recipe
import com.elbehiry.model.Recipes
import com.elbehiry.model.SearchItem

interface RecipesDataSource {
    suspend fun searchRecipes(
        query: String?,
        cuisine: String?,
        offset: Int
    ): SearchItem

    suspend fun getRecipeInformation(
        id: Int?
    ): Recipe

    suspend fun getRandomRecipes(
        tags: String?,
        number: Int?
    ): Recipes

    abstract fun fetchRecipes(query: String)
}
