package team.karakum.components

import csstype.FontWeight
import csstype.Overflow
import csstype.Padding
import csstype.px
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import team.karakum.Colors

val Header = FC<Props> {
    div {
        css {
            overflow = Overflow.hidden
            backgroundColor = Colors.Background.Gray
            padding = Padding(20.px, 10.px)
        }

        a {
            css {
                fontSize = 25.px
                fontWeight = FontWeight.bold
            }

            +"Kotlin React Table"
        }
    }
}
