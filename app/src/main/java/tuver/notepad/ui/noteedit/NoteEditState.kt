package tuver.notepad.ui.noteedit

import androidx.lifecycle.MutableLiveData
import tuver.notepad.model.NoteSummaryModel

interface NoteEditState {

    val title: MutableLiveData<String>
    val imageUrl: MutableLiveData<String>
    val description: MutableLiveData<String>

    class Mutable(noteSummaryModel: NoteSummaryModel?) : NoteEditState {

        override val title = MutableLiveData(noteSummaryModel?.title ?: "")
        override val imageUrl = MutableLiveData(noteSummaryModel?.imageUrl ?: "")
        override val description = MutableLiveData(noteSummaryModel?.shortDescription ?: "")

    }

}