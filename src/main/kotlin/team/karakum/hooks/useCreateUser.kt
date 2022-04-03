package team.karakum.hooks

import kotlinx.browser.window
import kotlinx.js.jso
import react.query.useMutation
import react.query.useQueryClient
import team.karakum.entities.User
import kotlin.js.Promise

typealias CreateUser = (User) -> Unit

fun useCreateUser(): CreateUser {
    val queryClient = useQueryClient()
    val mutation = useMutation<User, Error, User, Nothing>(
        mutationFn = { user -> createUser(user) },
        options = jso {
            onSuccess = { _, _, _ -> queryClient.invalidateQueries<Nothing>(team.karakum.QueryKey.USERS.name) }
        }
    )
    return { user ->
        mutation.mutate(user, jso())
    }
}

private fun createUser(user: User): Promise<User> =
    window.fetch(
        input = "https://jsonplaceholder.typicode.com/users",
        init = jso {
            method = "POST"
            body = JSON.stringify(user)
        }
    ).then { it.json() }.then { it.unsafeCast<User>() }
