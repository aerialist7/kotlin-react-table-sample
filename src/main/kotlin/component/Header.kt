package component

import Colors
import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.TextDecoration
import react.Props
import react.fc
import styled.css
import styled.styledA
import styled.styledDiv

// TODO: Return external interface's after wrappers upgrade
typealias HeaderProps = Props

val Header = fc<HeaderProps> {
    styledDiv {
        css {
            overflow = Overflow.hidden
            backgroundColor = Colors.Background.Gray
            padding = "20px 10px"
        }

        styledA {
            css {
                fontSize = 25.px
                fontWeight = FontWeight.bold
            }
            +"Kotlin React Table"
        }

        styledDiv {
            css {
                float = Float.right
            }

            styledA {
                css {
                    padding = "12px"
                    textDecoration = TextDecoration.none
                    fontSize = 18.px
                    lineHeight = LineHeight("25px")
                }
                attrs.href = "https://github.com/aerialist7"
                +"by @aerialist7"
            }
        }
    }
}
