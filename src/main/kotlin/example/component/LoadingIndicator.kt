package example.component

import example.hook.useUsers
import react.FC
import react.Props

typealias LoadingIndicatorProps = Props

val LoadingIndicator = FC<LoadingIndicatorProps> {
    val users = useUsers()

    if (users.isEmpty())
        +"Loading..."
}
