package tuver.notepad.ui.notesummarylist.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import tuver.notepad.model.NoteSummaryModel
import tuver.notepad.provider.ImageProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.text.DateFormat

class NoteSummaryListAdapter @AssistedInject constructor(
    @Assisted private val noteSummaryClickListener: (NoteSummaryModel) -> Unit,
    private val imageProvider: ImageProvider
) : PagingDataAdapter<NoteSummaryModel, NoteSummaryListViewHolder>(DIFF_UTIL) {

    private val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteSummaryListViewHolder {
        return NoteSummaryListViewHolder.newInstance(parent, dateFormat, imageProvider, noteSummaryClickListener)
    }

    override fun onBindViewHolder(holder: NoteSummaryListViewHolder, position: Int) {
        getItem(position)?.let { item -> holder.bind(item) }
    }

    companion object {

        @AssistedFactory
        interface Factory {
            fun create(noteClickListener: (NoteSummaryModel) -> Unit): NoteSummaryListAdapter
        }

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<NoteSummaryModel>() {

            override fun areItemsTheSame(oldItem: NoteSummaryModel, newItem: NoteSummaryModel): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: NoteSummaryModel, newItem: NoteSummaryModel): Boolean = oldItem == newItem

        }

    }

}