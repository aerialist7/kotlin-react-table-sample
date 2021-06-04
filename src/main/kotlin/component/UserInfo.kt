package component

import data.Key
import data.User
import hook.useSelectedUser
import kotlinx.css.*
import react.*
import styled.css
import styled.styledDiv

external interface UserInfoProps : RProps

private val UserInfo = functionalComponent<UserInfoProps> {
    var selectedKey by useState<Key?>(null)

    val onSelectionChanged = useCallback { user: User ->
        selectedKey = user.username
    }
    val selectedUser = useSelectedUser(selectedKey)

    styledDiv {
        css {
            padding = "20px"
            display = Display.grid
            gridAutoFlow = GridAutoFlow.column
        }
        LoadingIndicator {}
        UserTable {
            onRowClick = onSelectionChanged
        }
        ContextPanel {
            user = selectedUser
        }
    }
}

fun RBuilder.UserInfo(
    handler: UserInfoProps.() -> Unit,
): ReactElement =
    child(UserInfo) { attrs(handler) }
