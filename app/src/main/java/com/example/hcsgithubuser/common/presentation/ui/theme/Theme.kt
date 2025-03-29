package com.example.hcsgithubuser.common.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Green400,
    onPrimary = Black,
    primaryContainer = Green600,
    onPrimaryContainer = Green200,

    secondary = Green300,
    onSecondary = Black,
    secondaryContainer = Green700,
    onSecondaryContainer = Green100,

    tertiary = Green200,
    onTertiary = Black,
    tertiaryContainer = Green800,
    onTertiaryContainer = Green250,

    background = Green800,
    onBackground = Green100,

    surface = Green700,
    onSurface = Green200,

    error = Red500,
    onError = Black,
    errorContainer = Red600,
    onErrorContainer = Red100
)

private val LightColorScheme = lightColorScheme(
    primary = Green500,             // Standard primary green
    onPrimary = White,              // Text color on primary background
    primaryContainer = Green100,    // Light green container
    onPrimaryContainer = Green800,  // Dark green text on container

    secondary = Green400,           // Slightly lighter than primary
    onSecondary = White,
    secondaryContainer = Green200,
    onSecondaryContainer = Green700,

    tertiary = Green300,            // Softer green for tertiary elements
    onTertiary = White,
    tertiaryContainer = Green250,
    onTertiaryContainer = Green900, // Very dark green for contrast

    background = Green100,          // Lightest green for background
    onBackground = Green800,

    surface = Green200,             // Soft green surface
    onSurface = Green700,

    error = Red500,                 // Standard Material error color
    onError = White,
    errorContainer = Red100,
    onErrorContainer = Red900

    /* Other default colors to override
   background = Color(0xFFFFFBFE),
   surface = Color(0xFFFFFBFE),
   onPrimary = Color.White,
   onSecondary = Color.White,
   onTertiary = Color.White,
   onBackground = Color(0xFF1C1B1F),
   onSurface = Color(0xFF1C1B1F),
   */
)


@Composable
fun HcsGithubUserTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}