package ru.educationalwork.firstandroidapp

class Post(
    val id: Int,
    val date: Long,
    val author: String,
    val content: String,
    val avatar: Int = 0,
    var like: Boolean = false,
    var comment: Boolean = false,
    var share: Boolean = false,
    var commentCounter: Int = 0,
    var likeCounter: Int = 0,
    var shareCounter: Int = 0
)