package example

import example.component.*
import kotlinx.browser.document
import react.FC
import react.Props
import react.create
import react.dom.render

val Application = FC<Props> {
    UsersModule {
        SelectionModule {
            Header()
            LoadingIndicator()
            UserInfo()
        }
    }
}

fun main() {
    val container = document.createElement("div")
    document.body!!.appendChild(container)

    render(Application.create(), container)
}
