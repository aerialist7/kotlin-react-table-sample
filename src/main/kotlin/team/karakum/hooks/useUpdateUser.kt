package team.karakum.hooks

import kotlinx.browser.window
import kotlinx.js.jso
import react.query.useMutation
import react.query.useQueryClient
import team.karakum.entities.User
import kotlin.js.Promise

typealias UpdateUser = (User) -> Unit

fun useUpdateUser(): UpdateUser {
    val queryClient = useQueryClient()
    val mutation = useMutation<User, Error, User, Nothing>(
        mutationFn = { user -> updateUser(user) },
        options = jso {
            onSuccess = { _, _, _ -> queryClient.invalidateQueries<Nothing>(team.karakum.QueryKey.USERS.name) }
        }
    )
    return { user ->
        mutation.mutate(user, jso())
    }
}

private fun updateUser(user: User): Promise<User> =
    window.fetch(
        input = "https://jsonplaceholder.typicode.com/users/${user.id}",
        init = jso {
            method = "PUT"
            body = JSON.stringify(user)
        }
    ).then { it.json() }.then { it.unsafeCast<User>() }
