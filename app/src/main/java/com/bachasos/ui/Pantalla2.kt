package com.bachasos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bachasos.R

@Composable
fun Pantalla2(
    onRegresar: () -> Unit,
    onRegistrar: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var acepta by remember { mutableStateOf(false) }
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
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    RoundedCornerShape(
                        bottomStart = 30.dp,
                        bottomEnd = 30.dp
                    )
                )
                .padding(24.dp)
        ) {
            TextButton(onClick = onRegresar) {
                Text(stringResource(R.string.regresar))
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.crear_cuenta),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.registro_desc),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    error = false
                },
                label = {
                    Text(stringResource(R.string.nombre))
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(Modifier.height(15.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                    error = false
                },
                label = {
                    Text(stringResource(R.string.correo))
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(Modifier.height(15.dp))

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
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(Modifier.height(7.dp))

            Text(
                text = stringResource(R.string.minimo),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )

            Spacer(Modifier.height(18.dp))

            Row(
                verticalAlignment =
                    androidx.compose.ui.Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = acepta,
                    onCheckedChange = {
                        acepta = it
                        error = false
                    }
                )

                Text(
                    text = stringResource(R.string.terminos),
                    fontSize = 13.sp
                )
            }

            if (error) {
                Text(
                    text = stringResource(
                        if (!acepta)
                            R.string.terminos_error
                        else
                            R.string.error_datos
                    ),
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp
                )
            }

            Spacer(Modifier.height(26.dp))

            Button(
                onClick = {
                    if (
                        nombre.isNotBlank() &&
                        correo.contains("@") &&
                        contrasena.length >= 8 &&
                        acepta
                    ) {
                        onRegistrar()
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
                    text = stringResource(R.string.registrarme),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(16.dp))

            OutlinedButton(
                onClick = onRegresar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(stringResource(R.string.iniciar_sesion))
            }
        }
    }
}