package component

import kotlinx.css.*
import react.*
import styled.css
import styled.styledDiv

external interface UserInfoProps : RProps

private val UserInfo = functionalComponent<UserInfoProps> {
    styledDiv {
        css {
            padding = "20px"
            display = Display.grid
            gridAutoFlow = GridAutoFlow.column
        }
        UserTable {}
        UserPanel {}
    }
}

fun RBuilder.UserInfo(
    handler: UserInfoProps.() -> Unit,
): ReactElement =
    child(UserInfo) { attrs(handler) }
