package dev.clipstack.analytics

interface Analytics {
    fun track(event: String, props: Map<String, String> = emptyMap())
}

object NoOpAnalytics : Analytics {
    override fun track(event: String, props: Map<String, String>) = Unit
}

class LoggingAnalytics(
    private val sink: (String) -> Unit = { println("analytics: $it") },
) : Analytics {
    override fun track(event: String, props: Map<String, String>) {
        sink("$event $props")
    }
}
