package component

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.TextDecoration
import react.RProps
import react.functionalComponent
import styled.css
import styled.styledA
import styled.styledDiv

val Header = functionalComponent<RProps> {
    styledDiv {
        css {
            overflow = Overflow.hidden
            backgroundColor = Color("#f1f1f1")
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
