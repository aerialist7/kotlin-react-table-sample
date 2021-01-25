import react.RCleanup
import react.useCallback
import react.useEffectWithCleanup
import react.useMemo

// TODO: Migrate on default
fun <T> useMemo(
    vararg dependencies: dynamic,
    callback: () -> T,
): T =
    useMemo(callback, dependencies)

// TODO: Migrate on default
fun <T : Function<*>> useCallback(
    vararg dependencies: dynamic,
    callback: () -> T,
): () -> T =
    useCallback(callback, dependencies)

// TODO: Bug in standard?
fun useEffectWithCleanup(
    effect: () -> RCleanup,
) = useEffectWithCleanup(emptyList(), effect)
