package example.component

import csstype.BorderCollapse
import csstype.LineStyle
import csstype.WhiteSpace
import csstype.px
import example.Colors
import example.hook.useSelectedUser
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

typealias UserPanelProps = Props

val UserPanel = FC<UserPanelProps> {
    val user = useSelectedUser()

    div {
        div {
            +"Additional User Information"
        }

        div {
            css {
                width = 400.px
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
                borderWidth = 2.px
                borderStyle = LineStyle.solid
                borderColor = Colors.Stroke.Gray
                marginTop = 20.px
            }

            div {
                +"Name: ${user?.name ?: "—"}"
            }

            div {
                +"E-mail: ${user?.email ?: "—"}"
            }

            div {
                +"Phone: ${user?.phone ?: "—"}"
            }

            div {
                +"Website: ${user?.website ?: "—"}"
            }
        }
    }
}
