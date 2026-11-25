package com.bachasos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bachasos.R

@Composable
fun Pantalla1(
    onIngresar: () -> Unit,
    onCrearCuenta: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        bottomStart = 38.dp,
                        bottomEnd = 38.dp
                    )
                )
                .background(MaterialTheme.colorScheme.primary)
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 55.dp,
                    bottom = 36.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable._a3d18b51_f885_452c_8a7b_fa3fcc20f4ed_),
                contentDescription = stringResource(
                    R.string.logo_descripcion
                ),
                modifier = Modifier.size(110.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(14.dp))

            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 29.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.lema),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 13.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)
        ) {
            Text(
                text = stringResource(R.string.bienvenido),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(
                    R.string.inicia_para_continuar
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(28.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                    error = false
                },
                label = {
                    Text(stringResource(R.string.correo))
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(Modifier.height(14.dp))

            OutlinedTextField(
                value = contrasena,
                onValueChange = {
                    contrasena = it
                    error = false
                },
                label = {
                    Text(stringResource(R.string.contrasena))
                },
                visualTransformation =
                    PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            if (error) {
                Spacer(Modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.error_datos),
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp
                )
            }

            Spacer(Modifier.height(25.dp))

            Button(
                onClick = {
                    if (
                        correo.contains("@") &&
                        contrasena.isNotBlank()
                    ) {
                        onIngresar()
                    } else {
                        error = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(
                    text = stringResource(R.string.iniciar_sesion),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.sin_cuenta),
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )

            Spacer(Modifier.height(10.dp))

            OutlinedButton(
                onClick = onCrearCuenta,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(stringResource(R.string.crear_cuenta))
            }
        }
    }
}