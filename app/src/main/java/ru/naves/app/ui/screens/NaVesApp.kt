package ru.naves.app.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ru.naves.app.data.recipes

@Composable
fun NaVesApp() {
    var selectedId by remember { mutableStateOf<String?>(null) }
    val selected = recipes.find { it.id == selectedId }

    if (selected != null) {
        BackHandler { selectedId = null }
        RecipeDetailScreen(recipe = selected, onBack = { selectedId = null })
    } else {
        DishListScreen(onSelect = { selectedId = it })
    }
}
