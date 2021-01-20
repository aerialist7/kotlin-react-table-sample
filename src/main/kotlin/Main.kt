import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import react.child
import react.dom.render

fun main() {
    var users = emptyArray<User>()

    GlobalScope
        .launch { users = fetchUsers() }
        .invokeOnCompletion {
            render(document.getElementById("root")) {
                child(UserTable) {
                    attrs.data = users
                }
            }
        }
}

private suspend fun fetchUsers(): Array<User> =
    window.fetch("https://jsonplaceholder.typicode.com/users")
        .await()
        .json()
        .await()
        .unsafeCast<Array<User>>()
