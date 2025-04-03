package app.delish.data.network


import com.elbehiry.delish.BuildConfig
import app.delish.data.models.IngredientSubstitutesResponse
import app.delish.data.models.RecipeSearchResponse
import app.delish.data.models.RecipeDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularApiService {

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("diet") diet: String? = null,
        @Query("intolerances") intolerances: String? = null,
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY
    ): RecipeSearchResponse

    @GET("recipes/{id}/information")
    suspend fun getRecipeDetails(
        @Path("id") recipeId: Int,
        @Query("includeNutrition") includeNutrition: Boolean = true,
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY
    ): RecipeDetailsResponse

    @GET("food/ingredients/substitutes")
    suspend fun getIngredientSubstitutes(
        @Query("ingredientName") ingredient: String,
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY
    ): IngredientSubstitutesResponse
}

