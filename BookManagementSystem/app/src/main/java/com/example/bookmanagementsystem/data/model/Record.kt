package com.example.bookmanagementsystem.data.model

/**
 * @author dizzylay
 * @date 2019-10-10
 * @email liaoyanglay@outlook.com
 */
class Record(
    var sernum: Long = 0,
    var bookId: Int = 0,
    var readerId: Int = 0,
    var lendDate: String = "",
    var backDate: String = ""
)