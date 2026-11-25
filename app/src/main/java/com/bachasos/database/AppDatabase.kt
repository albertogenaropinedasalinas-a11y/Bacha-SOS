
package com.bachasos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bachasos.dao.UsuarioDao
import com.bachasos.dao.ReporteDao
import com.bachasos.entity.UsuarioEntity
import com.bachasos.entity.ReporteEntity

@Database(
    entities = [
        UsuarioEntity::class,
        ReporteEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    abstract fun reporteDao(): ReporteDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun obtenerBaseDatos(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bachasos_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
