package pappel.http

/**
 * Enum type for HTTP methods.
 */
enum class Method(val value: String) {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
}