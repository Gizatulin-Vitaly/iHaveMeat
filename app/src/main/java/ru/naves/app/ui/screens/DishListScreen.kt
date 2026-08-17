package ru.naves.app.ui.screens

import android.content.Intent
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
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.naves.app.UpdateManager
import ru.naves.app.data.Recipe
import ru.naves.app.data.recipes
import ru.naves.app.ui.theme.NavesColors

data class UpdateInfo(val versionName: String, val apkUrl: String)

@Composable
fun DishListScreen(onSelect: (String) -> Unit) {
    val context = LocalContext.current
    val updateManager = remember { UpdateManager(context) }
    var updateInfo by remember { mutableStateOf<UpdateInfo?>(null) }
    var isChecking by remember { mutableStateOf(false) }

    if (updateInfo != null) {
        AlertDialog(
            onDismissRequest = { updateInfo = null },
            title = { Text("Обновление", color = NavesColors.text) },
            text = { 
                Text(
                    "Доступна новая версия ${updateInfo?.versionName}. Рекомендуем обновиться для получения новых рецептов и функций.",
                    color = NavesColors.dim
                ) 
            },
            confirmButton = {
                TextButton(onClick = {
                    updateManager.downloadAndInstall(updateInfo!!.apkUrl)
                    updateInfo = null
                }) {
                    Text("ОБНОВИТЬ", color = NavesColors.tomato, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { updateInfo = null }) {
                    Text("ПОЗЖЕ", color = NavesColors.dim)
                }
            },
            containerColor = NavesColors.surface2,
            shape = RoundedCornerShape(24.dp)
        )
    }

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
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
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

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {
                                val sendIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "Скачай приложение I Have Meat для идеальных пропорций в готовке! Прямая ссылка на APK: https://github.com/Gizatulin-Vitaly/iHaveMeat/releases/latest/download/app-debug.apk")
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(sendIntent, null)
                                context.startActivity(shareIntent)
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                Icons.Filled.Share,
                                contentDescription = "Поделиться",
                                tint = NavesColors.dim,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(Modifier.width(8.dp))
                        if (isChecking) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = NavesColors.tomato,
                                strokeWidth = 2.dp
                            )
                        } else {
                            IconButton(
                                onClick = {
                                    isChecking = true
                                    updateManager.checkForUpdates(
                                        repoPath = "Gizatulin-Vitaly/iHaveMeat", 
                                        currentVersionName = "1.0.5",
                                        onUpdateAvailable = { name, url ->
                                            isChecking = false
                                            updateInfo = UpdateInfo(name, url)
                                        },
                                        onNoUpdate = {
                                            isChecking = false
                                            android.widget.Toast.makeText(context, "У вас последняя версия", android.widget.Toast.LENGTH_SHORT).show()
                                        },
                                        onError = {
                                            isChecking = false
                                            android.widget.Toast.makeText(context, "Ошибка сети", android.widget.Toast.LENGTH_SHORT).show()
                                        }
                                    )
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    Icons.Filled.Update,
                                    contentDescription = "Проверить обновления",
                                    tint = NavesColors.dim,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
                Text(
                    text = "Что готовим\nсегодня?",
                    color = NavesColors.text,
                    style = MaterialTheme.typography.displayMedium,
                    lineHeight = 38.sp
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Укажите количество мяса — мы рассчитаем идеальные пропорции остальных ингредиентов.",
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Будут добавляться новые рецепты,\nне забывайте обновлять приложение\nкнопкой в правом верхнем углу!",
                    color = NavesColors.dim.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    lineHeight = 18.sp
                )
                Spacer(Modifier.height(16.dp))
                Icon(
                    Icons.Filled.Update,
                    contentDescription = null,
                    tint = NavesColors.dim.copy(alpha = 0.4f),
                    modifier = Modifier.size(24.dp)
                )
            }
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
