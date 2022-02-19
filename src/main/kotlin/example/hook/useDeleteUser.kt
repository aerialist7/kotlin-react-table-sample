package example.hook

import example.QueryKey
import example.data.Key
import kotlinx.browser.window
import kotlinx.js.jso
import react.query.useMutation
import react.query.useQueryClient
import kotlin.js.Promise

typealias DeleteUser = (Key) -> Unit

fun useDeleteUser(): DeleteUser {
    val queryClient = useQueryClient()
    val mutation = useMutation<Unit, Error, Key, Nothing>(
        mutationFn = { userId -> deleteUser(userId) },
        options = jso { onSuccess = { _, _, _ -> queryClient.invalidateQueries<Nothing>(QueryKey.USERS.name) } }
    )
    return { userId ->
        mutation.mutate(userId, jso())
    }
}

private fun deleteUser(id: Key): Promise<Unit> =
    window.fetch(
        input = "https://jsonplaceholder.typicode.com/users/${id}",
        init = jso { method = "DELETE" }
    ).then {}
