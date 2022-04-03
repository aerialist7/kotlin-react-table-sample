package team.karakum

import kotlinx.browser.document
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.query.QueryClient
import react.query.QueryClientProvider
import team.karakum.components.Header
import team.karakum.components.LoadingIndicator
import team.karakum.components.UserInfo

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
