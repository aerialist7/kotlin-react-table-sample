import kotlinext.js.Object
import react.RProps
import react.dom.RDOMBuilder

var RDOMBuilder<*>.extraAttrs: RProps
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "write only")
    get() = error("write only")
    set(value) {
        for (key in Object.keys(value)) {
            setProp(key, value.asDynamic()[key])
        }
    }
