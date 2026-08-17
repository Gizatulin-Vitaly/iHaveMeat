package ru.naves.app.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.DinnerDining
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
    ),
    Recipe(
        id = "beshbarmak",
        name = "Бешбармак из говядины",
        subtitle = "Традиционное блюдо с домашней лапшой",
        time = "120 мин",
        baseServings = 6,
        baseMince = 1000.0,
        groups = listOf(
            IngredientGroup(
                id = "meat_broth",
                label = "Мясо и бульон",
                icon = Icons.Filled.LunchDining,
                color = NavesColors.tomato,
                items = listOf(
                    Ingredient(id = "beef_besh", name = "Говядина (на кости)", base = 1000.0, unit = "г", isBase = true),
                    Ingredient(id = "onion_broth", name = "Лук (в бульон)", base = 100.0, unit = "г"),
                    Ingredient(id = "bay_leaf", name = "Лавровый лист", base = 2.0, unit = "шт", fixed = true),
                    Ingredient(id = "pepper_besh", name = "Черный перец горошком", base = 5.0, unit = "шт", fixed = true),
                    Ingredient(id = "salt_besh", name = "Соль", base = 15.0, unit = "г")
                )
            ),
            IngredientGroup(
                id = "dough_besh",
                label = "Тесто (сочни)",
                icon = Icons.Filled.BakeryDining,
                color = NavesColors.basil,
                items = listOf(
                    Ingredient(id = "flour_besh", name = "Мука пшеничная", base = 400.0, unit = "г"),
                    Ingredient(id = "egg_besh", name = "Яйцо", base = 1.0, unit = "шт", fixed = true),
                    Ingredient(id = "water_besh", name = "Бульон или вода", base = 100.0, unit = "мл")
                )
            ),
            IngredientGroup(
                id = "uzuk",
                label = "Туздык (соус)",
                icon = Icons.Filled.DinnerDining,
                color = NavesColors.honey,
                items = listOf(
                    Ingredient(id = "onion_main", name = "Лук репчатый", base = 300.0, unit = "г"),
                    Ingredient(id = "pepper_ground", name = "Черный перец молотый", base = 2.0, unit = "г"),
                    Ingredient(id = "greens", name = "Зелень (петрушка, укроп)", base = 20.0, unit = "г")
                )
            )
        ),
        steps = listOf(
            CookingStep(
                title = "Сварить мясо",
                text = "Мясо залить холодной водой, довести до кипения, тщательно снять пену. Добавить луковицу, перец горошком, лавровый лист. Варить на медленном огне 2–2.5 часа до мягкости мяса."
            ),
            CookingStep(
                title = "Замесить тесто",
                text = "Из муки, яйца, соли и остывшего бульона (или воды) замесить крутое тесто. Оставить отдохнуть на 30 минут."
            ),
            CookingStep(
                title = "Раскатать сочни",
                text = "Раскатать тесто в тонкий пласт (1 мм) и нарезать ромбами примерно 8x8 см. Дать им немного подсохнуть на столе."
            ),
            CookingStep(
                title = "Приготовить туздык",
                text = "Лук нарезать тонкими кольцами, выложить в небольшую кастрюлю, посыпать перцем. Залить жирным верхним слоем горячего бульона и потомить на огне 2–3 минуты (лук должен стать мягким, но сохранить форму)."
            ),
            CookingStep(
                title = "Сварить тесто",
                text = "Готовое мясо вынуть, нарезать крупными кусками. В кипящем бульоне порциями отварить ромбы теста (сочни) в течение 3–5 минут."
            ),
            CookingStep(
                title = "Собрать блюдо",
                text = "На большое подогретое блюдо выложить сваренное тесто, сверху распределить мясо. Полить всё горячим туздыком (луком с бульоном) и посыпать свежей зеленью."
            )
        )
    ),
    Recipe(
        id = "profiteroles",
        name = "Профитроли",
        subtitle = "Нежные пирожные со сливочным кремом",
        time = "60 мин",
        baseServings = 20,
        baseMince = 250.0, // Base is water amount for dough scaling
        groups = listOf(
            IngredientGroup(
                id = "choux_pastry",
                label = "Заварное тесто",
                icon = Icons.Filled.BakeryDining,
                color = NavesColors.honey,
                items = listOf(
                    Ingredient(id = "water_choux", name = "Вода", base = 250.0, unit = "мл", isBase = true),
                    Ingredient(id = "butter", name = "Сливочное масло", base = 100.0, unit = "г"),
                    Ingredient(id = "flour_choux", name = "Мука пшеничная", base = 150.0, unit = "г"),
                    Ingredient(id = "eggs", name = "Яйца", base = 4.0, unit = "шт", fixed = true),
                    Ingredient(id = "salt_choux", name = "Соль", base = 2.0, unit = "г")
                )
            ),
            IngredientGroup(
                id = "cream_filling",
                label = "Сливочный крем",
                icon = Icons.Filled.DinnerDining,
                color = NavesColors.basil,
                items = listOf(
                    Ingredient(id = "cream", name = "Сливки (33%)", base = 500.0, unit = "мл"),
                    Ingredient(id = "sugar_powder", name = "Сахарная пудра", base = 100.0, unit = "г"),
                    Ingredient(id = "vanilla", name = "Ванильный сахар", base = 10.0, unit = "г", fixed = true)
                )
            )
        ),
        steps = listOf(
            CookingStep(
                title = "Заварить тесто",
                text = "В сотейник влить воду, добавить масло и соль. Довести до кипения. Всыпать всю муку разом и интенсивно мешать лопаткой, пока тесто не соберется в ком и на дне не появится налет."
            ),
            CookingStep(
                title = "Добавить яйца",
                text = "Снять с огня, дать немного остыть. Вводить яйца по одному, каждый раз тщательно вымешивая до однородности. Тесто должно стать гладким и медленно стекать с лопатки."
            ),
            CookingStep(
                title = "Отсадить профитроли",
                text = "С помощью кондитерского мешка или ложки выложить небольшие шарики на противень с пергаментом на расстоянии 3-4 см друг от друга."
            ),
            CookingStep(
                title = "Выпечь",
                text = "Выпекать в разогретой до 200°C духовке 10-15 минут, затем снизить до 180°C и печь еще 15-20 минут до золотистого цвета. Не открывать духовку во время процесса!"
            ),
            CookingStep(
                title = "Приготовить крем",
                text = "Холодные сливки взбить с сахарной пудрой и ванилью до крепких пиков."
            ),
            CookingStep(
                title = "Начинить",
                text = "У остывших профитролей сделать небольшой надрез или прокол. С помощью кондитерского мешка наполнить их кремом."
            )
        )
    )
)
