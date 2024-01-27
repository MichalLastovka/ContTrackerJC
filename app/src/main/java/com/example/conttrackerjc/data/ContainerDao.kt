package com.example.conttrackerjc.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContainerDao {
    @Update(entity = Container::class)
    suspend fun updateContainer(container: PartialContainer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContainer(container: Container)

    @Query("UPDATE containers SET notifyOn = :notify WHERE containerId = :id")
    suspend fun updateNotification(id: String, notify: Boolean)

    @Delete
    suspend fun deleteContainer(container: Container)

    @Query("SELECT * FROM containers WHERE containerId = :id")
    suspend fun getContainerById(id: String): Container?

    @Query("SELECT * FROM containers")
    fun getContainers(): Flow<List<Container>>
}