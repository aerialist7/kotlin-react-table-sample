package data

external interface User {
    val name: String
    val email: String
}

typealias Users = Array<out User>
