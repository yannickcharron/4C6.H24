package ca.qc.cstj.noty.data.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ca.qc.cstj.noty.models.Note

@Dao
interface NoteRepository {

    @Insert
    suspend fun create(note: Note)

    @Query("SELECT * FROM notes")
    fun retrieveAll() : List<Note>
}