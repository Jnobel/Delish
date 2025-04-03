package app.delish.data.recipes.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.delish.data.db.recipes.entities.RecipeEntity
import app.delish.data.models.RecipeSearchResponse
import app.delish.data.recipes.local.RecipesLocalDataSource
import app.delish.data.recipes.remote.RecipesDataSource
import app.delish.data.recipes.source.SearchSource
import com.elbehiry.model.Recipe
import com.elbehiry.model.RecipesItem
import com.elbehiry.model.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesRepositoryImpl @Inject constructor(
    private val recipesRemoteDataSource: RecipesDataSource,
    private val recipesLocalDataSource: RecipesLocalDataSource
) : RecipesRepository {

    override fun searchRecipes(
        query: String?,
        cuisine: String?
    ): Flow<PagingData<RecipesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchSource(recipesRemoteDataSource, query, cuisine)
            }
        ).flow
    }

    override suspend fun getRecipeInformation(id: Int?): RecipesItem = withContext(Dispatchers.IO) {
        requireNotNull(id) { "Recipe ID must not be null" }
        recipesLocalDataSource.getRecipeById(id)
            ?: recipesRemoteDataSource.getRecipeInformation(id).toUiModel()
    }

    override suspend fun getRandomRecipes(tags: String?, number: Int?): List<Recipe> =
        withContext(Dispatchers.IO) {
            recipesRemoteDataSource.getRandomRecipes(tags, number).recipes
        }

    override fun getRecipes(): Flow<PagingData<RecipeEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                recipesLocalDataSource.getRecipesPagingSource()
            }
        ).flow
    }

    override suspend fun deleteRecipe(recipeId: Int?) = withContext(Dispatchers.IO) {
        recipeId?.let { recipesLocalDataSource.deleteRecipe(it) }
    }

    override suspend fun isRecipeSaved(recipeId: Int?): Boolean = withContext(Dispatchers.IO) {
        recipeId?.let { recipesLocalDataSource.isRecipeSaved(it) } ?: false
    }

    override suspend fun saveRecipe(recipe: RecipesItem) = withContext(Dispatchers.IO) {
        recipesLocalDataSource.saveRecipe(recipe)
    }

    override suspend fun fetchRecipesFromApi(query: String): Unit =
        withContext(Dispatchers.IO) {
            recipesRemoteDataSource.fetchRecipes(query)
        }
}
