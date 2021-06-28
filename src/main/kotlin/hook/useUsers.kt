package hook

import data.Users
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import react.useEffect
import react.useState

fun useUsers(): Users {
    var data by useState<Users>(emptyArray())

    useEffect {
        val job = GlobalScope.launch {
            data = getUsers()
        }
        cleanup(job::cancel)
    }

    return data
}

private suspend fun getUsers(): Users =
    window.fetch("https://jsonplaceholder.typicode.com/users")
        .then { it.json() }
        .then { it.unsafeCast<Users>() }
        .await()
