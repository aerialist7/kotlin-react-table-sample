package example

import example.component.*
import kotlinx.browser.document
import react.FC
import react.Props
import react.create
import react.dom.render

fun main() {
    val container = document.createElement("div")
    document.body!!.appendChild(container)

    render(App.create(), container)
}

private val App = FC<Props> {
    UsersModule {
        SelectionModule {
            Header()
            LoadingIndicator()
            UserInfo()
        }
    }
}
