package team.karakum.hooks

import csstype.HtmlAttributes
import kotlinx.browser.window
import kotlinx.js.jso
import react.query.QueryKey
import react.query.useQuery
import team.karakum.USERS_QUERY_KEY
import team.karakum.entities.User
import team.karakum.entities.Users
import kotlin.js.Promise

fun useUsers(): Users {
    val result = useQuery<Users, Error, Users, QueryKey>(
        queryKey = USERS_QUERY_KEY,
        queryFn = { getUsers() }
    )
    return result.data ?: emptyArray()
}

private fun getUsers(): Promise<Users> =
    window.fetch("https://jsonplaceholder.typicode.com/users")
        .then { it.json() }
        .then { it.unsafeCast<Users>() }
