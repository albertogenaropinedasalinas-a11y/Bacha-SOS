package com.bachasos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bachasos.R
import com.bachasos.ui.theme.Rojo
import com.bachasos.ui.theme.Naranja
import com.bachasos.ui.theme.Verde

data class Reporte(
    val direccion: String,
    val folio: String,
    val fecha: String,
    val gravedad: String,
    val estado: String
)

@Composable
fun TarjetaReporte(reporte: Reporte) {

    val colorGravedad = when (reporte.gravedad) {
        "Alto" -> Rojo
        "Medio" -> Naranja
        else -> Verde
    }

    val recursoGravedad = when (reporte.gravedad) {
        "Alto" -> R.string.alto
        "Medio" -> R.string.medio
        else -> R.string.bajo
    }

    val recursoEstado = when (reporte.estado) {
        "Sin reparar" -> R.string.sin_reparar
        "En proceso" -> R.string.en_proceso
        else -> R.string.reparado
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        MaterialTheme.colorScheme.primaryContainer
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "!",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = reporte.direccion,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = reporte.fecha,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(colorGravedad)
                        .padding(
                            horizontal = 12.dp,
                            vertical = 4.dp
                        )
                ) {
                    Text(
                        text = stringResource(recursoGravedad),
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
            }

            Text(
                text = stringResource(recursoEstado),
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}