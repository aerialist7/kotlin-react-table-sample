package example.component

import example.data.Key
import react.*

data class Selection(
    val value: Key?,
    val setValue: StateSetter<Key?>,
)

val SelectionContext = createContext<Selection>()

external interface SelectionModuleProps : PropsWithChildren

val SelectionModule = FC<SelectionModuleProps> { props ->
    val (selectedKey, setSelectedKey) = useState<Key?>(null)
    val selection = useMemo(selectedKey, setSelectedKey) {
        Selection(selectedKey, setSelectedKey)
    }

    SelectionContext.Provider(selection) {
        props.children()
    }
}
