package app.delish.data.home.repository

import app.delish.data.home.local.HomeLocalDataSource
import app.delish.data.home.remote.HomeDataSource
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource,
    private val homeLocalDataSource: HomeLocalDataSource
) : HomeRepository {
    // Implement repository functions

    override suspend fun getCuisines(): List<CuisineItem> = withContext(Dispatchers.IO) {
        val savedCuisines = homeLocalDataSource.getCuisines()
        if (savedCuisines.isNotEmpty()) {
            savedCuisines
        } else {
            homeDataSource.getAvailableCuisines().also {
                homeLocalDataSource.saveCuisines(it)
            }
        }
    }

    override suspend fun getIngredients(): List<IngredientItem> = withContext(Dispatchers.IO) {
        val savedIngredients = homeLocalDataSource.getIngredients()
        if (savedIngredients.isNotEmpty()) {
            savedIngredients
        } else {
            homeDataSource.getIngredients().also {
                homeLocalDataSource.saveIngredients(it)
            }
        }
    }
}
