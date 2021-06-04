import component.Header
import component.UserInfo
import component.UsersModule
import kotlinx.browser.document
import react.dom.render

fun main() {
    render(document.getElementById("root")) {
        Header {}
        UsersModule {
            UserInfo {}
        }
    }
}
