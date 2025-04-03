package app.delish.data.recipes.remote.models

data class RecipeDetailsResponse(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String,
    val instructions: String,
    val ingredients: List<Ingredient>
)

data class Ingredient(
    val id: Int,
    val name: String,
    val amount: Double,
    val unit: String
)
