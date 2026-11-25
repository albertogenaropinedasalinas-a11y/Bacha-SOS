
package com.bachasos.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bachasos.R

@Composable
fun Pantalla3(
    reportes: List<Reporte>,
    onCerrarSesion: () -> Unit,
    onBaseDatos: () -> Unit
) {

    val contexto = LocalContext.current

    val mensajePendiente = stringResource(
        R.string.funcion_pendiente
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,

        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {

                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Text("●") },
                    label = {
                        Text(stringResource(R.string.inicio))
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        Toast.makeText(
                            contexto,
                            mensajePendiente,
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    icon = { Text("○") },
                    label = {
                        Text(stringResource(R.string.mapa))
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onBaseDatos,
                    icon = { Text("○") },
                    label = {
                        Text(stringResource(R.string.mis_reportes))
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onCerrarSesion,
                    icon = { Text("○") },
                    label = {
                        Text(stringResource(R.string.perfil))
                    }
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = onBaseDatos,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Text(
                    text = stringResource(R.string.simbolo_agregar),
                    fontSize = 30.sp
                )
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            Text(
                text = stringResource(R.string.saludo),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.saludo_desc),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(24.dp))

            // TARJETA PRINCIPAL
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Text(
                        text = stringResource(R.string.seguridad),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 12.sp
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = stringResource(R.string.reportar_bache),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = stringResource(R.string.reportar_desc),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 13.sp
                    )

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = onBaseDatos,
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                MaterialTheme.colorScheme.surface,
                            contentColor =
                                MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.nuevo_reporte)
                        )
                    }
                }
            }

            Spacer(Modifier.height(22.dp))

            // CONTADOR DE REPORTES
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.primaryContainer
                )
            ) {

                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = reportes.size.toString(),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color =
                            MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(Modifier.width(18.dp))

                    Text(
                        text = stringResource(
                            R.string.reportes_registrados
                        ),
                        color =
                            MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // REPORTES DE LA COMUNIDAD
            Text(
                text = stringResource(R.string.recientes),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            reportes.take(3).forEach { reporte ->

                TarjetaReporte(reporte)

                Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.height(70.dp))
        }
    }
}
