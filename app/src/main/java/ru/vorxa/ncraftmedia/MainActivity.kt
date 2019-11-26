package ru.vorxa.ncraftmedia

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.vorxa.ncraftmedia.dto.Event
import ru.vorxa.ncraftmedia.dto.Post
import ru.vorxa.ncraftmedia.dto.PostType
import ru.vorxa.ncraftmedia.utils.*
import androidx.recyclerview.widget.LinearLayoutManager
import ru.vorxa.ncraftmedia.adapter.PostAdapter
import java.net.URI

class MainActivity : AppCompatActivity() {

    // homework_5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val postsList = postsList()

        with(container) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(postsList)
        }

    }
}
/*
        val post = post1.copy(author = "Кот Матроскин", likedByMe = true)

        dateTextView.text = humanizeTime(currentTime - post.created)
        authorTextView.text = post.author
        contentTextView.text = post.content

        setButtons(post.likedByMe, post.likes, likesTextView, likeButton, "favorite")
        setButtons(post.sharedByMe, post.shares, sharesTextView, shareButton, "share")
        setButtons(post.commentedByMe, post.comments, commentsTextView, commentButton, "comment")

        likeButton.setOnClickListener{
            post.likedByMe=!post.likedByMe
            if (post.likedByMe) {
                post.likes += 1
            } else {
                post.likes -= 1
            }
            setButtons(post.likedByMe, post.likes, likesTextView, likeButton, "favorite")
        }
        commentButton.setOnClickListener{
            post.commentedByMe=!post.commentedByMe
            if (post.commentedByMe) {
                post.comments += 1
            } else {
                post.comments -= 1
            }
            setButtons(post.commentedByMe, post.comments, commentsTextView, commentButton, "comment")
        }
        shareButton.setOnClickListener{
            post.sharedByMe=!post.sharedByMe
            if (post.sharedByMe) {
                post.shares += 1
            } else {
                post.shares -= 1
            }
            setButtons(post.sharedByMe, post.shares, sharesTextView, shareButton, "share")
        }
        geoButton.setOnClickListener {
            val location = Uri.parse("geo:${post.location?.first},${post.location?.second}")
            val intent = Intent(Intent.ACTION_VIEW).apply { data = location }
            startActivity(intent)

        }


    }

    private fun setButtons(byMe: Boolean, count: Int, textView: TextView, buttonView: ImageButton, type: String) {
        textView.text = if (count > 0) count.toString() else ""
        val image = when(type) {
            "favorite" -> if (byMe) R.drawable.ic_favorite_red_24dp else R.drawable.ic_favorite_gray_24dp
            "comment" -> if (byMe) R.drawable.ic_comment_red_24dp else R.drawable.ic_comment_gray_24dp
            else -> if (byMe) R.drawable.ic_share_red_24dp else R.drawable.ic_share_gray_24dp
        }
        buttonView.setImageResource(image)
        textView.setTextColor(if (byMe) Color.RED else Color.GRAY)
    }
}
*/
