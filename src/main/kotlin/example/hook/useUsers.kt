package example.hook

import example.data.Users
import kotlinx.browser.window
import react.query.useQuery
import kotlin.js.Promise

fun useUsers(): Users {
    return useQuery<Users, Error, Users, String>(
        queryKey = "users",
        queryFn = { getUsers() }
    ).data ?: emptyArray()
}

private fun getUsers(): Promise<Users> =
    window.fetch("https://jsonplaceholder.typicode.com/users")
        .then { it.json() }
        .then { it.unsafeCast<Users>() }
