package example.hook

import example.component.SelectionContext
import example.data.User
import react.useContext
import react.useMemo

fun useSelectedUser(): User? {
    val users = useUsers()
    val (selectedKey) = useContext(SelectionContext)

    return useMemo(selectedKey, users) {
        users.find { it.id == selectedKey }
    }
}
