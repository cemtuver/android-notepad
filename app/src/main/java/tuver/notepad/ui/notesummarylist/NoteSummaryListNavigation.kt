package tuver.notepad.ui.notesummarylist

import tuver.notepad.model.NoteSummaryModel

sealed class NoteSummaryListNavigation {
    object NavigateToNoteCreate : NoteSummaryListNavigation()
    data class NavigateToNoteEdit(val noteSummaryModel: NoteSummaryModel) : NoteSummaryListNavigation()
}