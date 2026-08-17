package ru.naves.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.naves.app.data.IngredientGroup
import ru.naves.app.data.Recipe
import ru.naves.app.ui.components.ScaleInput
import ru.naves.app.ui.theme.NavesColors
import kotlin.math.roundToInt

private fun scaleAmount(base: Double, factor: Double): Double {
    val raw = base * factor
    return when {
        raw < 10 -> (raw * 2).roundToInt() / 2.0
        raw < 100 -> (raw / 5).roundToInt() * 5.0
        else -> (raw / 10).roundToInt() * 10.0
    }
}

private fun fmt(n: Double): String =
    if (n == n.toInt().toDouble()) n.toInt().toString() else "%.1f".format(n)

@Composable
fun RecipeDetailScreen(recipe: Recipe, onBack: () -> Unit) {
    var mince by remember(recipe.id) { mutableStateOf(recipe.baseMince.toInt()) }
    var done by remember(recipe.id) { mutableStateOf(setOf<Int>()) }

    val factor = mince / recipe.baseMince
    val servings = maxOf(1, (recipe.baseServings * factor).roundToInt())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(NavesColors.appBg)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NavesColors.bg)
                    .statusBarsPadding()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(NavesColors.appBg)
                        .border(1.dp, NavesColors.line, CircleShape)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Назад",
                        tint = NavesColors.text,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(Modifier.height(20.dp))
                Text(
                    recipe.name,
                    color = NavesColors.text,
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 32.sp),
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Schedule,
                        contentDescription = null,
                        tint = NavesColors.basil,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        recipe.time,
                        color = NavesColors.dim,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(Modifier.width(20.dp))
                    Icon(
                        Icons.Filled.Groups,
                        contentDescription = null,
                        tint = NavesColors.basil,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        "~$servings шт",
                        color = NavesColors.dim,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }

        item {
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Spacer(Modifier.height(24.dp))
                ScaleInput(value = mince, onValueChange = { mince = it })
                Spacer(Modifier.height(32.dp))
                Text(
                    "ИНГРЕДИЕНТЫ",
                    color = NavesColors.dim,
                    style = MaterialTheme.typography.labelMedium,
                    letterSpacing = 2.sp
                )
                Spacer(Modifier.height(16.dp))
            }
        }

        items(recipe.groups) { group ->
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                IngredientGroupCard(group = group, factor = factor)
                Spacer(Modifier.height(16.dp))
            }
        }

        item {
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "ПРИГОТОВЛЕНИЕ",
                        color = NavesColors.dim,
                        style = MaterialTheme.typography.labelMedium,
                        letterSpacing = 2.sp
                    )
                    Text(
                        "${done.size} из ${recipe.steps.size}",
                        color = NavesColors.basil,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Spacer(Modifier.height(16.dp))
            }
        }

        itemsIndexed(recipe.steps) { index, step ->
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                StepCard(
                    index = index,
                    title = step.title,
                    text = step.text,
                    isDone = done.contains(index),
                    onToggle = {
                        done = if (done.contains(index)) done - index else done + index
                    }
                )
                Spacer(Modifier.height(12.dp))
            }
        }

        item { Spacer(Modifier.height(40.dp)) }
    }
}

@Composable
private fun IngredientGroupCard(group: IngredientGroup, factor: Double) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(NavesColors.bg)
            .border(1.dp, NavesColors.line, RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                group.icon,
                contentDescription = null,
                tint = NavesColors.basil,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                group.label,
                color = NavesColors.basil,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.height(16.dp))
        group.items.forEachIndexed { index, ingredient ->
            val amount = if (ingredient.fixed) ingredient.base else scaleAmount(ingredient.base, factor)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        ingredient.name,
                        color = NavesColors.text,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    if (ingredient.note != null) {
                        Text(
                            ingredient.note,
                            color = NavesColors.dim,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "${fmt(amount)} ${ingredient.unit}",
                    color = if (ingredient.isBase) NavesColors.tomato else NavesColors.text,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }
            if (index < group.items.size - 1) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = NavesColors.line.copy(alpha = 0.5f),
                    thickness = 0.5.dp
                )
            }
        }
    }
}

@Composable
private fun StepCard(
    index: Int,
    title: String,
    text: String,
    isDone: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(if (isDone) NavesColors.basilDim.copy(alpha = 0.5f) else NavesColors.bg)
            .border(1.dp, if (isDone) NavesColors.basil.copy(alpha = 0.3f) else NavesColors.line, RoundedCornerShape(20.dp))
            .clickable(onClick = onToggle)
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(if (isDone) NavesColors.basil else NavesColors.surface2)
                .border(1.dp, NavesColors.line, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (isDone) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            } else {
                Text(
                    "${index + 1}",
                    color = NavesColors.tomato,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                color = if (isDone) NavesColors.basil else NavesColors.text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textDecoration = if (isDone) TextDecoration.LineThrough else TextDecoration.None
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = text,
                color = NavesColors.dim,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )
        }
    }
}
