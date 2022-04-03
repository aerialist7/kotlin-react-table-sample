package team.karakum.components

import react.FC
import react.Props
import team.karakum.hooks.useUsers

typealias LoadingIndicatorProps = Props

val LoadingIndicator = FC<LoadingIndicatorProps> {
    val users = useUsers()

    if (users.isEmpty())
        +"Loading..."
}
