package example.component

import csstype.*
import csstype.Auto.auto
import csstype.LineStyle.Companion.solid
import csstype.None.none
import emotion.react.css
import example.Colors
import example.data.User
import example.hook.useCreateUser
import example.hook.useUsers
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
import react.table.RenderType
import react.table.columns
import react.table.useTable
import react.useContext
import kotlin.random.Random.Default.nextInt

private val COLUMNS = columns<User> {
    column<String> {
        header = "Name"
        accessorFunction = { it.name }
    }
    column<String> {
        header = "E-mail"
        accessorFunction = { it.email }
    }
}

val UserTable = FC<Props> {
    val users = useUsers()
    val createUser = useCreateUser()
    val setSelectedUser = useContext(SetSelectedUserContext)

    val table = useTable<User>(
        options = jso {
            data = users
            columns = COLUMNS
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

            +table.getTableProps()

            thead {
                css {
                    color = Colors.Text.Gray
                    fontSize = 18.px
                    backgroundColor = Colors.Background.Gray
                }

                for (headerGroup in table.headerGroups) {
                    tr {
                        +headerGroup.getHeaderGroupProps()

                        for (h in headerGroup.headers) {
                            val originalHeader = h.placeholderOf
                            val header = originalHeader ?: h

                            th {
                                css {
                                    fontWeight = FontWeight.normal
                                    padding = Padding(4.px, 12.px)
                                    borderRight = Border(1.px, solid, Colors.Stroke.Gray)

                                    if (header.columns != null) {
                                        borderBottom = Border(1.px, solid, Colors.Stroke.Gray)
                                    }

                                    lastChild {
                                        borderRight = none
                                    }
                                }

                                +header.getHeaderProps()
                                +header.render(RenderType.Header)
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

                +table.getTableBodyProps()

                for (row in table.rows) {
                    table.prepareRow(row)

                    tr {
                        css {
                            fontSize = 16.px
                            cursor = Cursor.pointer
                            borderBottom = Border(1.px, solid, Colors.Stroke.LightGray)
                            hover {
                                backgroundColor = Colors.Background.Gray
                            }
                        }

                        +row.getRowProps()
                        onClick = { setSelectedUser(row.original) }

                        for (cell in row.cells) {
                            td {
                                css {
                                    padding = Padding(10.px, 12.px)
                                }

                                +cell.getCellProps()
                                +cell.render(RenderType.Cell)
                            }
                        }
                    }
                }
            }
        }
    }
}
