package ru.vorxa.ncraftmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vorxa.ncraftmedia.R
import ru.vorxa.ncraftmedia.dto.Post
import ru.vorxa.ncraftmedia.dto.PostType

const val VIEW_TYPE_POST = 1
const val VIEW_TYPE_REPOST = 2
const val VIEW_TYPE_EVENT = 3
const val VIEW_TYPE_VIDEO = 4
const val VIEW_TYPE_AD = 5

fun viewTypeToPostType(viewType: Int) = when (viewType) {
    VIEW_TYPE_POST -> PostType.POST
    VIEW_TYPE_REPOST -> PostType.REPOST
    VIEW_TYPE_EVENT -> PostType.EVENT
    VIEW_TYPE_VIDEO -> PostType.VIDEO
    VIEW_TYPE_AD -> PostType.AD
    else -> TODO("unknown view type")
}

class PostAdapter(val list: List<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewTypeToPostType(viewType)) {
            PostType.POST -> PostViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_post_card,
                    parent,
                    false
                )
            )
            PostType.REPOST -> RepostViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_repost_card,
                    parent,
                    false
                )
            )
            PostType.EVENT -> EventViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_event_card,
                    parent,
                    false
                )
            )
            PostType.VIDEO -> VideoViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_video_card,
                    parent,
                    false
                )
            )
            PostType.AD -> AdViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_ad_card,
                    parent,
                    false
                )
            )
            else -> TODO()
        }

    override fun getItemCount() = list.size

    override fun getItemId(position: Int) = list[position].id

    override fun getItemViewType(position: Int) = when (list[position].type) {
        PostType.POST -> VIEW_TYPE_POST
        PostType.REPOST -> VIEW_TYPE_REPOST
        PostType.EVENT -> VIEW_TYPE_EVENT
        PostType.VIDEO -> VIEW_TYPE_VIDEO
        PostType.AD -> VIEW_TYPE_AD
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as BaseViewHolder) {
            bind(list[position])
        }
    }
}

