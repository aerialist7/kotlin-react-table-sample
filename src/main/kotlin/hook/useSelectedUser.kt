package hook

import component.SelectionContext
import component.UsersContext
import data.User
import react.useContext
import react.useMemo

fun useSelectedUser(): User? {
    val users = useContext(UsersContext)
    val (selectedKey) = useContext(SelectionContext)

    return useMemo(selectedKey, users) {
        users.find { it.username == selectedKey }
    }
}

