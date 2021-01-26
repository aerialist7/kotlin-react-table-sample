import react.RCleanup
import react.useEffectWithCleanup

fun useEffectWithCleanup(
    vararg dependencies: dynamic,
    callback: () -> RCleanup,
) = useEffectWithCleanup(dependencies.toList(), callback)
