package android.com.poebuild.repository.remote

import android.com.poebuild.repository.remote.data.POEQuery

interface DownloadLayer {
    fun download()
    fun <T: POEQuery<T>> store(values: T)
}