package ru.naves.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ru.naves.app.ui.screens.NaVesApp
import ru.naves.app.ui.theme.NaVesTheme
import ru.naves.app.ui.theme.NavesColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NaVesTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(NavesColors.appBg)
                ) {
                    NaVesApp()
                }
            }
        }
    }
}
