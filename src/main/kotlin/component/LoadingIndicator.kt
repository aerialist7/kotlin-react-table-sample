package component

import react.Props
import react.fc
import react.useContext

typealias LoadingIndicatorProps = Props

val LoadingIndicator = fc<LoadingIndicatorProps> {
    val users = useContext(UsersContext)

    when {
        users.isEmpty() -> +"Loading..."
        else -> +""
    }
}
