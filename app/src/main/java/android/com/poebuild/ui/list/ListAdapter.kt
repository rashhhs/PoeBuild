package android.com.poebuild.ui.list

import android.com.poebuild.R
import android.com.poebuild.ui.data.UnitView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView

class ListAdapter: PagedListAdapter<UnitView,ListAdapter.ViewHolder>(ListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unit = getItem(position)
        holder.title.text = unit?.title
    }

    open inner class ViewHolder(item:View): RecyclerView.ViewHolder(item){
        internal var icon:ImageView = item.findViewById(R.id.imageView_icon)
        internal var title:TextView = item.findViewById(R.id.textView_title)
        internal var description:TextView = item.findViewById(R.id.textView_description)

    }
}