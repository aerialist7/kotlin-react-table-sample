import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.tr
import react.functionalComponent
import react.table.RenderType
import react.table.columns
import react.table.useTable
import react.useState
import styled.*

val UserTable = functionalComponent<RProps> {
    val (data, setData) = useState<Array<User>>(emptyArray())

    useEffectWithCleanup {
        GlobalScope.launch {
            setData(fetchData())
        }::cancel
    }

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
        this.data = data
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
                    textAlign = TextAlign.start
                }

                attrs {
                    extraAttrs = table.getTableBodyProps()
                }

                for (row in table.rows) {
                    table.prepareRow(row)

                    styledTr {
                        css {
                            fontSize = 16.px
                            cursor = Cursor.pointer
                            borderBottom = solid(Colors.Stroke.LightGray)
                            hover {
                                backgroundColor = Colors.Background.Gray
                            }
                        }

                        attrs {
                            extraAttrs = row.getRowProps()
                            onClickFunction = { onRowClick()(row.original) }
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

private suspend fun fetchData(): Array<User> =
    window.fetch("https://jsonplaceholder.typicode.com/users")
        .await()
        .json()
        .await()
        .unsafeCast<Array<User>>()
