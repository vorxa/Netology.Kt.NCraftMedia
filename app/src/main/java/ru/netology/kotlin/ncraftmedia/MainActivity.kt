package ru.netology.kotlin.ncraftmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.kotlin.ncraftmedia.dto.Post

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val post = Post(
            1,
            "Иван Иваныч",
            "Первый пост",
            "12 ноября 2019",
            true
        )

        dateTextView.text = post.created
        authorTextView.text = post.author
        contentTextView.text = post.content

        if (post.likedByMe) {
            likeButton.setImageResource(R.drawable.ic_favorite_active_24dp)
        }
    }
}
