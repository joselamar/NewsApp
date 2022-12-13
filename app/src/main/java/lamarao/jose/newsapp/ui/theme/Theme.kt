package lamarao.jose.newsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import lamarao.jose.newsapp.common.rememberFlowWithLifecycle
import lamarao.jose.newsapp.entities.Colors
import lamarao.jose.newsapp.entities.Themes
import lamarao.jose.newsapp.repositories.datastore.DatastoreRepository

private val DarkColorPurplePalette = darkColors(
    primary = Purple,
    background = Black,
    primaryVariant = Black,
    secondary = White
)

private val DarkColorBluePalette = darkColors(
    primary = Blue,
    background = Black,
    primaryVariant = Black,
    secondary = White
)

private val DarkColorYellowPalette = darkColors(
    primary = Yellow,
    background = Black,
    primaryVariant = Black,
    secondary = White
)

private val DarkColorPinkPalette = darkColors(
    primary = Pink,
    background = Black,
    primaryVariant = Black,
    secondary = White
)

private val DarkColorGreenPalette = darkColors(
    primary = Green,
    background = Black,
    primaryVariant = Black,
    secondary = White
)

private val DarkColorOrangePalette = darkColors(
    primary = Orange,
    background = Black,
    primaryVariant = Black,
    secondary = White
)

private val LightColorPurplePalette = lightColors(
    primary = Purple,
    background = White,
    primaryVariant = White,
    secondary = Black
)

private val LightColorBluePalette = lightColors(
    primary = Blue,
    background = White,
    primaryVariant = White,
    secondary = Black
)

private val LightColorYellowPalette = lightColors(
    primary = Yellow,
    background = White,
    primaryVariant = White,
    secondary = Black
)

private val LightColorPinkPalette = lightColors(
    primary = Pink,
    background = White,
    primaryVariant = White,
    secondary = Black
)

private val LightColorGreenPalette = lightColors(
    primary = Green,
    background = White,
    primaryVariant = White,
    secondary = Black
)

private val LightColorOrangePalette = lightColors(
    primary = Orange,
    background = White,
    primaryVariant = White,
    secondary = Black
)

@Composable
fun NewsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
    datastoreRepository: DatastoreRepository
) {

    val initialColor by lazy {
        runBlocking {
            datastoreRepository.getPreferredColor().first()
        }
    }
    val getPreferredColorState by rememberFlowWithLifecycle(datastoreRepository.getPreferredColor())
        .collectAsState(initial = initialColor)

    val initialTheme by lazy {
        runBlocking {
            datastoreRepository.getPreferredTheme().first()
        }
    }
    val preferredTheme by rememberFlowWithLifecycle(datastoreRepository.getPreferredTheme())
        .collectAsState(initial = initialTheme)

    val colors = when (preferredTheme) {
        Themes.DEFAULT_THEME.toString() -> {
            when (getPreferredColorState) {
                Colors.PURPLE.name -> if (darkTheme) DarkColorPurplePalette else LightColorPurplePalette
                Colors.YELLOW.name -> if (darkTheme) DarkColorYellowPalette else LightColorYellowPalette
                Colors.PINK.name -> if (darkTheme) DarkColorPinkPalette else LightColorPinkPalette
                Colors.GREEN.name -> if (darkTheme) DarkColorGreenPalette else LightColorGreenPalette
                Colors.ORANGE.name -> if (darkTheme) DarkColorOrangePalette else LightColorOrangePalette
                else -> if (darkTheme) DarkColorBluePalette else LightColorBluePalette
            }
        }
        Themes.DARK_THEME.toString() -> {
            when (getPreferredColorState) {
                Colors.PURPLE.name -> DarkColorPurplePalette
                Colors.YELLOW.name -> DarkColorYellowPalette
                Colors.PINK.name -> DarkColorPinkPalette
                Colors.GREEN.name -> DarkColorGreenPalette
                Colors.ORANGE.name -> DarkColorOrangePalette
                else -> DarkColorBluePalette
            }
        }
        Themes.LIGHT_THEME.toString() -> {
            when (getPreferredColorState) {
                Colors.PURPLE.name -> LightColorPurplePalette
                Colors.YELLOW.name -> LightColorYellowPalette
                Colors.PINK.name -> LightColorPinkPalette
                Colors.GREEN.name -> LightColorGreenPalette
                Colors.ORANGE.name -> LightColorOrangePalette
                else -> LightColorBluePalette
            }
        }
        else -> {
            LightColorBluePalette
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
