package ru.naves.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.naves.app.data.Recipe
import ru.naves.app.data.recipes
import ru.naves.app.ui.theme.NavesColors

private val comingSoon = listOf("Плов", "Пельмени", "Хачапури")

@Composable
fun DishListScreen(onSelect: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(NavesColors.appBg)
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(top = 40.dp, bottom = 40.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(bottom = 32.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Icon(
                        Icons.Filled.RestaurantMenu,
                        contentDescription = null,
                        tint = NavesColors.tomato,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        "I HAVE MEAT",
                        color = NavesColors.dim,
                        style = MaterialTheme.typography.labelSmall,
                        letterSpacing = 2.sp
                    )
                }
                Text(
                    text = "Что готовим\nсегодня?",
                    color = NavesColors.text,
                    style = MaterialTheme.typography.displayMedium,
                    lineHeight = 38.sp
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Укажите количество фарша — мы рассчитаем идеальные пропорции остальных ингредиентов.",
                    color = NavesColors.dim,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp
                )
            }
        }

        items(recipes) { recipe ->
            DishCard(recipe = recipe, onClick = { onSelect(recipe.id) })
            Spacer(Modifier.height(16.dp))
        }

        item {
            Spacer(Modifier.height(24.dp))
            Text(
                "СКОРО В МЕНЮ",
                color = NavesColors.dim,
                style = MaterialTheme.typography.labelMedium,
                letterSpacing = 1.5.sp,
                modifier = Modifier.padding(bottom = 16.dp, start = 4.dp)
            )
        }

        items(comingSoon) { name ->
            ComingSoonCard(name)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun DishCard(recipe: Recipe, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(NavesColors.bg)
            .border(1.dp, NavesColors.line, RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(NavesColors.tomatoDim),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.LunchDining,
                contentDescription = null,
                tint = NavesColors.tomato,
                modifier = Modifier.size(26.dp)
            )
        }
        Spacer(Modifier.width(20.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = recipe.name,
                color = NavesColors.text,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = recipe.subtitle,
                color = NavesColors.dim,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Schedule,
                    contentDescription = null,
                    tint = NavesColors.basil,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    recipe.time,
                    color = NavesColors.dim,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(Modifier.width(16.dp))
                Icon(
                    Icons.Filled.Groups,
                    contentDescription = null,
                    tint = NavesColors.basil,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    "~${recipe.baseServings} шт",
                    color = NavesColors.dim,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Composable
private fun ComingSoonCard(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(NavesColors.surface2.copy(alpha = 0.5f))
            .border(1.dp, NavesColors.line.copy(alpha = 0.8f), RoundedCornerShape(20.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(NavesColors.appBg.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.Lock,
                contentDescription = null,
                tint = NavesColors.dim.copy(alpha = 0.5f),
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(Modifier.width(16.dp))
        Text(
            text = name,
            color = NavesColors.dim.copy(alpha = 0.7f),
            style = MaterialTheme.typography.titleMedium
        )
    }
}
