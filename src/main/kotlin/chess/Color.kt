package chess

enum class Color {
    WHITE, BLACK;

    fun opposite(): Color {
        return if (this == WHITE) BLACK else WHITE
    }
}