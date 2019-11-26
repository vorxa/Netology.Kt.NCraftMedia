package ru.vorxa.ncraftmedia.adapter

import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.vorxa.ncraftmedia.R
import ru.vorxa.ncraftmedia.dto.Post
import kotlinx.android.synthetic.main.post_repost_card.view.*
import ru.vorxa.ncraftmedia.dto.PostType
import ru.vorxa.ncraftmedia.utils.humanizeTime
import java.sql.Date
import java.sql.Time
import java.sql.Timestamp

class RepostViewHolder(adapter: PostAdapter, view: View): BaseViewHolder(adapter, view) {
    init {
        with(itemView) {
            likeButton.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    item.likedByMe = !item.likedByMe
                    if (item.likedByMe) item.likes += 1 else item.likes -= 1
                    adapter.notifyItemChanged(adapterPosition)
                }
            }
            commentButton.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    item.commentedByMe = !item.commentedByMe
                    if (item.commentedByMe) item.comments += 1 else item.comments -= 1
                    adapter.notifyItemChanged(adapterPosition)
                }
            }
            shareButton.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    item.sharedByMe = !item.sharedByMe
                    if (item.sharedByMe) {
                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_TEXT, """
                                    ${item.author} (${item.created})
    
                                    ${item.content}
                                """.trimIndent()
                            )
                            type = "text/plain"
                        }
                        itemView.context.startActivity(intent)
                        item.shares += 1
                    } else { item.shares -= 1 }
                    adapter.notifyItemChanged(adapterPosition)
                }
            }
        }
    }

    override fun bind(post: Post) {
        with(itemView) {
            dateTextView.text = humanizeTime(System.currentTimeMillis()/1000 - post.created)
            authorTextView.text = post.author
            contentTextView.text = post.content
            sourceTextView.text = if (post.source != null) {
                """
                Отправил ${post.source.author}:
                ${post.source.content}
                
            """.trimIndent()
            } else {
                ""
            }
            likesTextView.text = if (post.likes > 0) post.likes.toString() else ""
            commentsTextView.text = if (post.comments > 0) post.comments.toString() else ""
            sharesTextView.text = if (post.shares > 0) post.shares.toString() else ""

            if (post.likedByMe) {
                likeButton.setImageResource(R.drawable.ic_favorite_red_24dp)
                likesTextView.setTextColor(Color.RED)
            } else {
                likeButton.setImageResource(R.drawable.ic_favorite_gray_24dp)
                likesTextView.setTextColor(Color.GRAY)
            }

            if (post.commentedByMe) {
                commentButton.setImageResource(R.drawable.ic_comment_red_24dp)
                commentsTextView.setTextColor(Color.RED)
            } else {
                commentButton.setImageResource(R.drawable.ic_comment_gray_24dp)
                commentsTextView.setTextColor(Color.GRAY)
            }

            if (post.sharedByMe) {
                shareButton.setImageResource(R.drawable.ic_share_red_24dp)
                sharesTextView.setTextColor(Color.RED)
            } else {
                shareButton.setImageResource(R.drawable.ic_share_gray_24dp)
                sharesTextView.setTextColor(Color.GRAY)
            }
        }
    }
}
