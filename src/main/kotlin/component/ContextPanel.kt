package component

import Colors
import data.User
import kotlinx.css.*
import react.*
import styled.css
import styled.styledDiv

external interface ContextPanelProps : RProps {
    var user: User?
}

private val ContextPanel = functionalComponent<ContextPanelProps> { props ->
    styledDiv {
        css {
            width = 400.px
            borderSpacing = 0.px
            borderCollapse = BorderCollapse.collapse
            whiteSpace = WhiteSpace.nowrap
            borderWidth = 2.px
            borderStyle = BorderStyle.solid
            borderColor = Colors.Stroke.Gray
            margin(LinearDimension.auto)
        }

        styledDiv {
            +"Name: ${props.user?.name ?: "—"}"
        }
        styledDiv {
            +"E-mail: ${props.user?.email ?: "—"}"
        }
        styledDiv {
            +"Phone: ${props.user?.phone ?: "—"}"
        }
        styledDiv {
            +"Website: ${props.user?.website ?: "—"}"
        }
    }

}

fun RBuilder.ContextPanel(
    handler: ContextPanelProps.() -> Unit,
): ReactElement =
    child(ContextPanel) { attrs(handler) }
