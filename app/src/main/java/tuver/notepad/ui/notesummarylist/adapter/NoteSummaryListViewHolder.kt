package tuver.notepad.ui.notesummarylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tuver.notepad.databinding.ItemNoteSummaryBinding
import tuver.notepad.model.NoteSummaryModel
import tuver.notepad.provider.ImageProvider
import java.text.DateFormat

class NoteSummaryListViewHolder(
    private val binding: ItemNoteSummaryBinding,
    private val dateFormat: DateFormat,
    private val imageProvider: ImageProvider,
    private val noteSummaryClickListener: (NoteSummaryModel) -> Unit
) : ViewHolder(binding.root) {

    fun bind(noteSummaryModel: NoteSummaryModel) {
        binding.apply {
            root.setOnClickListener { noteSummaryClickListener(noteSummaryModel) }
            titleText.text = noteSummaryModel.title
            shortDescriptionText.text = noteSummaryModel.shortDescription
            createdDateText.text = dateFormat.format(noteSummaryModel.createdDate)
            editedText.isVisible = noteSummaryModel.isUpdated
            imageView.setImageBitmap(null)
            noteSummaryModel.imageUrl?.let { imageProvider.loadImage(it, imageView) }
        }
    }

    companion object {

        fun newInstance(
            parent: ViewGroup,
            dateFormat: DateFormat,
            imageProvider: ImageProvider,
            noteSummaryClickListener: (NoteSummaryModel) -> Unit
        ): NoteSummaryListViewHolder {
            val binding = ItemNoteSummaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return NoteSummaryListViewHolder(binding, dateFormat, imageProvider, noteSummaryClickListener)
        }

    }

}