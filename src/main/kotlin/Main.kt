import component.*
import kotlinx.browser.document
import react.Fragment
import react.create
import react.dom.render

fun main() {
    val container = document.createElement("div").also {
        document.body!!.appendChild(it)
    }

    val application = Fragment.create {
        UsersModule {
            SelectionModule {
                Header()
                LoadingIndicator()
                UserInfo()
            }
        }
    }

    render(application, container)
}
