package team.karakum.components

import react.*
import team.karakum.entities.User

val SelectedUserContext: Context<User?> =
    createContext()

val SetSelectedUserContext: RequiredContext<StateSetter<User?>> =
    createRequiredContext()

val SelectedUserModule = FC<PropsWithChildren> { props ->
    val (user, setUser) = useState<User>()

    SelectedUserContext(user) {
        SetSelectedUserContext(setUser) {
            +props.children
        }
    }
}
