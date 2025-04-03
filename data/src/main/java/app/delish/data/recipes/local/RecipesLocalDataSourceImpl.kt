package app.delish.data.recipes.local

import androidx.paging.PagingSource
import app.delish.data.db.recipes.dao.RecipesDao
import app.delish.data.db.recipes.entities.RecipeEntity
import app.delish.data.db.recipes.mapper.toRecipeEntity
import app.delish.data.db.recipes.mapper.toRecipesItem
import app.delish.result.Result
import com.elbehiry.model.RecipesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipesLocalDataSourceImpl @Inject constructor(
    private val recipesTable: RecipesDao
) : RecipesLocalDataSource {

    override suspend fun saveRecipe(recipe: RecipesItem) {
        recipesTable.saveRecipe(recipe.toRecipeEntity())
    }

    override fun getRecipesPagingSource(): PagingSource<Int, RecipeEntity> {
        return recipesTable.getPagedRecipes()
    }

    private fun Flow<List<RecipeEntity>>.toListDataRecipeFlow(): Flow<Result<List<RecipesItem>>> {
        return this.map { items ->
            Result.Success(items.toDataRecipes())
        }
    }

    private fun List<RecipeEntity>.toDataRecipes(): List<RecipesItem> {
        return this.map { it.toRecipesItem() }
    }

    override suspend fun getRecipeById(recipeId: Int?): RecipesItem? {
        return recipeId?.let {
            recipesTable.getRecipe(it)?.toRecipesItem()
        }
    }

    override suspend fun deleteRecipe(recipeId: Int) {
        recipesTable.deleteRecipe(recipeId)
    }

    override suspend fun isRecipeSaved(recipeId: Int): Boolean {
        return recipesTable.isRecipeSaved(recipeId)
    }
}