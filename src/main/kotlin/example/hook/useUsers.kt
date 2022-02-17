package example.hook

import example.QueryKey
import example.data.Users
import kotlinx.browser.window
import react.query.useQuery
import kotlin.js.Promise

fun useUsers(): Users {
    val result = useQuery<Users, Error, Users, String>(
        queryKey = QueryKey.USERS.name,
        queryFn = { getUsers() }
    )
    return result.data ?: emptyArray()
}

private fun getUsers(): Promise<Users> =
    window.fetch("https://jsonplaceholder.typicode.com/users")
        .then { it.json() }
        .then { it.unsafeCast<Users>() }
