package com.example.conttrackerjc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Container::class],
    version = 3,
    exportSchema = false
)
abstract class ContainersDatabase: RoomDatabase() {
    abstract val dao: ContainerDao
    companion object{
        @Volatile
        private var INSTANCE: ContainerDao? = null

        fun getDaoInstance(context: Context): ContainerDao{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null){
                    instance = buildDatabase(context).dao
                    INSTANCE = instance
                }
                return instance
            }
        }
        private fun buildDatabase(context: Context):
                ContainersDatabase = Room.databaseBuilder(
            context.applicationContext,
            ContainersDatabase::class.java,
            "containers_database"
        ).fallbackToDestructiveMigration().build()
    }
}