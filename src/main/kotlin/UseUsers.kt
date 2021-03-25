import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import react.*

fun useUsers(): Array<User> {
    var data by useState(emptyArray<User>())

    useEffectWithCleanup(emptyList()) {
        val job = GlobalScope.launch {
            data = getUsers()
        }
        job::cancel
    }

    return data
}

private suspend fun getUsers(): Array<User> =
    window.fetch("https://jsonplaceholder.typicode.com/users")
        .then { it.json() }
        .then { it.unsafeCast<Array<User>>() }
        .await()
