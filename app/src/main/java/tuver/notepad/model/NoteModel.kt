package tuver.notepad.model

import java.util.*

data class NoteModel(
    val id: String = "",
    val title: String = "",
    val imageUrl: String? = null,
    val description: String = "",
    val createdDate: Date = Date(),
    val updatedDate: Date? = null
) {

    val isNew: Boolean
        get() = id.isBlank()

}