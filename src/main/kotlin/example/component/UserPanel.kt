package example.component

import csstype.BorderCollapse
import csstype.LineStyle
import csstype.WhiteSpace
import csstype.px
import example.Colors
import example.hook.useSelectedUser
import example.hook.useUserDelete
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div

typealias UserPanelProps = Props

val UserPanel = FC<UserPanelProps> {
    val user = useSelectedUser()
    val mutateOnUserDelete = useUserDelete()

    div {
        div {
            +"User Information"

            button {
                css {
                    marginLeft = 20.px
                }
                disabled = user == null
                onClick = { mutateOnUserDelete(user?.id ?: "", null) }

                +"Delete"
            }
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
