import react.RCleanup
import react.rawUseEffect

fun useEffectWithCleanup(
    vararg dependencies: dynamic,
    callback: () -> RCleanup,
) = rawUseEffect(callback, dependencies)
