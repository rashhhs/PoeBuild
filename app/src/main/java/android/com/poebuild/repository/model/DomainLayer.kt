package android.com.poebuild.repository.model

import android.com.poebuild.repository.model.database.Database
import androidx.lifecycle.LiveData
import androidx.paging.PagedList

interface DomainLayer<T,Z> {
    fun getDatabase():Database
    fun insert(values:List<T>)
    fun get():LiveData<PagedList<Z>>
}