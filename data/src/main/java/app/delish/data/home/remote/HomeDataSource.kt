package app.delish.data.home.remote

import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val apiService: ApiService // Assuming API service exists
) {

    suspend fun getAvailableCuisines(): List<CuisineItem> {
        return apiService.getCuisines() // Replace with actual API call
    }

    suspend fun getIngredients(): List<IngredientItem> {
        return apiService.getIngredients() // Replace with actual API call
    }
}