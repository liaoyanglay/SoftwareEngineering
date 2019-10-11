package com.example.bookmanagementsystem.data

import com.example.bookmanagementsystem.data.model.LoggedInUser
import java.io.IOException
import java.lang.Exception

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            if (username == "admin" && password == "123456") {
                val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "AYong")
                return Result.Success(fakeUser)
            } else {
                return Result.Error(Exception("Error logging in"))
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

