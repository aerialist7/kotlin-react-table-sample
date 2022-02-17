package example.component

import csstype.Display
import csstype.GridAutoFlow
import csstype.px
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

typealias UserInfoProps = Props

val UserInfo = FC<UserInfoProps> {
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
