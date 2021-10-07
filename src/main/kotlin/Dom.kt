import kotlinext.js.Object
import react.Props
import react.RElementBuilder

var RElementBuilder<*>.extraAttrs: Props
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "write only")
    get() = error("write only")
    set(value) {
        for (key in Object.keys(value)) {
            attrs.asDynamic()[key] = value.asDynamic()[key]
        }
    }