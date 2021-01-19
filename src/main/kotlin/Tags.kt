import kotlinext.js.Object
import kotlinx.html.Tag
import react.RProps

var Tag.extraAttrs: RProps
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "write only")
    get() = error("write only")
    set(value) {
        for (key in Object.keys(value)) {
            @Suppress("UnsafeCastFromDynamic")
            attributes[key] = value.asDynamic()[key]
        }
    }
