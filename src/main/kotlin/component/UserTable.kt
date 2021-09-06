package component

import Colors
import data.User
import extraAttrs
import kotlinext.js.jsObject
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.tr
import react.table.RenderType
import react.table.columns
import react.table.useTable
import styled.*

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
        options = jsObject {
            this.data = users
            this.columns = columns
        }
    )

    styledDiv {
        styledTable {
            extraAttrs = table.getTableProps()

            css {
                width = 400.px
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
                borderWidth = 2.px
                borderStyle = BorderStyle.solid
                borderColor = Colors.Stroke.Gray
                margin(LinearDimension.auto)
            }
            styledThead {
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

                            styledTh {
                                extraAttrs = header.getHeaderProps()

                                css {
                                    fontWeight = FontWeight.normal
                                    padding(4.px, 12.px)
                                    borderRight = solid(Colors.Stroke.Gray)

                                    if (header.columns != null) {
                                        borderBottom = solid(Colors.Stroke.Gray)
                                    }

                                    lastChild {
                                        borderRight = "none"
                                    }
                                }
                                +header.render(RenderType.Header)
                            }
                        }
                    }
                }
            }
            styledTbody {
                extraAttrs = table.getTableBodyProps()

                css {
                    color = Colors.Text.Black
                    backgroundColor = Colors.Background.White
                    textAlign = TextAlign.start
                }
                for (row in table.rows) {
                    table.prepareRow(row)

                    styledTr {

                        extraAttrs = row.getRowProps()
                        attrs.onClickFunction = { onRowClick(row.original) }

                        css {
                            fontSize = 16.px
                            cursor = Cursor.pointer
                            borderBottom = solid(Colors.Stroke.LightGray)
                            hover {
                                backgroundColor = Colors.Background.Gray
                            }
                        }
                        for (cell in row.cells) {
                            styledTd {
                                extraAttrs = cell.getCellProps()

                                css {
                                    padding(10.px, 12.px)
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
    thickness: Int = 1,
): String =
    "${thickness}px solid $color"
