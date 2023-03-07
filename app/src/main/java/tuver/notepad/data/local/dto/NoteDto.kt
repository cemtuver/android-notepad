package tuver.notepad.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import tuver.notepad.data.local.dto.NoteDto.Companion.DB_TABLE
import java.util.*

@Entity(tableName = DB_TABLE)
data class NoteDto(
    @PrimaryKey @ColumnInfo(name = DB_COLUMN_ID) val id: UUID,
    @ColumnInfo(name = DB_COLUMN_TITLE) val title: String,
    @ColumnInfo(name = DB_COLUMN_IMAGE_URL) val imageUrl: String?,
    @ColumnInfo(name = DB_COLUMN_DESCRIPTION) val description: String,
    @ColumnInfo(name = DB_COLUMN_CREATED_DATE) val createdDate: Long,
    @ColumnInfo(name = DB_COLUMN_UPDATED_DATE) val updatedDate: Long?
) {

    companion object {

        const val DB_TABLE = "notes"
        const val DB_COLUMN_ID = "id"
        const val DB_COLUMN_TITLE = "title"
        const val DB_COLUMN_IMAGE_URL = "imageUrl"
        const val DB_COLUMN_DESCRIPTION = "description"
        const val DB_COLUMN_CREATED_DATE = "createdDate"
        const val DB_COLUMN_UPDATED_DATE = "updatedDate"

    }

}