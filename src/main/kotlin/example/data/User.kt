package example.data

external interface User {
    val id: Key
    val name: String
    val email: String
    val phone: String
    val website: String
}

typealias Users = Array<out User>
