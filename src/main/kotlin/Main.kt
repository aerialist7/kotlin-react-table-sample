import component.Header
import component.UserTable
import kotlinx.browser.document
import react.dom.render

fun main() {
    render(document.getElementById("root")) {
        Header {}
        UserTable {}
    }
}
