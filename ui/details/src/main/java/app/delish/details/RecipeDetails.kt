package app.delish.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.delish.details.vm.DetailsViewModel
import app.delish.details.vm.ViewEvent
import app.delish.compose.view.CircularLoading
import app.delish.discover.sheet.IngredientsViewModel
import app.delish.data.models.IngredientSubstitutesResponse

@Composable
fun DetailsScreen(
    navController: NavController,
    recipeId: Int
) {
    DetailsScreen(
        viewModel = hiltViewModel(),
        ingredientsViewModel = hiltViewModel(),
        navController = navController,
        recipeId = recipeId
    )
}

@Composable
internal fun DetailsScreen(
    viewModel: DetailsViewModel,
    ingredientsViewModel: IngredientsViewModel,
    navController: NavController,
    recipeId: Int
) {

    val viewState by viewModel.states.collectAsStateWithLifecycle()
    val ingredientSubstitutes by ingredientsViewModel.ingredientSubstitutes.collectAsStateWithLifecycle()
    var selectedIngredient by remember { mutableStateOf<String?>(null) }

    // Fetch recipe details from API
    viewModel.fetchRecipeDetails(recipeId)

    CircularLoading(
        isLoading = viewState.isLoading
    ) {
        viewState.recipe?.let { recipe ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .background(color = MaterialTheme.colors.background)
            ) {
                item { RecipesHeader(recipe, navController) }
                item {
                    RecipeOptions(recipe) { recipe ->
                        recipe.saved = !recipe.saved
                        viewModel.processEvent(ViewEvent.ToggleBookMark(recipe))
                    }
                }
                item { RecipeDivider() }
                item { RecipeSummary(recipe) }
                item { RecipeDivider() }
                item { RecipeTags(recipe) }
                item { RecipeCaloric(recipe) }
                item { RecipeDivider() }
                item { RecipeIngredientTitle() }
                items(recipe.ingredientOriginalString ?: listOf()) { ingredient ->
                    Text(
                        text = ingredient,
                        modifier = Modifier.clickable {
                            selectedIngredient = ingredient
                            ingredientsViewModel.fetchIngredientSubstitutes(ingredient)
                        },
                        color = MaterialTheme.colors.primary
                    )
                }
                item { RecipeDivider() }

                // Display Ingredient Substitutes
                selectedIngredient?.let { ingredient ->
                    ingredientSubstitutes?.let { substitutes ->
                        item {
                            Text(text = "Substitutes for $ingredient:", style = MaterialTheme.typography.h6)
                        }
                        items(substitutes.substitutes ?: listOf()) { substitute ->
                            Text(text = substitute, modifier = Modifier.padding(start = 16.dp))
                        }
                        item { RecipeDivider() }
                    }
                }

                item { Spacer(modifier = Modifier.height(30.dp)) }
            }
        }
    }
}
