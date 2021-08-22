package component

import kotlinx.css.*
import react.RProps
import react.fc
import styled.css
import styled.styledDiv

external interface UserInfoProps : RProps

val UserInfo = fc<UserInfoProps> {
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
