package example.component

import example.QueryKeys
import react.FC
import react.Props
import react.query.useIsFetching

typealias LoadingIndicatorProps = Props

val LoadingIndicator = FC<LoadingIndicatorProps> {
    val isFetchingUsers = useIsFetching(QueryKeys.USERS.name)

    if (isFetchingUsers != 0)
        +"Loading..."
}
