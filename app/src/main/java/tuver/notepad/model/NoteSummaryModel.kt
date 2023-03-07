package tuver.notepad.model

import java.io.Serializable
import java.util.*

data class NoteSummaryModel(
    val id: String = "",
    val title: String = "",
    val imageUrl: String? = null,
    val shortDescription: String = "",
    val createdDate: Date = Date(),
    val isUpdated: Boolean = false
) : Serializable