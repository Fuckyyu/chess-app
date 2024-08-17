package chess

enum class GameState {
    ONGOING,
    STALEMATE,
    CHECKMATE_TO_WHITE_KING,
    CHECKMATE_TO_BLACK_KING;
}