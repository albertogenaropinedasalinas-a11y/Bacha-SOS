
package com.bachasos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.bachasos.entity.UsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    // INSERT: Registrar usuario
    @Insert
    suspend fun insertar(usuario: UsuarioEntity)

    // SELECT: Mostrar usuarios
    @Query("SELECT * FROM usuarios ORDER BY id DESC")
    fun obtenerTodos(): Flow<List<UsuarioEntity>>

    // UPDATE: Modificar usuario
    @Update
    suspend fun actualizar(usuario: UsuarioEntity)

    // DELETE: Eliminar usuario
    @Delete
    suspend fun eliminar(usuario: UsuarioEntity)
}
