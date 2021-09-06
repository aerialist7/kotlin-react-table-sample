import component.*
import kotlinx.browser.document
import react.dom.render

fun main() {
    render(document.getElementById("root")) {
        UsersModule {
            SelectionModule {
                Header()
                LoadingIndicator()
                UserInfo()
            }
        }
    }
}
