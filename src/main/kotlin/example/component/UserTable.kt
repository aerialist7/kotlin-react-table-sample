package example.component

import csstype.*
import example.Colors
import example.data.User
import kotlinext.js.jso
import react.FC
import react.Props
import react.css.css
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
import react.useCallback
import react.useContext

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
    val users = useContext(UsersContext)
    val (_, setSelectionKey) = useContext(SelectionContext)

    val onRowClick = useCallback { user: User ->
        setSelectionKey(user.username)
    }

    val table = useTable<User>(
        options = jso {
            data = users
            columns = COLUMNS
        }
    )

    div {
        table {
            css {
                width = 400.px
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
                border = solid(Colors.Stroke.Gray, 2)
                margin = Length.auto
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
                                    borderRight = solid(Colors.Stroke.Gray)

                                    if (header.columns != null) {
                                        borderBottom = solid(Colors.Stroke.Gray)
                                    }

                                    lastChild {
                                        borderRight = LineStyle.none
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
                            borderBottom = solid(Colors.Stroke.LightGray)
                            hover {
                                backgroundColor = Colors.Background.Gray
                            }
                        }

                        +row.getRowProps()
                        onClick = { onRowClick(row.original) }

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

private fun solid(
    color: Color,
    length: Int = 1,
): Border =
    "${length}px solid $color"
        .unsafeCast<Border>()
