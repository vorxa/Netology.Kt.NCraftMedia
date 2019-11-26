package ru.vorxa.ncraftmedia.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_post_card.view.*
import ru.vorxa.ncraftmedia.dto.Post
import ru.vorxa.ncraftmedia.utils.*


abstract class BaseViewHolder(val adapter: PostAdapter, view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(post: Post)
}

class PostViewHolder(adapter: PostAdapter, view: View): BaseViewHolder(adapter, view) {
    init {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            with(itemView) {
                likeButton.setOnClickListener {
                    val item = adapter.list[adapterPosition]
                    item.likedByMe = !item.likedByMe
                    if (item.likedByMe) item.likes += 1 else item.likes -= 1
                    setButtons(item.likedByMe, item.likes, likesTextView, likeButton, "favorite")
                }
                commentButton.setOnClickListener {
                    val item = adapter.list[adapterPosition]
                    item.commentedByMe = !item.commentedByMe
                    if (item.commentedByMe) item.comments += 1 else item.comments -= 1
                    setButtons(item.commentedByMe, item.comments, commentsTextView, commentButton, "comment")
                }
                shareButton.setOnClickListener {
                    val item = adapter.list[adapterPosition]
                    item.sharedByMe = !item.sharedByMe
                    if (item.sharedByMe) item.shares += 1 else item.shares -= 1
                    setButtons(item.sharedByMe, item.shares, sharesTextView, shareButton, "share")
                }
            }
            adapter.notifyItemChanged(adapterPosition)
        }
    }

    override fun bind(post: Post) {
        with(itemView) {
            dateTextView.text = humanizeTime(System.currentTimeMillis()/1000 - post.created)
            authorTextView.text = post.author
            contentTextView.text = post.content

            setButtons(post.likedByMe, post.likes, likesTextView, likeButton, "favorite")
            setButtons(post.sharedByMe, post.shares, sharesTextView, shareButton, "share")
            setButtons(post.commentedByMe, post.comments, commentsTextView, commentButton, "comment")
            authorTextView.text = post.author
            contentTextView.text = post.content
        }
    }
}


