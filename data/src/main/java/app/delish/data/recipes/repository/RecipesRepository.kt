package app.delish.data.recipes.repository

import androidx.paging.PagingData
import app.delish.data.db.recipes.entities.RecipeEntity
import app.delish.data.models.RecipeSearchResponse
import com.elbehiry.model.Recipe
import com.elbehiry.model.RecipesItem
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    fun searchRecipes(
        query: String?,
        cuisine: String?
    ): Flow<PagingData<RecipesItem>>

    suspend fun getRecipeInformation(id: Int?): RecipesItem?

    suspend fun getRandomRecipes(tags: String?, number: Int?): List<Recipe>

    fun getRecipes(): Flow<PagingData<RecipeEntity>>

    suspend fun deleteRecipe(recipeId: Int?)

    suspend fun isRecipeSaved(recipeId: Int?): Boolean

    suspend fun saveRecipe(recipe: RecipesItem)

    // API call to fetch recipes from Spoonacular API
    suspend fun fetchRecipesFromApi(query: String): RecipeSearchResponse
}