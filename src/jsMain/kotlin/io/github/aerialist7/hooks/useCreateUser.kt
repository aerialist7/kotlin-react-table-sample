package io.github.aerialist7.hooks

import io.github.aerialist7.USERS_QUERY_KEY
import io.github.aerialist7.entities.User
import js.promise.Promise
import tanstack.query.core.InvalidateQueryFilters
import tanstack.query.core.QueryKey
import tanstack.react.query.UseMutationOptions
import tanstack.react.query.useMutation
import tanstack.react.query.useQueryClient
import web.http.*

typealias CreateUser = (User) -> Unit

fun useCreateUser(): CreateUser {
    val client = useQueryClient()
    return useMutation<User, Error, User, QueryKey>(
        options = UseMutationOptions(
            mutationFn = { user, _ -> createUser(user) },
            onSuccess = { _, _, _, _ ->
                client.invalidateQueries(
                    filters = InvalidateQueryFilters(
                        queryKey = USERS_QUERY_KEY,
                    )
                )
            },
        )
    ).mutate.unsafeCast<CreateUser>()
}

private fun createUser(user: User): Promise<User> =
    fetchAsync(
        url = "https://jsonplaceholder.typicode.com/users",
        init = RequestInit(
            method = RequestMethod.POST,
            body = BodyInit(JSON.stringify(user)),
        )
    ).then { it.jsonAsync() }.then { it.unsafeCast<User>() }
