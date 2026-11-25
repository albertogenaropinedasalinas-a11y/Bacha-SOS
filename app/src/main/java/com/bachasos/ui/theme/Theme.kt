package com.bachasos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColoresClaros = lightColorScheme(
    primary = VerdePrincipal,
    onPrimary = Blanco,
    primaryContainer = VerdeMenta,
    onPrimaryContainer = VerdeOscuro,
    secondary = VerdeOscuro,
    onSecondary = Blanco,
    background = FondoClaro,
    onBackground = TextoOscuro,
    surface = Blanco,
    onSurface = TextoOscuro,
    surfaceVariant = GrisTarjeta,
    onSurfaceVariant = TextoSecundario,
    outline = TextoSecundario
)

private val ColoresOscuros = darkColorScheme(
    primary = VerdeNocturno,
    onPrimary = FondoOscuro,
    primaryContainer = VerdeOscuro,
    onPrimaryContainer = Blanco,
    secondary = VerdeMenta,
    onSecondary = FondoOscuro,
    background = FondoOscuro,
    onBackground = Blanco,
    surface = SuperficieOscura,
    onSurface = Blanco,
    surfaceVariant = SuperficieOscura,
    onSurfaceVariant = VerdeMenta,
    outline = VerdeNocturno
)

@Composable
fun BachaTheme(
    tema: String = "Sistema",
    content: @Composable () -> Unit
) {
    val oscuro = when (tema) {
        "Claro" -> false
        "Oscuro" -> true
        else -> isSystemInDarkTheme()
    }

    MaterialTheme(
        colorScheme = if (oscuro) ColoresOscuros else ColoresClaros,
        content = content
    )
}