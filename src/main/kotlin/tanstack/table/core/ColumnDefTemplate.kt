// TODO remove after wrappers update

@file:Suppress(
    "NOTHING_TO_INLINE",
)

package tanstack.table.core

sealed external interface ColumnDefTemplate<TProps : Any> /* string | ((props: TProps) -> any) */

inline fun <TProps : Any> ColumnDefTemplate(
    source: String,
): ColumnDefTemplate<TProps> =
    source.unsafeCast<ColumnDefTemplate<TProps>>()
