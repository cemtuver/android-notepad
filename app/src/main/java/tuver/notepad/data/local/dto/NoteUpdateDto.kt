package tuver.notepad.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = tuver.notepad.data.local.dto.NoteDto.Companion.DB_TABLE)
data class NoteUpdateDto(
    @PrimaryKey @ColumnInfo(name = tuver.notepad.data.local.dto.NoteDto.Companion.DB_COLUMN_ID) val id: UUID,
    @ColumnInfo(name = tuver.notepad.data.local.dto.NoteDto.Companion.DB_COLUMN_TITLE) val title: String,
    @ColumnInfo(name = tuver.notepad.data.local.dto.NoteDto.Companion.DB_COLUMN_IMAGE_URL) val imageUrl: String?,
    @ColumnInfo(name = tuver.notepad.data.local.dto.NoteDto.Companion.DB_COLUMN_DESCRIPTION) val description: String,
    @ColumnInfo(name = tuver.notepad.data.local.dto.NoteDto.Companion.DB_COLUMN_UPDATED_DATE) val updatedDate: Long?
)