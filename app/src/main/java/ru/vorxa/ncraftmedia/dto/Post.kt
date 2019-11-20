package ru.vorxa.ncraftmedia.dto

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val created: Long = System.currentTimeMillis()/1000,
    // следующие параметры могут изменяться, в процессе взаимодействия
    var likedByMe: Boolean = false,
    var commentedByMe: Boolean = false,
    var sharedByMe: Boolean = false,
    var likes: Int = 0,
    var comments: Int = 0,
    var shares: Int = 0

) {

}