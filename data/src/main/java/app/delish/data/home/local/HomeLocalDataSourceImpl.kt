package app.delish.data.home.local

import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeLocalDataSourceImpl @Inject constructor(
    private val database: AppDatabase // Replace with actual database class
) : HomeLocalDataSource {

    override suspend fun saveCuisines(cuisines: List<CuisineItem>) {
        database.cuisineDao().insertCuisines(cuisines) // Replace with actual DAO method
    }

    override suspend fun getCuisines(): List<CuisineItem> {
        return database.cuisineDao().getAllCuisines() // Replace with actual DAO method
    }

    override suspend fun getIngredients(): List<IngredientItem> {
        return database.ingredientDao().getAllIngredients() // Replace with actual DAO method
    }

    override suspend fun saveIngredients(ingredients: List<IngredientItem>) {
        database.ingredientDao().insertIngredients(ingredients) // Replace with actual DAO method
    }
}