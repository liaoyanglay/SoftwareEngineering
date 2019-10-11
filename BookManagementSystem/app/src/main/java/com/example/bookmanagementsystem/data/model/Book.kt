package com.example.bookmanagementsystem.data.model

import java.io.Serializable

/**
 * @author dizzylay
 * @date 2019-10-09
 * @email liaoyanglay@outlook.com
 */
data class Book(
    var id: Int = 0,
    var name: String = "",
    var cover: String = "",
    var author: String = "",
    var clazz: String = "",
    var isRent: Boolean = false,
    var publisher: String = "",
    var publishDate: String = "",
    var price: String = "",
    var rate: Float = 0f,
    var isbn: String = "",
    var description: String = ""
) : Serializable