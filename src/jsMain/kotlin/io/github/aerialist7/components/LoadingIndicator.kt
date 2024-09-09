package io.github.aerialist7.components

import io.github.aerialist7.hooks.useUsers
import react.FC

val LoadingIndicator = FC {
    val users = useUsers()

    if (users.isEmpty())
        +"Loading..."
}
