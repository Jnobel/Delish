package app.delish.data.recipes.repository

import app.delish.data.models.RecipeSearchResponse
import app.delish.data.models.RecipeDetailsResponse
import app.delish.data.models.IngredientSubstitutesResponse
import app.delish.data.network.ApiClient

class SpoonacularRepository {

    suspend fun searchRecipes(query: String): RecipeSearchResponse {
        return ApiClient.api.searchRecipes(query)
    }

    suspend fun getRecipeDetails(recipeId: Int): RecipeDetailsResponse {
        return ApiClient.api.getRecipeDetails(recipeId)
    }

    suspend fun getIngredientSubstitutes(ingredient: String): IngredientSubstitutesResponse {
        return ApiClient.api.getIngredientSubstitutes(ingredient)
    }
}
