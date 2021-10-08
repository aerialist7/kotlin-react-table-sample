package component

import csstype.Display
import csstype.GridAutoFlow
import csstype.px
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div
import react.fc

typealias UserInfoProps = Props

val UserInfo = fc<UserInfoProps> {
    div {
        css {
            padding = 20.px
            display = Display.grid
            gridAutoFlow = GridAutoFlow.column
        }
        UserTable()
        UserPanel()
    }
}
