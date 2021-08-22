package component

import react.*

external interface LoadingIndicatorProps : RProps

private val LoadingIndicator = fc<LoadingIndicatorProps> {
    val users = useContext(UsersContext)

    when {
        users.isEmpty() -> +"Loading..."
        else -> +""
    }
}

fun RBuilder.LoadingIndicator(
    handler: LoadingIndicatorProps.() -> Unit,
): ReactElement =
    child(LoadingIndicator) { attrs(handler) }
