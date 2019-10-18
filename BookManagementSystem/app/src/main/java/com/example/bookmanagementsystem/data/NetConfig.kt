package com.example.bookmanagementsystem.data

import okhttp3.OkHttpClient

/**
 * @author dizzylay
 * @date 2019-10-10
 * @email liaoyanglay@outlook.com
 */
object NetConfig {
    val client = OkHttpClient()
    const val BASE_URL = "http://10.11.52.0:9000"
    const val ALL_BOOK = "/allbooks"
    const val EDIT_BOOK = "/editbook"
    const val DELETE_BOOK = "/deletebook"
    const val ADD_BOOK = "/addbook"
    const val QUERY_BOOK = "/querybook"
    const val LEND_BOOK = "/lendbook"
    const val RETURN_BOOK = "/returnbook"
    const val ALL_READERS = "/allreaders"
    const val ALL_RECORDS = "/lendlist"
}