package example.hook

import example.QueryKey
import example.data.Key
import kotlinx.browser.window
import kotlinx.js.jso
import react.query.UseMutationResult
import react.query.useMutation
import react.query.useQueryClient
import kotlin.js.Promise

fun useUserDelete(): UseMutationResult<Unit?, Error, Any, Any> {
    val queryClient = useQueryClient()
    val user = useSelectedUser()

    return useMutation(
        mutationFn = { deleteUser(user?.id ?: "") },
        options = jso { onSuccess = { _, _, _ -> queryClient.invalidateQueries<Any>(QueryKey.USERS.name) } }
    )
}

private fun deleteUser(key: Key): Promise<Unit> =
    window.fetch("https://jsonplaceholder.typicode.com/users/${key}", jso { method = "DELETE" })
        .then {}
