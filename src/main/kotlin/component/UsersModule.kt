package component

import data.Users
import hook.useUsers
import react.FC
import react.PropsWithChildren
import react.createContext

val UsersContext = createContext<Users>()

external interface UserModuleProps : PropsWithChildren

val UsersModule = FC<UserModuleProps> { props ->

    val users = useUsers()

    UsersContext.Provider(users) {
        props.children()
    }

}
