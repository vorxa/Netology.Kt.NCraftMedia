package ru.vorxa.ncraftmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ru.vorxa.ncraftmedia.utils.*
import androidx.recyclerview.widget.LinearLayoutManager
import io.ktor.client.request.get
import kotlinx.coroutines.*
import ru.vorxa.ncraftmedia.adapter.PostAdapter
import ru.vorxa.ncraftmedia.client.Api
import ru.vorxa.ncraftmedia.dto.Post
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    // homework_6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()

//        with(container) {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = PostAdapter(list = posts)
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    fun fetchData() = launch {
        val posts = withContext(Dispatchers.IO) {
            Api.client.get<List<Post>>(Api.url)
        }
        Toast.makeText(this@MainActivity, "Length: ${posts.size}", Toast.LENGTH_LONG).show()
    }
}
