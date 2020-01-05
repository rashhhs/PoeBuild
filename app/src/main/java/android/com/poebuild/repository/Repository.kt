package android.com.poebuild.repository

import android.com.poebuild.repository.model.database.Database
import android.com.poebuild.repository.remote.DownloadService
import android.content.Context

class Repository {
    lateinit var database: Database
    lateinit var downloadService: DownloadService

    fun init(context: Context){
        initDatabase(context)
        initDownloadService()
    }

    private fun initDatabase(context: Context){
        database = Database()
        database.init(context)
    }

    private fun initDownloadService(){
        downloadService = DownloadService.instance
        downloadService.donwloads()
    }

    companion object{
        val instance = Repository()
    }
}