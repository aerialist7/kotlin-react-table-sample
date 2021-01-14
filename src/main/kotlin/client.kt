import kotlinx.browser.document
import kotlinx.browser.window
import react.child
import react.dom.render

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(UserTable) {
                attrs {
                    headers = arrayOf(
                        "Name", "Age"
                    )
                    users = arrayOf(
                        User("Victor", 32),
                        User("Alexander", 26),
                        User("Catherine", 25),
                        User("Nicolas", 25),
                    )
                }
            }
        }
    }
}
