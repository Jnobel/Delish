package app.delish.data.recipes.remote

import app.delish.data.network.ApiClient
import app.delish.data.remote.DelishApi
import app.delish.data.models.RecipeSearchResponse
import com.elbehiry.model.Recipe
import com.elbehiry.model.Recipes
import com.elbehiry.model.SearchItem
import javax.inject.Inject

const val DEFAULT_NUMBER = 20
const val DEFAULT_RECIPE_INFORMATION = false

class RecipesRemoteDataSource @Inject constructor(
    private val api: DelishApi,
    private val spoonacularKey: String
) : RecipesDataSource {

    override suspend fun getRecipeInformation(id: Int?): Recipe {
        requireNotNull(id) { "Recipe ID cannot be null" }
        return api.getRecipeInformation(id = id, apiKey = spoonacularKey)
    }

    override suspend fun searchRecipes(
        query: String?,
        cuisine: String?,
        offset: Int
    ): SearchItem {
        return api.searchRecipes(
            query = query,
            cuisine = cuisine,
            addRecipeInformation = DEFAULT_RECIPE_INFORMATION,
            number = DEFAULT_NUMBER,
            offset = offset,
            apiKey = spoonacularKey
        )
    }

    override suspend fun getRandomRecipes(
        tags: String?,
        number: Int?
    ): Recipes {
        return api.getRandomRecipes(tags = tags, number = number ?: DEFAULT_NUMBER, apiKey = spoonacularKey)
    }

    // Added Spoonacular API integration
    suspend fun fetchRecipesFromApi(query: String): RecipeSearchResponse {
        return ApiClient.api.searchRecipes(query)
    }
}
