package component

import data.Users
import hook.useUsers
import react.*

val UsersContext = createContext<Users>()

external interface UserModuleProps : RProps

private val UsersModule = functionalComponent<UserModuleProps> { props ->

    val users = useUsers()

    UsersContext.Provider(users) {
        props.children()
    }

}

fun RBuilder.UsersModule(
    handler: RHandler<UserModuleProps>,
): ReactElement =
    child(
        component = UsersModule,
        handler = handler,
    )
