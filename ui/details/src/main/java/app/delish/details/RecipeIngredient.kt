package app.delish.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.delish.details.R

@Composable
fun RecipeIngredientTitle() {
    Text(
        text = stringResource(id = R.string.ingredients),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        color = Color.White
    )
}

@Composable
fun RecipeIngredientItem(ingredientTitle: String, onIngredientClick: (String) -> Unit) {
    Text(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clickable { onIngredientClick(ingredientTitle) },
        text = "✓  $ingredientTitle",
        style = MaterialTheme.typography.subtitle2,
        color = MaterialTheme.colors.primary
    )
}
