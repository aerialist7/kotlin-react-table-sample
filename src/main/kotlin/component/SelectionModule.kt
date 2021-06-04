package component

import data.Key
import react.*

data class Selection(
    val value: Key?,
    val setValue: RSetState<Key?>,
)

val SelectionContext = createContext<Selection>()

external interface SelectionModuleProps : RProps

private val SelectionModule = functionalComponent<SelectionModuleProps> { props ->
    val (selectedKey, setSelectedKey) = useState<Key?>(null)
    val selection = useMemo(selectedKey, setSelectedKey) {
        Selection(selectedKey, setSelectedKey)
    }
    SelectionContext.Provider(selection) {
        props.children()
    }
}

fun RBuilder.SelectionModule(
    handler: RHandler<SelectionModuleProps>,
): ReactElement =
    child(
        component = SelectionModule,
        handler = handler,
    )
