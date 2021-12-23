package example.component

import react.FC
import react.Props
import react.useContext

typealias LoadingIndicatorProps = Props

val LoadingIndicator = FC<LoadingIndicatorProps> {
    val users = useContext(UsersContext)

    if (users.isEmpty())
        +"Loading..."
}
