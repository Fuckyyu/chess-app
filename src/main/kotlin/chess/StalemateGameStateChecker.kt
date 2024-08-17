package chess

import board.Board

class StalemateGameStateChecker : GameStateChecker() {
    override fun check(board: Board, color: Color): GameState {
        val pieces = board.getPiecesByColor(color)

        for (piece in pieces) {
            val availableMoveSquares = piece.getAvailableMoveSquares(board)

            if (availableMoveSquares.isNotEmpty()) {
                return GameState.ONGOING
            }
        }
        return GameState.STALEMATE
    }
}