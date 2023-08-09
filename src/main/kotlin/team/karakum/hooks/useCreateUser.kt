package team.karakum.hooks

import js.core.Void
import js.core.jso
import js.promise.Promise
import tanstack.query.core.QueryKey
import tanstack.react.query.useMutation
import tanstack.react.query.useQueryClient
import team.karakum.USERS_QUERY_KEY
import team.karakum.entities.User
import web.http.fetchAsync

typealias CreateUser = (User) -> Unit

fun useCreateUser(): CreateUser {
    val client = useQueryClient()
    return useMutation<User, Error, User, QueryKey>(
        mutationFn = { user -> createUser(user) },
        options = jso {
            onSuccess = { _, _, _ -> client.invalidateQueries<Void>(USERS_QUERY_KEY) }
        }
    ).mutate.unsafeCast<CreateUser>()
}

private fun createUser(user: User): Promise<User> =
    fetchAsync(
        input = "https://jsonplaceholder.typicode.com/users",
        init = jso {
            method = "POST"
            body = JSON.stringify(user)
        }
    ).then { it.json() }.then { it.unsafeCast<User>() }
