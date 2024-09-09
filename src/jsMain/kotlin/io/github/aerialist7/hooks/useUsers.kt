package io.github.aerialist7.hooks

import io.github.aerialist7.USERS_QUERY_KEY
import io.github.aerialist7.entities.Users
import js.objects.jso
import js.promise.Promise
import tanstack.query.core.QueryFunction
import tanstack.query.core.QueryKey
import tanstack.query.core.skipToken
import tanstack.react.query.useQuery
import web.http.fetchAsync

fun useUsers(): Users {
    val result = useQuery<Users, Error, Users, QueryKey>(
        options = jso {
            queryKey = USERS_QUERY_KEY
            queryFn = QueryFunction { getUsers() }
        },
    )
    return result.data ?: emptyArray()
}

fun useSkipUsers(): Users {
    val result = useQuery<Users, Error, Users, QueryKey>(
        options = jso {
            queryKey = USERS_QUERY_KEY
            queryFn = skipToken
        },
    )
    return result.data ?: emptyArray()
}

private fun getUsers(): Promise<Users> =
    fetchAsync("https://jsonplaceholder.typicode.com/users")
        .then { it.jsonAsync() }
        .then { it.unsafeCast<Users>() }
