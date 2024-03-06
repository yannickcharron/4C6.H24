package ca.qc.cstj.noty.data.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ca.qc.cstj.noty.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteRepository {

    @Insert
    suspend fun create(note: Note)

    @Query("SELECT * FROM notes")
    suspend fun retrieveAllWithoutFlow() : List<Note>

    @Query("SELECT * FROM notes")
    fun retrieveAll() : Flow<List<Note>>

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()
}