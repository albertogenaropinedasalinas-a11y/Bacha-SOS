
package com.bachasos.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bachasos.database.AppDatabase
import com.bachasos.entity.UsuarioEntity
import com.bachasos.entity.ReporteEntity
import kotlinx.coroutines.launch

@Composable
fun PantallaBaseDatos(onRegresar: () -> Unit) {

    val context = LocalContext.current
    val db = remember {
        AppDatabase.obtenerBaseDatos(context)
    }

    val scope = rememberCoroutineScope()

    val usuarios by db.usuarioDao()
        .obtenerTodos()
        .collectAsState(initial = emptyList())

    val reportes by db.reporteDao()
        .obtenerTodos()
        .collectAsState(initial = emptyList())

    var pestaña by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextButton(onClick = onRegresar) {
            Text("← Regresar")
        }

        Text(
            "Base de datos Bacha S.O.S",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(12.dp))

        TabRow(selectedTabIndex = pestaña) {
            Tab(
                selected = pestaña == 0,
                onClick = { pestaña = 0 },
                text = { Text("Usuarios") }
            )

            Tab(
                selected = pestaña == 1,
                onClick = { pestaña = 1 },
                text = { Text("Reportes") }
            )
        }

        Spacer(Modifier.height(12.dp))

        if (pestaña == 0) {
            FormularioUsuarios(
                usuarios = usuarios,
                guardar = { usuario, editar ->
                    scope.launch {
                        if (editar) {
                            db.usuarioDao().actualizar(usuario)
                        } else {
                            db.usuarioDao().insertar(usuario)
                        }
                    }
                },
                eliminar = { usuario ->
                    scope.launch {
                        db.usuarioDao().eliminar(usuario)
                    }
                }
            )
        } else {
            FormularioReportes(
                usuarios = usuarios,
                reportes = reportes,
                guardar = { reporte, editar ->
                    scope.launch {
                        if (editar) {
                            db.reporteDao().actualizar(reporte)
                        } else {
                            db.reporteDao().insertar(reporte)
                        }
                    }
                },
                eliminar = { reporte ->
                    scope.launch {
                        db.reporteDao().eliminar(reporte)
                    }
                }
            )
        }
    }
}

@Composable
fun FormularioUsuarios(
    usuarios: List<UsuarioEntity>,
    guardar: (UsuarioEntity, Boolean) -> Unit,
    eliminar: (UsuarioEntity) -> Unit
) {
    var id by remember { mutableIntStateOf(0) }
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            if (id == 0) "Registrar usuario" else "Editar usuario",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                if (
                    nombre.isBlank() ||
                    correo.isBlank() ||
                    telefono.isBlank()
                ) {
                    error = "Completa todos los campos"
                } else {
                    guardar(
                        UsuarioEntity(
                            id = id,
                            nombre = nombre,
                            correo = correo,
                            telefono = telefono
                        ),
                        id != 0
                    )

                    id = 0
                    nombre = ""
                    correo = ""
                    telefono = ""
                    error = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (id == 0) "Guardar usuario"
                else "Actualizar usuario"
            )
        }

        if (error.isNotEmpty()) {
            Text(
                error,
                color = MaterialTheme.colorScheme.error
            )
        }

        HorizontalDivider(Modifier.padding(vertical = 12.dp))

        Text(
            "Usuarios registrados: ${usuarios.size}",
            style = MaterialTheme.typography.titleMedium
        )

        usuarios.forEach { usuario ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text("ID: ${usuario.id}")
                    Text("Nombre: ${usuario.nombre}")
                    Text("Correo: ${usuario.correo}")
                    Text("Teléfono: ${usuario.telefono}")

                    Row {
                        TextButton(
                            onClick = {
                                id = usuario.id
                                nombre = usuario.nombre
                                correo = usuario.correo
                                telefono = usuario.telefono
                            }
                        ) {
                            Text("Editar")
                        }

                        TextButton(
                            onClick = {
                                eliminar(usuario)
                            }
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FormularioReportes(
    usuarios: List<UsuarioEntity>,
    reportes: List<ReporteEntity>,
    guardar: (ReporteEntity, Boolean) -> Unit,
    eliminar: (ReporteEntity) -> Unit
) {
    var id by remember { mutableIntStateOf(0) }
    var usuarioId by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            if (id == 0) "Registrar reporte" else "Editar reporte",
            style = MaterialTheme.typography.titleMedium
        )

        Text("Usuarios disponibles:")

        usuarios.forEach { usuario ->
            Text("ID ${usuario.id}: ${usuario.nombre}")
        }

        OutlinedTextField(
            value = usuarioId,
            onValueChange = { usuarioId = it },
            label = { Text("ID del usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción del reporte") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicación") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                val numeroUsuario = usuarioId.toIntOrNull()

                if (
                    numeroUsuario == null ||
                    usuarios.none { it.id == numeroUsuario } ||
                    descripcion.isBlank() ||
                    ubicacion.isBlank()
                ) {
                    error = "Verifica los datos del reporte"
                } else {
                    guardar(

                        ReporteEntity(
                            id = id,
                            usuarioId = numeroUsuario,
                            descripcion = descripcion,
                            ubicacion = ubicacion,
                            estado = "Pendiente"
                        )
                        ,
                        id != 0
                    )

                    id = 0
                    usuarioId = ""
                    descripcion = ""
                    ubicacion = ""
                    error = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (id == 0) "Guardar reporte"
                else "Actualizar reporte"
            )
        }

        if (error.isNotEmpty()) {
            Text(
                error,
                color = MaterialTheme.colorScheme.error
            )
        }

        HorizontalDivider(Modifier.padding(vertical = 12.dp))

        Text(
            "Reportes registrados: ${reportes.size}",
            style = MaterialTheme.typography.titleMedium
        )

        reportes.forEach { reporte ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text("ID: ${reporte.id}")
                    Text("Usuario ID: ${reporte.usuarioId}")
                    Text("Descripción: ${reporte.descripcion}")
                    Text("Ubicación: ${reporte.ubicacion}")

                    Row {
                        TextButton(
                            onClick = {
                                id = reporte.id
                                usuarioId = reporte.usuarioId.toString()
                                descripcion = reporte.descripcion
                                ubicacion = reporte.ubicacion
                            }
                        ) {
                            Text("Editar")
                        }

                        TextButton(
                            onClick = {
                                eliminar(reporte)
                            }
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}
