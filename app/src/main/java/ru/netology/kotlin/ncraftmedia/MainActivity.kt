package ru.netology.kotlin.ncraftmedia

import android.graphics.Color
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
            "12 ноября 2019"
        )

        dateTextView.text = post.created
        authorTextView.text = post.author
        contentTextView.text = post.content
        post.likedByMe = true
        post.commentedByMe = false
        post.sharedByMe = false
        post.likes = 5
        post.comments = 0
        post.shares = 1


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
