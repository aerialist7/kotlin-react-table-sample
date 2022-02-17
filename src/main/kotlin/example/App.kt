package example

import example.component.Header
import example.component.LoadingIndicator
import example.component.SelectionModule
import example.component.UserInfo
import kotlinx.browser.document
import react.FC
import react.Props
import react.create
import react.dom.render
import react.query.QueryClient
import react.query.QueryClientProvider

fun main() {
    val container = document.createElement("div")
    document.body!!.appendChild(container)

    render(App.create(), container)
}

private val App = FC<Props> {
    QueryClientProvider {
        client = queryClient

        SelectionModule {
            Header()
            LoadingIndicator()
            UserInfo()
        }
    }
}

private val queryClient = QueryClient()
