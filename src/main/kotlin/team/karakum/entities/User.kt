package team.karakum.entities

import tanstack.table.core.RowData

external interface User:RowData {
    var id: Key
    var name: String
    var email: String
    var phone: String
    var website: String
}

typealias Users = Array<out User>
