package team.karakum.components


import csstype.*
import csstype.Auto.auto
import csstype.LineStyle.Companion.solid
import csstype.None.none
import emotion.react.css
import kotlinx.js.ReadonlyArray
import kotlinx.js.jso
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr
import react.key
import react.useContext
import tanstack.react.table.ColumnDefTemplate
import tanstack.react.table.flexRender
import tanstack.react.table.useReactTable
import tanstack.table.core.*
import team.karakum.Colors
import team.karakum.entities.User
import team.karakum.hooks.useCreateUser
import team.karakum.hooks.useUsers
import kotlin.random.Random.Default.nextInt

private val COLUMNS: ReadonlyArray<ColumnDef<User, *>> =
    arrayOf(
        jso {
            id = "name"
            header = ColumnDefTemplate("Name")
            accessorFn = { row, _ -> row.name }
        },
        jso<ColumnDef<User, String>> {
            id = "email"
            header = ColumnDefTemplate("E-mail")
            cell = ColumnDefTemplate(CustomCell)
            accessorFn = { row, _ -> row.email }
        },
    )

val UserTable = FC<Props> {
    val users = useUsers()
    val createUser = useCreateUser()
    val setSelectedUser = useContext(SetSelectedUserContext)

    val table = useReactTable(
        options = jso<TableOptions<User>> {
            data = users
            columns = COLUMNS
            getCoreRowModel = coreRowModel()
        }
    )

    div {
        button {
            onClick = { createUser(jso { id = "${nextInt()}" }) }

            +"Create New"
        }

        table {
            css {
                width = 400.px
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
                border = Border(2.px, solid, Colors.Stroke.Gray)
                margin = auto
            }

            thead {
                css {
                    color = Colors.Text.Gray
                    fontSize = 18.px
                    backgroundColor = Colors.Background.Gray
                }

                for (headerGroup in table.getHeaderGroups()) {
                    tr {
                        key = headerGroup.id

                        for (header in headerGroup.headers) {

                            th {
                                css {
                                    fontWeight = FontWeight.normal
                                    padding = Padding(4.px, 12.px)
                                    borderRight = Border(1.px, solid, Colors.Stroke.Gray)

                                    lastChild {
                                        borderRight = none
                                    }
                                }
                                key = header.id

                                +flexRender(header.column.columnDef.header, header.getContext())
                            }
                        }
                    }
                }
            }

            tbody {
                css {
                    color = Colors.Text.Black
                    backgroundColor = Colors.Background.White
                    textAlign = TextAlign.start
                }

                for (row in table.getRowModel().rows) {
                    tr {
                        css {
                            fontSize = 16.px
                            cursor = Cursor.pointer
                            borderBottom = Border(1.px, solid, Colors.Stroke.LightGray)
                            hover {
                                backgroundColor = Colors.Background.Gray
                            }
                        }

                        onClick = { setSelectedUser(row.original) }

                        for (cell in row.getVisibleCells()) {
                            td {
                                css {
                                    padding = Padding(10.px, 12.px)
                                }

                                key = cell.id

                                +flexRender(cell.column.columnDef.cell, cell.getContext())
                            }
                        }
                    }
                }
            }
        }
    }
}

// TODO update after wrappers update
private fun coreRowModel(): (Table<*>) -> () -> RowModel<*> = {
    val table = it.unsafeCast<Table<User>>()
    val result = getCoreRowModel<User>()(table)
    result.unsafeCast<() -> RowModel<*>>()
}
