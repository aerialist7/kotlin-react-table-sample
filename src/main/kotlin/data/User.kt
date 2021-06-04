package data

external interface User {
    val username: Key
    val name: String
    val email: String
    val phone: String
    val website: String
}

typealias Users = Array<out User>
