package com.example.bookmanagementsystem.data.model

/**
 * @author dizzylay
 * @date 2019-10-10
 * @email liaoyanglay@outlook.com
 */
data class State(
    var status: Int = 0,
    var msg: String = ""
) {
    companion object {
        const val OK = 0
    }
}