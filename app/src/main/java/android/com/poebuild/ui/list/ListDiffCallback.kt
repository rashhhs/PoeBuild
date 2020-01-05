package android.com.poebuild.ui.list

import android.com.poebuild.ui.data.UnitView
import androidx.recyclerview.widget.DiffUtil

class ListDiffCallback: DiffUtil.ItemCallback<UnitView>() {
    override fun areItemsTheSame(oldItem: UnitView, newItem: UnitView): Boolean {
        return oldItem.id == newItem.id
}

    override fun areContentsTheSame(oldItem: UnitView, newItem: UnitView): Boolean {
        return oldItem.id == newItem.id
    }
}