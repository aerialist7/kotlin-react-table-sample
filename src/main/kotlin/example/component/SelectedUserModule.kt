package example.component

import example.data.User
import react.*

val SelectedUserContext = createContext<StateInstance<User?>>()

val SelectedUserModule = FC<PropsWithChildren> { props ->
    val selectedUserState = useState<User>()

    SelectedUserContext.Provider(selectedUserState) {
        props.children()
    }
}
