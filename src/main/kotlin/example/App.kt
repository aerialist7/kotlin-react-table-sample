package example

import example.component.Header
import example.component.LoadingIndicator
import example.component.UserInfo
import kotlinx.browser.document
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.query.QueryClient
import react.query.QueryClientProvider

fun main() {
    createRoot(document.createElement("div").also { document.body!!.appendChild(it) })
        .render(App.create())
}

private val queryClient = QueryClient()

private val App = FC<Props> {
    QueryClientProvider {
        client = queryClient

        Header()
        LoadingIndicator()
        UserInfo()
    }
}
