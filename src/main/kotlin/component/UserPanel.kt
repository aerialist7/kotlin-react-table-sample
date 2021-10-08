package component

import Colors
import csstype.BorderCollapse
import csstype.LineStyle
import csstype.WhiteSpace
import csstype.px
import hook.useSelectedUser
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div
import react.fc

typealias UserPanelProps = Props

val UserPanel = fc<UserPanelProps> {
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
