
package com.bachasos.data

import androidx.compose.runtime.*
import com.bachasos.ui.Pantalla1
import com.bachasos.ui.Pantalla2
import com.bachasos.ui.Pantalla3
import com.bachasos.ui.PantallaBaseDatos
import com.bachasos.ui.Reporte
import com.bachasos.ui.theme.BachaTheme

@Composable
fun AppScreens() {

    // Control de pantallas
    var pantalla by remember {
        mutableIntStateOf(1)
    }

    // Reportes de ejemplo
    val reportes = remember {
        mutableStateListOf(
            Reporte(
                direccion = "Av. Juárez 120, Centro",
                folio = "012",
                fecha = "Hace 2 horas",
                gravedad = "Alto",
                estado = "Sin reparar"
            ),
            Reporte(
                direccion = "Calle Hidalgo 45, Col. Norte",
                folio = "009",
                fecha = "Ayer",
                gravedad = "Medio",
                estado = "En proceso"
            ),
            Reporte(
                direccion = "Blvd. Sur 300",
                folio = "006",
                fecha = "Hace 3 días",
                gravedad = "Bajo",
                estado = "Reparado"
            )
        )
    }

    BachaTheme(tema = "Claro") {

        when (pantalla) {

            // PANTALLA 1: INICIO DE SESIÓN
            1 -> Pantalla1(
                onIngresar = {
                    pantalla = 3
                },
                onCrearCuenta = {
                    pantalla = 2
                }
            )

            // PANTALLA 2: CREAR CUENTA
            2 -> Pantalla2(
                onRegresar = {
                    pantalla = 1
                },
                onRegistrar = {
                    pantalla = 3
                }
            )

            // PANTALLA 3: INICIO
            3 -> Pantalla3(
                reportes = reportes,
                onCerrarSesion = {
                    pantalla = 1
                },
                onBaseDatos = {
                    pantalla = 4
                }
            )

            // PANTALLA 4: BASE DE DATOS ROOM
            4 -> PantallaBaseDatos(
                onRegresar = {
                    pantalla = 3
                }
            )
        }
    }
}
