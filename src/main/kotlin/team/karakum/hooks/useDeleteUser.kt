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

typealias DeleteUser = (User) -> Unit

fun useDeleteUser(): DeleteUser {
    val client = useQueryClient()
    return useMutation<Unit, Error, User, QueryKey>(
        mutationFn = { user -> deleteUser(user) },
        options = jso {
            onSuccess = { _, _, _ -> client.invalidateQueries<Void>(USERS_QUERY_KEY) }
        }
    ).mutate.unsafeCast<DeleteUser>()
}

private fun deleteUser(user: User): Promise<Unit> =
    window.fetch(
        input = "https://jsonplaceholder.typicode.com/users/${user.id}",
        init = jso { method = "DELETE" }
    ).then {}
