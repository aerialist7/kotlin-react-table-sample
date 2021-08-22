package component

import react.RProps
import react.fc
import react.useContext

external interface LoadingIndicatorProps : RProps

val LoadingIndicator = fc<LoadingIndicatorProps> {
    val users = useContext(UsersContext)

    when {
        users.isEmpty() -> +"Loading..."
        else -> +""
    }
}
