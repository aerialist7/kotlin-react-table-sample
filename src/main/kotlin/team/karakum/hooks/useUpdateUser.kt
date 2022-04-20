package team.karakum.hooks

import kotlinx.browser.window
import kotlinx.js.Void
import kotlinx.js.jso
import react.query.QueryKey
import react.query.useMutation
import react.query.useQueryClient
import team.karakum.USERS_QUERY_KEY
import team.karakum.entities.User
import kotlin.js.Promise

typealias UpdateUser = (User) -> Unit

fun useUpdateUser(): UpdateUser {
    val client = useQueryClient()
    return useMutation<User, Error, User, QueryKey>(
        mutationFn = { user -> updateUser(user) },
        options = jso {
            onSuccess = { _, _, _ -> client.invalidateQueries<Void>(USERS_QUERY_KEY) }
        }
    ).mutate.unsafeCast<UpdateUser>()
}

private fun updateUser(user: User): Promise<User> =
    window.fetch(
        input = "https://jsonplaceholder.typicode.com/users/${user.id}",
        init = jso {
            method = "PUT"
            body = JSON.stringify(user)
        }
    ).then { it.json() }.then { it.unsafeCast<User>() }
