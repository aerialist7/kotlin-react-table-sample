package component

import Colors
import csstype.*
import react.Props
import react.css.css
import react.dom.ReactHTML.a
import react.dom.ReactHTML.div
import react.fc

// TODO: Return external interface's after wrappers upgrade
typealias HeaderProps = Props

val Header = fc<HeaderProps> {
    div {
        css {
            overflow = Overflow.hidden
            backgroundColor = Colors.Background.Gray
            padding(20.px, 10.px)
        }

        a {
            css {
                fontSize = 25.px
                fontWeight = FontWeight.bold
            }
            +"Kotlin React Table"
        }

        div {
            css {
                float = Float.right
            }

            a {
                css {
                    padding = 12.px
                    textDecoration = TextDecoration.none
                    fontSize = 18.px
                    lineHeight = 25.px
                }
                attrs.href = "https://github.com/aerialist7"
                +"by @aerialist7"
            }
        }
    }
}
