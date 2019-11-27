package ru.vorxa.ncraftmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import io.ktor.client.request.get
import kotlinx.coroutines.*
import ru.vorxa.ncraftmedia.adapter.PostAdapter
import ru.vorxa.ncraftmedia.client.Api
import ru.vorxa.ncraftmedia.dto.Post
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    // homework_6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()


    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    fun fetchData() = launch {
        progressBar.visibility = View.VISIBLE
        val posts = withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Api.client.get<List<Post>>(Api.url)
        }
        progressBar.visibility = View.GONE

        with(container) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(list = posts)
        }
    }
}
