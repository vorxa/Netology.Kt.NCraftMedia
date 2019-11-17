package ru.vorxa.ncraftmedia.dto

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val created: Long = System.currentTimeMillis()/1000,
    val likedByMe: Boolean = false,
    val commentedByMe: Boolean = false,
    val sharedByMe: Boolean = false,
    val likes: Int = 0,
    val comments: Int = 0,
    val shares: Int = 0

) {

}