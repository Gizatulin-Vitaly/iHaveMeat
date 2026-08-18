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
        id = "pork_roast",
        name = "Жаркое из свинины",
        subtitle = "Сытное блюдо с картофелем и овощами",
        time = "70 мин",
        baseServings = 4,
        baseMince = 600.0,
        groups = listOf(
            IngredientGroup(
                id = "pork_main",
                label = "Мясо",
                icon = Icons.Filled.LunchDining,
                color = NavesColors.tomato,
                items = listOf(
                    Ingredient(id = "pork_meat", name = "Свинина (шея или лопатка)", base = 600.0, unit = "г", isBase = true),
                    Ingredient(id = "onion_roast", name = "Лук репчатый", base = 150.0, unit = "г"),
                    Ingredient(id = "oil_roast", name = "Масло растительное", base = 40.0, unit = "мл")
                )
            ),
            IngredientGroup(
                id = "veggies_roast",
                label = "Овощи",
                icon = Icons.Filled.SoupKitchen,
                color = NavesColors.basil,
                items = listOf(
                    Ingredient(id = "potatoes", name = "Картофель", base = 800.0, unit = "г"),
                    Ingredient(id = "carrot_roast", name = "Морковь", base = 150.0, unit = "г"),
                    Ingredient(id = "garlic_roast", name = "Чеснок", base = 3.0, unit = "зубчика", fixed = true)
                )
            ),
            IngredientGroup(
                id = "flavor_roast",
                label = "Вкус и аромат",
                icon = Icons.Filled.LocalFireDepartment,
                color = NavesColors.honey,
                items = listOf(
                    Ingredient(id = "water_roast", name = "Вода или бульон", base = 400.0, unit = "мл"),
                    Ingredient(id = "salt_roast", name = "Соль", base = 10.0, unit = "г"),
                    Ingredient(id = "pepper_roast", name = "Черный перец молотый", base = 2.0, unit = "г"),
                    Ingredient(id = "bay_roast", name = "Лавровый лист", base = 2.0, unit = "шт", fixed = true)
                )
            )
        ),
        steps = listOf(
            CookingStep(
                title = "Подготовить мясо",
                text = "Свинину нарезать средними кубиками (3-4 см). Лук нарезать полукольцами, морковь — кружочками или крупными кусками."
            ),
            CookingStep(
                title = "Обжарить мясо",
                text = "В глубокой сковороде или казане разогреть масло. Обжарить свинину до золотистой корочки на сильном огне."
            ),
            CookingStep(
                title = "Добавить овощи",
                text = "К мясу добавить лук и морковь. Жарить еще 7-10 минут, периодически помешивая, пока овощи не станут мягкими."
            ),
            CookingStep(
                title = "Подготовить картофель",
                text = "Картофель очистить и нарезать крупными дольками или кубиками."
            ),
            CookingStep(
                title = "Тушить",
                text = "Добавить картофель к мясу с овощами. Влить горячую воду (или бульон) так, чтобы она почти полностью покрывала содержимое. Добавить соль, перец и лавровый лист."
            ),
            CookingStep(
                title = "Томить",
                text = "Накрыть крышкой и тушить на медленном огне 30-40 минут до готовности картофеля. За 5 минут до конца добавить мелко нарезанный чеснок."
            ),
            CookingStep(
                title = "Подавать",
                text = "Дать настояться 5-10 минут под крышкой. Подавать, посыпав свежей зеленью."
            )
        )
    ),
    Recipe(
        id = "lagman",
        name = "Лагман",
        subtitle = "Густой суп с домашней лапшой и овощами",
        time = "80 мин",
        baseServings = 6,
        baseMince = 600.0,
        groups = listOf(
            IngredientGroup(
                id = "meat_lagman",
                label = "Мясо и база",
                icon = Icons.Filled.LunchDining,
                color = NavesColors.tomato,
                items = listOf(
                    Ingredient(id = "beef_lagman", name = "Говядина", base = 600.0, unit = "г", isBase = true),
                    Ingredient(id = "oil_lagman", name = "Масло растительное", base = 100.0, unit = "мл"),
                    Ingredient(id = "onion_lagman", name = "Лук репчатый", base = 200.0, unit = "г")
                )
            ),
            IngredientGroup(
                id = "veggies_lagman",
                label = "Овощная подлива (Ваджа)",
                icon = Icons.Filled.SoupKitchen,
                color = NavesColors.basil,
                items = listOf(
                    Ingredient(id = "pepper_lagman", name = "Перец болгарский", base = 200.0, unit = "г"),
                    Ingredient(id = "radish_lagman", name = "Редька (или дайкон)", base = 150.0, unit = "г"),
                    Ingredient(id = "tomato_lagman", name = "Помидоры", base = 200.0, unit = "г"),
                    Ingredient(id = "potato_lagman", name = "Картофель (по желанию)", base = 200.0, unit = "г"),
                    Ingredient(id = "garlic_lagman", name = "Чеснок", base = 4.0, unit = "зубчика", fixed = true)
                )
            ),
            IngredientGroup(
                id = "spices_lagman",
                label = "Специи и лапша",
                icon = Icons.Filled.LocalFireDepartment,
                color = NavesColors.honey,
                items = listOf(
                    Ingredient(id = "noodles", name = "Лапша для лагмана", base = 500.0, unit = "г"),
                    Ingredient(id = "zira_lagman", name = "Зира", base = 3.0, unit = "г"),
                    Ingredient(id = "badyan", name = "Бадьян", base = 1.0, unit = "звездочка", fixed = true),
                    Ingredient(id = "salt_lagman", name = "Соль", base = 12.0, unit = "г")
                )
            )
        ),
        steps = listOf(
            CookingStep(
                title = "Подготовить мясо и овощи",
                text = "Мясо нарезать небольшими кубиками. Лук — полукольцами, морковь, редьку и перец — мелкой соломкой. Помидоры очистить от кожицы и мелко нарубить."
            ),
            CookingStep(
                title = "Обжарить базу",
                text = "В казане сильно разогреть масло. Обжарить мясо до румяной корочки. Добавить лук, жарить до золотистого цвета."
            ),
            CookingStep(
                title = "Тушить овощи",
                text = "Поочередно добавлять морковь, редьку, болгарский перец и помидоры. Жарить всё вместе 10–12 минут на сильном огне."
            ),
            CookingStep(
                title = "Приготовить подливу",
                text = "Влить горячую воду (или бульон), чтобы она покрыла овощи на 2–3 см. Добавить измельченный чеснок, зиру, бадьян и соль. Томить на медленном огне 30 минут."
            ),
            CookingStep(
                title = "Сварить лапшу",
                text = "Лапшу отварить в подсоленной воде до готовности. Откинуть на дуршлаг и промыть горячей водой."
            ),
            CookingStep(
                title = "Подача",
                text = "В глубокую тарелку (кесе) выложить порцию лапши, залить горячей овощной подливой с мясом. Посыпать свежей кинзой или укропом."
            )
        )
    ),
    Recipe(
        id = "carbonara",
        name = "Карбонара с беконом",
        subtitle = "Итальянская классика с нежным соусом",
        time = "20 мин",
        baseServings = 2,
        baseMince = 200.0,
        groups = listOf(
            IngredientGroup(
                id = "pasta_base",
                label = "Паста",
                icon = Icons.Filled.BakeryDining,
                color = NavesColors.honey,
                items = listOf(
                    Ingredient(id = "spaghetti", name = "Спагетти", base = 250.0, unit = "г"),
                    Ingredient(id = "salt_pasta", name = "Соль (для воды)", base = 10.0, unit = "г", fixed = true)
                )
            ),
            IngredientGroup(
                id = "sauce_meat",
                label = "Соус и мясо",
                icon = Icons.Filled.LunchDining,
                color = NavesColors.tomato,
                items = listOf(
                    Ingredient(id = "bacon", name = "Бекон", base = 200.0, unit = "г", isBase = true),
                    Ingredient(id = "eggs_carb", name = "Яйца (желтки)", base = 3.0, unit = "шт", fixed = true),
                    Ingredient(id = "cheese_parm", name = "Пармезан (или твердый сыр)", base = 50.0, unit = "г"),
                    Ingredient(id = "black_pepper", name = "Черный перец (свежемолотый)", base = 2.0, unit = "г", fixed = true)
                )
            )
        ),
        steps = listOf(
            CookingStep(
                title = "Сварить пасту",
                text = "Поставьте воду для пасты. Варите спагетти до состояния аль-денте (на 1-2 минуты меньше, чем указано на упаковке)."
            ),
            CookingStep(
                title = "Обжарить бекон",
                text = "Нарежьте бекон полосками. Обжарьте на сухой сковороде до золотистого цвета и хруста."
            ),
            CookingStep(
                title = "Приготовить соус",
                text = "Смешайте желтки с тертым сыром и большим количеством черного перца в отдельной миске."
            ),
            CookingStep(
                title = "Соединить",
                text = "Переложите готовую пасту в сковороду к бекону (огонь выключен!). Добавьте немного воды от пасты и яично-сырную смесь. Быстро перемешивайте, чтобы яйца не свернулись, а превратились в кремовый соус."
            )
        )
    ),
    Recipe(
        id = "pea_soup",
        name = "Гороховый суп",
        subtitle = "Сытный суп с копченостями",
        time = "100 мин",
        baseServings = 6,
        baseMince = 500.0,
        groups = listOf(
            IngredientGroup(
                id = "soup_base",
                label = "Основа",
                icon = Icons.Filled.LunchDining,
                color = NavesColors.tomato,
                items = listOf(
                    Ingredient(id = "smoked_ribs", name = "Копченые ребра или мясо", base = 500.0, unit = "г", isBase = true),
                    Ingredient(id = "peas", name = "Горох сухой", base = 300.0, unit = "г", note = "замочить заранее")
                )
            ),
            IngredientGroup(
                id = "soup_veggies",
                label = "Овощи",
                icon = Icons.Filled.SoupKitchen,
                color = NavesColors.basil,
                items = listOf(
                    Ingredient(id = "potatoes_pea", name = "Картофель", base = 400.0, unit = "г"),
                    Ingredient(id = "onion_pea", name = "Лук репчатый", base = 100.0, unit = "г"),
                    Ingredient(id = "carrot_pea", name = "Морковь", base = 100.0, unit = "г")
                )
            ),
            IngredientGroup(
                id = "soup_flavor",
                label = "Вкус",
                icon = Icons.Filled.LocalFireDepartment,
                color = NavesColors.honey,
                items = listOf(
                    Ingredient(id = "salt_pea", name = "Соль", base = 8.0, unit = "г"),
                    Ingredient(id = "pepper_pea", name = "Перец", base = 2.0, unit = "г"),
                    Ingredient(id = "bay_pea", name = "Лавровый лист", base = 1.0, unit = "шт", fixed = true)
                )
            )
        ),
        steps = listOf(
            CookingStep(
                title = "Подготовка гороха",
                text = "Горох промойте и замочите в холодной воде минимум на 2-3 часа (лучше на ночь)."
            ),
            CookingStep(
                title = "Варим бульон",
                text = "Копчености залейте водой и варите 40-50 минут. Выньте мясо, отделите от костей и нарежьте."
            ),
            CookingStep(
                title = "Варим горох",
                text = "В бульон добавьте горох и варите до его размягчения (30-60 минут в зависимости от сорта)."
            ),
            CookingStep(
                title = "Добавляем картофель",
                text = "Добавьте нарезанный кубиками картофель."
            ),
            CookingStep(
                title = "Зажарка",
                text = "Лук и морковь мелко нарежьте, обжарьте на масле до золотистого цвета и добавьте в суп."
            ),
            CookingStep(
                title = "Финал",
                text = "Верните мясо в суп, добавьте соль, перец и лавровый лист. Варите еще 5-10 минут. Подавайте с сухариками."
            )
        )
    ),
    Recipe(
        id = "solyanka",
        name = "Солянка мясная",
        subtitle = "Густой, наваристый суп с кислинкой",
        time = "60 мин",
        baseServings = 6,
        baseMince = 500.0,
        groups = listOf(
            IngredientGroup(
                id = "sol_meats",
                label = "Мясное ассорти",
                icon = Icons.Filled.LunchDining,
                color = NavesColors.tomato,
                items = listOf(
                    Ingredient(id = "meat_mix", name = "Мясные деликатесы (ветчина, сосиски, буженина)", base = 500.0, unit = "г", isBase = true),
                    Ingredient(id = "beef_broth", name = "Бульон мясной", base = 2000.0, unit = "мл")
                )
            ),
            IngredientGroup(
                id = "sol_pickles",
                label = "Для кислинки",
                icon = Icons.Filled.SoupKitchen,
                color = NavesColors.basil,
                items = listOf(
                    Ingredient(id = "pickles", name = "Огурцы соленые", base = 200.0, unit = "г"),
                    Ingredient(id = "onion_sol", name = "Лук репчатый", base = 150.0, unit = "г"),
                    Ingredient(id = "tomato_paste", name = "Томатная паста", base = 50.0, unit = "г"),
                    Ingredient(id = "olives", name = "Маслины", base = 50.0, unit = "г", fixed = true)
                )
            ),
            IngredientGroup(
                id = "sol_serving",
                label = "Подача",
                icon = Icons.Filled.DinnerDining,
                color = NavesColors.honey,
                items = listOf(
                    Ingredient(id = "lemon", name = "Лимон", base = 0.5, unit = "шт", fixed = true),
                    Ingredient(id = "sour_cream", name = "Сметана", base = 100.0, unit = "г", fixed = true)
                )
            )
        ),
        steps = listOf(
            CookingStep(
                title = "Подготовка основы",
                text = "Лук мелко нарежьте и обжарьте до прозрачности. Добавьте нарезанные соленые огурцы и томатную пасту, тушите 5-7 минут."
            ),
            CookingStep(
                title = "Нарезка мяса",
                text = "Все виды мясных продуктов нарежьте небольшими кусочками (соломкой или кубиками)."
            ),
            CookingStep(
                title = "Сборка супа",
                text = "В кипящий бульон добавьте зажарку и мясное ассорти. Варите на медленном огне 15 минут."
            ),
            CookingStep(
                title = "Добавление маслин",
                text = "Добавьте в суп маслины вместе с небольшим количеством рассола. Посолите и поперчите по вкусу."
            ),
            CookingStep(
                title = "Подача",
                text = "В каждую тарелку при подаче положите ломтик лимона, ложку сметаны и посыпьте свежей зеленью."
            )
        )
    )
)
