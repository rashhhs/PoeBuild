package android.com.poebuild

import android.com.poebuild.repository.Repository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.com.poebuild.ui.list.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository.instance
        repository.init(this)

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commitNow()
        }
    }

}
