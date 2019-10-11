package com.example.bookmanagementsystem.data

import okhttp3.OkHttpClient

/**
 * @author dizzylay
 * @date 2019-10-10
 * @email liaoyanglay@outlook.com
 */
object NetConfig {
    val client = OkHttpClient()
    const val BASE_URL = "http://10.14.126.141:9000"
    const val ALL_BOOK = "/allbooks"
    const val EDIT_BOOK = "/editbook"
    const val DELETE_BOOK = "/deletebook"
    const val ADD_BOOK = "/addbook"
    const val QUERY_BOOK = "/querybook"
}