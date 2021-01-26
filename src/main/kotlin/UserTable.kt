import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.tr
import react.functionalComponent
import react.table.RenderType
import react.table.columns
import react.table.useTable
import react.useCallback
import react.useMemo
import styled.*

val UserTable = functionalComponent<RProps> {
    val users = useUsers()

    val onRowClick = useCallback {
        { user: User -> window.alert(user.name) }
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

    val table = useTable<User> {
        this.data = users
        this.columns = columns
    }

    styledDiv {
        styledTable {
            attrs {
                extraAttrs = table.getTableProps()
            }
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
                        attrs {
                            extraAttrs = headerGroup.getHeaderGroupProps()
                        }
                        for (h in headerGroup.headers) {
                            val originalHeader = h.placeholderOf
                            val header = originalHeader ?: h

                            styledTh {
                                attrs {
                                    extraAttrs = header.getHeaderProps()
                                }
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
                attrs {
                    extraAttrs = table.getTableBodyProps()
                }
                css {
                    color = Colors.Text.Black
                    backgroundColor = Colors.Background.White
                    textAlign = TextAlign.start
                }
                for (row in table.rows) {
                    table.prepareRow(row)

                    styledTr {
                        attrs {
                            extraAttrs = row.getRowProps()
                            onClickFunction = { onRowClick()(row.original) }
                        }
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
                                attrs {
                                    extraAttrs = cell.getCellProps()
                                }

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
