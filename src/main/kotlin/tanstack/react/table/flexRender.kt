// TODO remove after wrappers update

@file:JsModule("@tanstack/react-table")
@file:JsNonModule

package tanstack.react.table

import react.ReactNode
import tanstack.table.core.ColumnDefTemplate

// TODO remove after wrappers update
external fun <T : Any> flexRender(
    Comp: ColumnDefTemplate<out () -> T>?,
    props: T,
): ReactNode?
