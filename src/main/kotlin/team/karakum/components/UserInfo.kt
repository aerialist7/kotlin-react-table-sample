package team.karakum.components

import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import web.cssom.Display
import web.cssom.GridAutoFlow
import web.cssom.px

typealias UserInfoProps = Props

val UserInfo = FC<UserInfoProps> {
    div {
        css {
            padding = 20.px
            display = Display.grid
            gridAutoFlow = GridAutoFlow.column
        }

        SelectedUserModule {
            UserTable()
            UserPanel()
        }
    }
}
