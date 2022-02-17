package example.component

import csstype.Display
import csstype.GridAutoFlow
import csstype.px
import example.QueryKeys
import example.data.Users
import kotlinx.browser.window
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div
import react.query.useQuery
import kotlin.js.Promise

typealias UserInfoProps = Props

val UserInfo = FC<UserInfoProps> {
    useQuery<Users, Error, Users, String>(
        queryKey = QueryKeys.USERS.name,
        queryFn = { getUsers() }
    )

    div {
        css {
            padding = 20.px
            display = Display.grid
            gridAutoFlow = GridAutoFlow.column
        }

        UserTable()
        UserPanel()
    }
}

private fun getUsers(): Promise<Users> =
    window.fetch("https://jsonplaceholder.typicode.com/users")
        .then { it.json() }
        .then { it.unsafeCast<Users>() }
