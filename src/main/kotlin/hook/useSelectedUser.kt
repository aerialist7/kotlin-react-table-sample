package hook

import component.UsersContext
import data.Key
import data.User
import react.useContext
import react.useMemo

fun useSelectedUser(
    key: Key?,
): User? {
    val users = useContext(UsersContext)

    return useMemo(key, users) {
        users.find { it.username == key }
    }
}

