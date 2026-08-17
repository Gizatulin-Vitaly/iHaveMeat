package ru.naves.app.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.RiceBowl
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.ui.graphics.vector.ImageVector
import ru.naves.app.ui.theme.NavesColors
import androidx.compose.ui.graphics.Color

data class Ingredient(
    val id: String,
    val name: String,
    val base: Double,
    val unit: String,
    val isBase: Boolean = false,
    val fixed: Boolean = false,
    val note: String? = null
)

data class IngredientGroup(
    val id: String,
    val label: String,
    val icon: ImageVector,
    val color: Color,
    val items: List<Ingredient>
)

data class CookingStep(
    val title: String,
    val text: String
)

data class Recipe(
    val id: String,
    val name: String,
    val subtitle: String,
    val time: String,
    val baseServings: Int,
    val baseMince: Double,
    val groups: List<IngredientGroup>,
    val steps: List<CookingStep>
)

// ---------------------------------------------------------------------------
// One dish implemented end to end. Add more recipes here later - the list
// screen and detail screen both work off this list without extra changes.
// ---------------------------------------------------------------------------
val recipes: List<Recipe> = listOf(
    Recipe(
        id = "cheburek",
        name = "Чебуреки",
        subtitle = "Хрустящее тесто, сочная начинка",
        time = "60 мин",
        baseServings = 16,
        baseMince = 500.0,
        groups = listOf(
            IngredientGroup(
                id = "dough",
                label = "Тесто",
                icon = Icons.Filled.BakeryDining,
                color = NavesColors.basil,
                items = listOf(
                    Ingredient(id = "flour", name = "Мука пшеничная", base = 400.0, unit = "г"),
                    Ingredient(id = "water", name = "Вода тёплая", base = 180.0, unit = "мл"),
                    Ingredient(id = "salt_dough", name = "Соль", base = 5.0, unit = "г"),
                    Ingredient(id = "oil_dough", name = "Масло растительное", base = 30.0, unit = "мл")
                )
            ),
            IngredientGroup(
                id = "filling",
                label = "Начинка",
                icon = Icons.Filled.LunchDining,
                color = NavesColors.tomato,
                items = listOf(
                    Ingredient(id = "mince", name = "Фарш говяжий/свиной", base = 500.0, unit = "г", isBase = true),
                    Ingredient(id = "onion", name = "Лук репчатый", base = 150.0, unit = "г"),
                    Ingredient(id = "water_ice", name = "Вода ледяная", base = 100.0, unit = "мл"),
                    Ingredient(id = "salt_filling", name = "Соль", base = 6.0, unit = "г"),
                    Ingredient(id = "pepper", name = "Перец чёрный молотый", base = 2.0, unit = "г"),
                    Ingredient(id = "garlic", name = "Чеснок", base = 10.0, unit = "г", note = "через пресс")
                )
            ),
            IngredientGroup(
                id = "fry",
                label = "Жарка",
                icon = Icons.Filled.LocalFireDepartment,
                color = NavesColors.honey,
                items = listOf(
                    Ingredient(
                        id = "oil_fry",
                        name = "Масло для фритюра",
                        base = 500.0,
                        unit = "мл",
                        fixed = true,
                        note = "слой ~2 см в сковороде"
                    )
                )
            )
        ),
        steps = listOf(
            CookingStep(
                title = "Замесить тесто",
                text = "Муку смешать с солью, влить тёплую воду с маслом. Замешивать 8–10 минут до гладкого эластичного теста."
            ),
            CookingStep(
                title = "Дать тесту отдохнуть",
                text = "Завернуть в плёнку, оставить на 30 минут при комнатной температуре — клейковина расслабится, тесто станет легче раскатывать."
            ),
            CookingStep(
                title = "Сделать начинку",
                text = "Лук мелко нарезать или пробить блендером, чеснок пропустить через пресс. Смешать с фаршем, посолить и поперчить. Вливать ледяную воду небольшими порциями, вымешивая — так начинка станет сочнее."
            ),
            CookingStep(
                title = "Раскатать тесто",
                text = "Разделить тесто на шарики по 40–50 г, каждый раскатать в тонкий круг толщиной 2–3 мм."
            ),
            CookingStep(
                title = "Слепить чебуреки",
                text = "На одну половину круга выложить 2–3 ст. л. начинки, накрыть второй половиной. Защипнуть край плотно, без пропусков — иначе сок вытечет при жарке."
            ),
            CookingStep(
                title = "Разогреть масло",
                text = "Разогреть масло до 180°C. Проверка: кусочек теста должен всплыть и зашипеть за 5–7 секунд."
            ),
            CookingStep(
                title = "Обжарить",
                text = "Жарить по 2–3 минуты с каждой стороны до золотистой корочки. Не более 2–3 штук за раз, чтобы температура масла не падала."
            ),
            CookingStep(
                title = "Подавать",
                text = "Выложить на бумажное полотенце, чтобы убрать лишний жир. Подавать горячими."
            )
        )
    ),
    Recipe(
        id = "plov",
        name = "Плов из говядины",
        subtitle = "Ароматный рис, нежное мясо и пряности",
        time = "90 мин",
        baseServings = 6,
        baseMince = 600.0,
        groups = listOf(
            IngredientGroup(
                id = "meat_veg",
                label = "Мясо и овощи",
                icon = Icons.Filled.LunchDining,
                color = NavesColors.tomato,
                items = listOf(
                    Ingredient(id = "beef", name = "Говядина (мякоть)", base = 600.0, unit = "г", isBase = true),
                    Ingredient(id = "carrots", name = "Морковь", base = 600.0, unit = "г", note = "соломкой"),
                    Ingredient(id = "onion_plov", name = "Лук репчатый", base = 200.0, unit = "г")
                )
            ),
            IngredientGroup(
                id = "rice_garlic",
                label = "Крупа и овощи в зирвак",
                icon = Icons.Filled.RiceBowl,
                color = NavesColors.basil,
                items = listOf(
                    Ingredient(id = "rice", name = "Рис (девзира или длиннозерный)", base = 600.0, unit = "г"),
                    Ingredient(id = "garlic_head", name = "Чеснок", base = 2.0, unit = "головки", fixed = true),
                    Ingredient(id = "chili", name = "Острый перец", base = 1.0, unit = "стручок", fixed = true)
                )
            ),
            IngredientGroup(
                id = "spices_oil",
                label = "Специи и масло",
                icon = Icons.Filled.SoupKitchen,
                color = NavesColors.honey,
                items = listOf(
                    Ingredient(id = "oil_plov", name = "Масло растительное", base = 150.0, unit = "мл"),
                    Ingredient(id = "zira", name = "Зира (кумин)", base = 5.0, unit = "г"),
                    Ingredient(id = "barberry", name = "Барбарис", base = 10.0, unit = "г"),
                    Ingredient(id = "turmeric", name = "Куркума", base = 3.0, unit = "г"),
                    Ingredient(id = "pepper_corns", name = "Черный перец горошком", base = 10.0, unit = "шт", fixed = true),
                    Ingredient(id = "salt_plov", name = "Соль", base = 15.0, unit = "г")
                )
            )
        ),
        steps = listOf(
            CookingStep(
                title = "Подготовить ингредиенты",
                text = "Мясо нарезать кубиками по 3 см, морковь — длинной соломкой, лук — полукольцами. Рис тщательно промыть до прозрачной воды."
            ),
            CookingStep(
                title = "Обжарить мясо",
                text = "В казане разогреть масло до легкого дымка. Обжарить мясо до румяной корочки на сильном огне."
            ),
            CookingStep(
                title = "Добавить овощи",
                text = "Добавить лук, жарить до золотистого цвета. Затем выложить морковь, жарить всё вместе 10–15 минут, пока морковь не станет мягкой."
            ),
            CookingStep(
                title = "Приготовить зирвак",
                text = "Влить горячую воду, чтобы она покрыла мясо с овощами. Добавить зиру, барбарис, куркуму, перец горошком и соль. Убавить огонь и томить зирвак 30–40 минут на медленном огне."
            ),
            CookingStep(
                title = "Закладка риса в зирвак",
                text = "Выложить рис ровным слоем поверх зирвака (не перемешивать!). В центр воткнуть целые головки чеснока и целый стручок перца (не повредите его!). Долить кипяток так, чтобы уровень воды был выше риса на 1.5–2 см."
            ),
            CookingStep(
                title = "Финальный этап",
                text = "Варить на сильном огне без крышки, пока вода не уйдет с поверхности риса в зирвак. Затем сделать отверстия в рисе до дна, накрыть крышкой и томить на минимальном огне 20–25 минут."
            ),
            CookingStep(
                title = "Настоять и подавать",
                text = "Выключить огонь, дать плову настояться под крышкой ещё 10–15 минут. Перед подачей аккуратно извлечь чеснок и перец, перемешать рис с зирваком (мясом и морковью)."
            )
        )
    )
)
