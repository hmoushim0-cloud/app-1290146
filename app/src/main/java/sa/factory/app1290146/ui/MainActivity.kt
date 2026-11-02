package sa.factory.app1290146.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import sa.factory.app1290146.data.InMemoryTikerFormulaPackageRepository
import sa.factory.app1290146.model.FormulaAlgorithmSpec

val BespokeColorScheme = darkColorScheme(
    primary = Color(0xFF0F766E),
    secondary = Color(0xFFF59E0B),
    tertiary = Color(0xFFF59E0B),
    background = Color(0xFF0F172A),
    surface = Color(0xFF1E293B),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFFF8FAFC),
    onSurface = Color(0xFFF8FAFC)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = InMemoryTikerFormulaPackageRepository()
        val viewModel = TikerFormulaPackageViewModel(repository)

        // Mathematical Lock 2: Support start_screen and screen intent extras
        val startScreen = intent?.getStringExtra("start_screen")
            ?: intent?.getStringExtra("screen")
            ?: "home"

        setContent {
            MaterialTheme(colorScheme = BespokeColorScheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf(startScreen) }
                    var selectedItem by remember { mutableStateOf<FormulaAlgorithmSpec?>(null) }

                    when (currentScreen) {
                        "details" -> {
                            TikerFormulaPackageDetailsScreen(
                                item = selectedItem,
                                onBack = { currentScreen = "home" },
                                onProceedCheckout = { currentScreen = "checkout" }
                            )
                        }
                        "checkout", "tracking" -> {
                            TikerFormulaPackageCheckoutScreen(
                                onBack = { currentScreen = "details" },
                                onComplete = { currentScreen = "home" }
                            )
                        }
                        else -> {
                            TikerFormulaPackageHomeScreen(
                                viewModel = viewModel,
                                onSelectItem = { item ->
                                    selectedItem = item
                                    currentScreen = "details"
                                },
                                onQuickCheckout = {
                                    currentScreen = "checkout"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
