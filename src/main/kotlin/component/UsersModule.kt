package component

import data.Users
import hook.useUsers
import react.RProps
import react.createContext
import react.fc

val UsersContext = createContext<Users>()

external interface UserModuleProps : RProps

val UsersModule = fc<UserModuleProps> { props ->

    val users = useUsers()

    UsersContext.Provider(users) {
        props.children()
    }

}
