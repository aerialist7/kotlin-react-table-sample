package example.hook

import example.QueryKeys
import example.component.SelectionContext
import example.data.User
import example.data.Users
import react.query.useQueryClient
import react.useContext
import react.useMemo

fun useSelectedUser(): User? {
    val users = useQueryClient().getQueryData<Users>(QueryKeys.USERS.name)
    val (selectedKey) = useContext(SelectionContext)

    return useMemo(selectedKey, users) {
        users?.find { it.id == selectedKey }
    }
}
