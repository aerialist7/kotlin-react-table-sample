package io.github.aerialist7.components

import emotion.react.css
import io.github.aerialist7.Colors
import io.github.aerialist7.entities.User
import io.github.aerialist7.hooks.useCreateUser
import io.github.aerialist7.hooks.useUsers
import js.objects.unsafeJso
import react.FC
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr
import tanstack.react.table.renderCell
import tanstack.react.table.renderHeader
import tanstack.react.table.useReactTable
import tanstack.table.core.ColumnDef
import tanstack.table.core.StringOrTemplateHeader
import tanstack.table.core.TableOptions
import tanstack.table.core.getCoreRowModel
import web.cssom.*
import web.cssom.Auto.Companion.auto
import web.cssom.LineStyle.Companion.solid
import web.cssom.None.Companion.none
import kotlin.random.Random.Default.nextInt

val UserTable = FC {
    val users = useUsers()
    val createUser = useCreateUser()
    val setSelectedUser = useSetSelectedUser()

    val table = useReactTable(
        options = TableOptions(
            data = users,
            columns = arrayOf<ColumnDef<User, String>>(
                unsafeJso {
                    id = "name"
                    header = StringOrTemplateHeader("Name")
                    accessorFn = { row, _ -> row.name }
                },
                unsafeJso {
                    id = "email"
                    header = StringOrTemplateHeader("E-mail")
                    accessorFn = { row, _ -> row.email }
                },
            ),
            getCoreRowModel = getCoreRowModel(),
        )
    )

    div {
        button {
            onClick = { createUser(unsafeJso { id = "${nextInt()}" }) }

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
                        for (header in headerGroup.headers) {
                            th {
                                css {
                                    fontWeight = FontWeight.normal
                                    padding = Padding(4.px, 12.px)
                                    borderRight = Border(1.px, solid, Colors.Stroke.Gray)
                                    borderBottom = Border(1.px, solid, Colors.Stroke.Gray)

                                    lastChild {
                                        borderRight = none
                                    }
                                }

                                +renderHeader(header)
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

                                +renderCell(cell)
                            }
                        }
                    }
                }
            }
        }
    }
}
