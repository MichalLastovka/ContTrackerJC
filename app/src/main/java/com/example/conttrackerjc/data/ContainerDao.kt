package com.example.conttrackerjc.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ContainerDao {
    @Update(entity = Container::class)
    suspend fun updateContainer(container: PartialContainer) // Updating already cached data with remote data (not bringing notifyOn and note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContainer(container: Container) // Inserting new container into local database

    @Query("UPDATE containers SET notifyOn = :notify WHERE containerId = :id")
    suspend fun updateNotification(id: String, notify: Boolean) // Updating one field only - notifyOn true/false based on provided id

    @Query("UPDATE containers SET uuid = :uuid WHERE containerId = :id")
    suspend fun updateUuid(id: String, uuid: UUID) // Updating one field only - generation new UUID based on provided id

    @Delete
    suspend fun deleteContainer(container: Container)

    @Query("SELECT * FROM containers WHERE containerId = :id")
    suspend fun getContainerById(id: String): Container?

    @Query("SELECT * FROM containers")
    fun getContainers(): Flow<List<Container>> //Flow is updating list in real time
}