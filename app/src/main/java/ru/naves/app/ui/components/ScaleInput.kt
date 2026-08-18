package ru.naves.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.naves.app.ui.theme.NavesColors

@Composable
fun ScaleInput(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    min: Int = 100,
    max: Int = 3000,
    step: Int = 50
) {
    fun clamp(v: Int) = v.coerceIn(min, max)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(NavesColors.bg)
            .border(1.dp, NavesColors.line, RoundedCornerShape(28.dp))
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                Icons.Filled.MonitorWeight,
                contentDescription = null,
                tint = NavesColors.basil,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "СКОЛЬКО У ВАС МЯСА",
                color = NavesColors.dim,
                style = MaterialTheme.typography.labelMedium,
                letterSpacing = 1.2.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(
                onClick = { onValueChange(clamp(value - step)) },
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(NavesColors.surface2)
                    .border(1.dp, NavesColors.line, CircleShape)
            ) {
                Icon(
                    Icons.Filled.Remove,
                    contentDescription = "Уменьшить",
                    tint = NavesColors.tomato,
                    modifier = Modifier.size(24.dp)
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(72.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(NavesColors.appBg)
                    .border(1.dp, NavesColors.line, RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "$value",
                        color = NavesColors.tomato,
                        style = MaterialTheme.typography.displayMedium.copy(fontSize = 42.sp),
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "г",
                        color = NavesColors.dim,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            IconButton(
                onClick = { onValueChange(clamp(value + step)) },
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(NavesColors.surface2)
                    .border(1.dp, NavesColors.line, CircleShape)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Увеличить",
                    tint = NavesColors.tomato,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
