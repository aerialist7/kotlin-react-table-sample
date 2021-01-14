import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.tr
import react.functionalComponent
import react.table.RenderType
import react.table.columns
import react.table.useTable
import styled.*

external interface UserTableProps : RProps {
    var headers: Array<String>
    var users: Array<User>
}

val UserTable = functionalComponent<UserTableProps> { props ->
    val onRowClick = { user: User ->
        window.alert(user.name)
    }

    val table = useTable<User> {
        data = props.users
        columns = columns {
            column<String> {
                header = props.headers[0]
                accessorFunction = { it.name }
            }
            column<Int> {
                header = props.headers[1]
                accessorFunction = { it.age }
            }
        }
    }

    styledDiv {
        styledTable {
            css {
                width = 100.pct
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
            }
            attrs {
                extraAttrs = table.getTableProps()
            }
            styledThead {
                css {
                    color = Colors.Text.Gray
                    fontSize = 12.px
                    backgroundColor = Colors.Background.Gray
                }

                for (headerGroup in table.headerGroups) {
                    tr {
                        attrs {
                            extraAttrs = headerGroup.getHeaderGroupProps()
                        }

                        for (h in headerGroup.headers) {
                            val originalHeader = h.placeholderOf
                            val header = originalHeader ?: h

                            styledTh {
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

                                attrs {
                                    extraAttrs = header.getHeaderProps()
                                }

                                +header.render(RenderType.Header)
                            }
                        }
                    }
                }
            }
            styledTbody {
                css {
                    color = Colors.Text.Black
                    backgroundColor = Colors.Background.White
                    textAlign = TextAlign.center
                }

                attrs {
                    extraAttrs = table.getTableBodyProps()
                }

                for (row in table.rows) {
                    table.prepareRow(row)

                    styledTr {
                        css {
                            fontSize = 14.px
                            cursor = Cursor.pointer
                            borderBottom = solid(Colors.Stroke.LightGray)
                            hover {
                                backgroundColor = Colors.Background.Gray
                            }
                        }

                        attrs {
                            extraAttrs = row.getRowProps()
                            onClickFunction = { onRowClick(row.original) }
                        }

                        for (cell in row.cells) {
                            styledTd {
                                css {
                                    padding(10.px, 12.px)
                                }

                                attrs {
                                    extraAttrs = cell.getCellProps()
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
