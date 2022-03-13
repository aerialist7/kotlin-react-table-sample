package example.component

import example.data.User
import react.*

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
