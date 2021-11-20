package component

import Colors
import csstype.*
import data.User
import extraAttrs
import kotlinext.js.jso
import react.*
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

typealias UserTableProps = Props

val UserTable = fc<UserTableProps> {
    val users = useContext(UsersContext)
    val (_, setSelectionKey) = useContext(SelectionContext)

    val onRowClick = useCallback { user: User ->
        setSelectionKey(user.username)
    }

    val columns = useMemo {
        columns<User> {
            column<String> {
                header = "Name"
                accessorFunction = { it.name }
            }
            column<String> {
                header = "E-mail"
                accessorFunction = { it.email }
            }
        }
    }

    val table = useTable<User>(
        options = jso {
            this.data = users
            this.columns = columns
        }
    )

    div {
        table {
            extraAttrs = table.getTableProps()

            css {
                width = 400.px
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
                border = solid(Colors.Stroke.Gray, 2)
                margin = Length.auto
            }
            thead {
                css {
                    color = Colors.Text.Gray
                    fontSize = 18.px
                    backgroundColor = Colors.Background.Gray
                }
                for (headerGroup in table.headerGroups) {
                    tr {
                        extraAttrs = headerGroup.getHeaderGroupProps()

                        for (h in headerGroup.headers) {
                            val originalHeader = h.placeholderOf
                            val header = originalHeader ?: h

                            th {
                                extraAttrs = header.getHeaderProps()

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
                                +header.render(RenderType.Header)
                            }
                        }
                    }
                }
            }
            tbody {
                extraAttrs = table.getTableBodyProps()

                css {
                    color = Colors.Text.Black
                    backgroundColor = Colors.Background.White
                    textAlign = TextAlign.start
                }
                for (row in table.rows) {
                    table.prepareRow(row)

                    tr {

                        extraAttrs = row.getRowProps()
                        attrs.onClick = { onRowClick(row.original) }

                        css {
                            fontSize = 16.px
                            cursor = Cursor.pointer
                            borderBottom = solid(Colors.Stroke.LightGray)
                            hover {
                                backgroundColor = Colors.Background.Gray
                            }
                        }
                        for (cell in row.cells) {
                            td {
                                extraAttrs = cell.getCellProps()

                                css {
                                    padding = Padding(10.px, 12.px)
                                }

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
