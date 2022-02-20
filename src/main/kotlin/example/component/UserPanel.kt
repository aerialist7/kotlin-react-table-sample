package example.component

import csstype.BorderCollapse
import csstype.LineStyle
import csstype.WhiteSpace
import csstype.px
import example.Colors
import example.hook.useDeleteUser
import example.hook.useUpdateUser
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.useContext

typealias UserPanelProps = Props

val UserPanel = FC<UserPanelProps> {
    val selectedUser by useContext(SelectedUserContext)
    val updateUser = useUpdateUser()
    val deleteUser = useDeleteUser()

    div {
        div {
            +"User Information"

            button {
                css {
                    marginLeft = 20.px
                }
                disabled = selectedUser == null
                onClick = { updateUser(selectedUser!!.apply { name += "_Updated" }) }

                +"Update"
            }

            button {
                css {
                    marginLeft = 20.px
                }
                disabled = selectedUser == null
                onClick = { deleteUser(selectedUser!!.id) }

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
                +"Name: ${selectedUser?.name ?: "—"}"
            }

            div {
                +"E-mail: ${selectedUser?.email ?: "—"}"
            }

            div {
                +"Phone: ${selectedUser?.phone ?: "—"}"
            }

            div {
                +"Website: ${selectedUser?.website ?: "—"}"
            }
        }
    }
}
