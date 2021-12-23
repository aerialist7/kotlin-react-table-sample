package example.component

import csstype.FontWeight
import csstype.Overflow
import csstype.Padding
import csstype.px
import example.Colors
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div

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
