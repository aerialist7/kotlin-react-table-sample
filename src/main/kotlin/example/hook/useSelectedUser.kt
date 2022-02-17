package example.hook

import example.component.SelectionContext
import example.component.UsersContext
import example.data.User
import react.useContext
import react.useMemo

fun useSelectedUser(): User? {
    val users = useContext(UsersContext)
    val (selectedKey) = useContext(SelectionContext)

    return useMemo(selectedKey, users) {
        users.find { it.username == selectedKey }
    }
}
