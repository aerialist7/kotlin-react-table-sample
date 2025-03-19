package io.github.aerialist7.hooks

import io.github.aerialist7.USERS_QUERY_KEY
import io.github.aerialist7.entities.User
import js.objects.jso
import js.promise.Promise
import tanstack.query.core.InvalidateQueryFilters
import tanstack.query.core.QueryKey
import tanstack.react.query.useMutation
import tanstack.react.query.useQueryClient
import web.http.RequestInit
import web.http.RequestMethod
import web.http.fetchAsync

typealias DeleteUser = (User) -> Unit

fun useDeleteUser(): DeleteUser {
    val client = useQueryClient()
    return useMutation<Unit, Error, User, QueryKey>(
        options = jso {
            mutationFn = { user -> deleteUser(user) }
            onSuccess = { _, _, _ ->
                client.invalidateQueries<InvalidateQueryFilters<*, *, *, QueryKey>>(
                    filters = jso {
                        queryKey = USERS_QUERY_KEY
                    }
                )
            }
        }
    ).mutate.unsafeCast<DeleteUser>()
}

private fun deleteUser(user: User): Promise<Unit> =
    fetchAsync(
        url = "https://jsonplaceholder.typicode.com/users/${user.id}",
        init = RequestInit(method = RequestMethod.DELETE),
    ).then {}
