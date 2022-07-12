package team.karakum.components

import react.FC
import react.Props
import react.dom.html.ReactHTML.span
import tanstack.table.core.CoreCell
import team.karakum.entities.User

external interface CustomCellProps : CoreCell.Context<User, String>, Props

val CustomCell = FC<CustomCellProps> { props ->
    span {
        +props.getValue()
    }
}
