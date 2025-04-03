package app.delish.discover.sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.delish.domain.usecases.GetIngredientsUseCase
import app.delish.data.recipes.repository.SpoonacularRepository
import app.delish.result.data
import com.elbehiry.model.IngredientItem
import app.delish.data.models.IngredientSubstitutesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class IngredientsViewModel @Inject constructor(
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val spoonacularRepository: SpoonacularRepository
): ViewModel(){

    private val _currentIngredients = MutableStateFlow<List<IngredientItem>>(emptyList())
    val currentIngredients: StateFlow<List<IngredientItem>> = _currentIngredients

    private val _ingredientSubstitutes = MutableStateFlow<IngredientSubstitutesResponse?>(null)
    val ingredientSubstitutes: StateFlow<IngredientSubstitutesResponse?> = _ingredientSubstitutes

    init {
        viewModelScope.launch {
            _currentIngredients.emit(getIngredientsUseCase(Unit).data?.shuffled() ?: emptyList())
        }
    }

    fun fetchIngredientSubstitutes(ingredientName: String) {
        viewModelScope.launch {
            try {
                _ingredientSubstitutes.value = spoonacularRepository.getIngredientSubstitutes(ingredientName)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}