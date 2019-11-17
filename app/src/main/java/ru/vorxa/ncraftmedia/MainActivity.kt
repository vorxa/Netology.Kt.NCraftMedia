package ru.vorxa.ncraftmedia

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.vorxa.ncraftmedia.dto.Post
import ru.vorxa.ncraftmedia.utils.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentTime: Long = System.currentTimeMillis()/1000
        val post1 = Post(
            1,
            "Сан Саныч",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            currentTime - 700,
            false,
            false,
            true,
            5,
            0,
            1
        )

        val post = post1.copy(author = "Кот Матроскин", likedByMe = true)

        dateTextView.text = humanizeTime(currentTime - post.created)
        authorTextView.text = post.author
        contentTextView.text = post.content

        if (post.likes > 0) {
            likesTextView.text = post.likes.toString()
        }
        if (post.comments > 0) {
            commentsTextView.text = post.comments.toString()
        }
        if (post.shares > 0) {
            sharesTextView.text = post.shares.toString()
        }
        if (post.likedByMe) {
            likeButton.setImageResource(R.drawable.ic_favorite_red_24dp)
            likesTextView.setTextColor(Color.RED)
        }
        if (post.commentedByMe) {
            commentButton.setImageResource(R.drawable.ic_comment_red_24dp)
            commentsTextView.setTextColor(Color.RED)
        }
        if (post.sharedByMe) {
            shareButton.setImageResource(R.drawable.ic_share_red_24dp)
            sharesTextView.setTextColor(Color.RED)
        }
    }
}
