package component

import kotlinx.css.*
import react.Props
import react.fc
import styled.css
import styled.styledDiv

typealias UserInfoProps = Props

val UserInfo = fc<UserInfoProps> {
    styledDiv {
        css {
            padding = "20px"
            display = Display.grid
            gridAutoFlow = GridAutoFlow.column
        }
        UserTable()
        UserPanel()
    }
}
