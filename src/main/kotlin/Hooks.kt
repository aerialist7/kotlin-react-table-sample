import react.useCallback
import react.useMemo

fun <T> useMemo(
    vararg dependencies: dynamic,
    callback: () -> T,
): T =
    useMemo(callback, dependencies)

fun <T : Function<*>> useCallback(
    vararg dependencies: dynamic,
    callback: () -> T,
): () -> T =
    useCallback(callback, dependencies)
