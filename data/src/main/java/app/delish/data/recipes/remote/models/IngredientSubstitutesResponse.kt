package app.delish.data.recipes.remote.models

data class IngredientSubstitutesResponse(
    val ingredient: String,
    val substitutes: List<String>?
)
