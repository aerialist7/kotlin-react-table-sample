import kotlinx.browser.document
import react.child
import react.dom.render

fun main() {
    render(document.getElementById("root")) {
        child(UserTable) {
            attrs.users = arrayOf(
                User("Victor", 32),
                User("Alexander", 26),
                User("Catherine", 25),
                User("Nicolas", 25),
            )
        }
    }
}
