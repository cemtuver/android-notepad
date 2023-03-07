package tuver.notepad.data.local.database

import androidx.paging.PagingSource
import androidx.room.*
import tuver.notepad.data.local.dto.NoteDto
import tuver.notepad.data.local.dto.NoteUpdateDto
import java.util.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM ${NoteDto.DB_TABLE} WHERE ${NoteDto.DB_COLUMN_ID}=:id ")
    suspend fun select(id: UUID): NoteDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteDto: NoteDto)

    @Update(entity = NoteDto::class)
    suspend fun update(noteUpdateDto: NoteUpdateDto)

    @Delete
    suspend fun delete(noteDto: NoteDto)

    @Query("SELECT * FROM ${NoteDto.DB_TABLE}")
    fun selectPagingSource(): PagingSource<Int, NoteDto>

}