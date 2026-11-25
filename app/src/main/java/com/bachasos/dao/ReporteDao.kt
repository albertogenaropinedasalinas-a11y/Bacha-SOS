
package com.bachasos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.bachasos.entity.ReporteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReporteDao {

    // INSERT: Registrar reporte
    @Insert
    suspend fun insertar(reporte: ReporteEntity)

    // SELECT: Mostrar todos los reportes
    @Query("SELECT * FROM reportes ORDER BY id DESC")
    fun obtenerTodos(): Flow<List<ReporteEntity>>

    // SELECT: Reportes de un usuario
    @Query("SELECT * FROM reportes WHERE usuarioId = :idUsuario")
    fun obtenerPorUsuario(idUsuario: Int): Flow<List<ReporteEntity>>

    // UPDATE: Modificar reporte
    @Update
    suspend fun actualizar(reporte: ReporteEntity)

    // DELETE: Eliminar reporte
    @Delete
    suspend fun eliminar(reporte: ReporteEntity)
}
