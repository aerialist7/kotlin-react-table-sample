package io.github.aerialist7.components

import io.github.aerialist7.entities.User
import react.*

private val SelectedUserContext: Context<User?> =
    createContext()

private val SetSelectedUserContext: RequiredContext<StateSetter<User?>> =
    createRequiredContext()

fun useSelectedUser(): User? =
    use(SelectedUserContext)

fun useSetSelectedUser(): StateSetter<User?> =
    useRequired(SetSelectedUserContext)

val SelectedUserModule = FC<PropsWithChildren> { props ->
    val (user, setUser) = useState<User>()

    SelectedUserContext(user) {
        SetSelectedUserContext(setUser) {
            +props.children
        }
    }
}
