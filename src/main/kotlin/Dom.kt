import kotlinext.js.Object
import react.Props
import react.dom.RDOMBuilder
import react.dom.setProp

var RDOMBuilder<*>.extraAttrs: Props
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "write only")
    get() = error("write only")
    set(value) {
        for (key in Object.keys(value)) {
            setProp(key, value.asDynamic()[key])
        }
    }
