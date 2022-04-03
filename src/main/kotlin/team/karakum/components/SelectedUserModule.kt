package team.karakum.components

import react.*
import team.karakum.entities.User

val SelectedUserContext = createContext<User?>()
val SetSelectedUserContext = createContext<StateSetter<User?>>()

val SelectedUserModule = FC<PropsWithChildren> { props ->
    val (user, setUser) = useState<User>()

    SelectedUserContext.Provider(user) {
        SetSelectedUserContext.Provider(setUser) {
            +props.children
        }
    }
}
