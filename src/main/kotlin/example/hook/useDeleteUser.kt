package example.hook

import example.QueryKey
import example.data.Key
import kotlinx.browser.window
import kotlinx.js.jso
import react.query.MutateFunction
import react.query.useMutation
import react.query.useQueryClient
import kotlin.js.Promise

typealias DeleteUserHandler = MutateFunction<Unit, Error, Key, Nothing>

fun useDeleteUser(): DeleteUserHandler {
    val queryClient = useQueryClient()
    val mutation = useMutation<Unit, Error, Key, Nothing>(
        mutationFn = { userId -> deleteUser(userId) },
        options = jso { onSuccess = { _, _, _ -> queryClient.invalidateQueries<Nothing>(QueryKey.USERS.name) } }
    )
    return mutation.mutate
}

private fun deleteUser(id: Key): Promise<Unit> =
    window.fetch(
        input = "https://jsonplaceholder.typicode.com/users/${id}",
        init = jso { method = "DELETE" }
    ).then {}
