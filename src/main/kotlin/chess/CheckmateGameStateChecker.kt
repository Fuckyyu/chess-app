package chess

import board.Board
import board.BoardFactory
import board.Move
import piece.King
import piece.Piece

class CheckmateGameStateChecker : GameStateChecker() {
    override fun check(board: Board, color: Color): GameState {
        // check if king in check
        // check that there is no move to prevent this check

        // we trust that there is king on the board
        val king: Piece = board.getPiecesByColor(color).filterIsInstance<King>().first()

        if (!board.isSquareAttackedByColor(king.coordinates, color.opposite())) {
            return GameState.ONGOING
        }
        val pieces = board.getPiecesByColor(color)
        for (piece in pieces) {
            val availableMoveSquares = piece.getAvailableMoveSquares(board)

            for (coordinates in availableMoveSquares) {
                val clone = BoardFactory().copy(board)
                clone.makeMove(Move(piece.coordinates, coordinates))

                val clonedKing: Piece = clone.getPiecesByColor(color).filterIsInstance<King>().first()

                if (!clone.isSquareAttackedByColor(clonedKing.coordinates, color.opposite())) {
                    return GameState.ONGOING
                }
            }
        }
        return if (color == Color.WHITE) {
            GameState.CHECKMATE_TO_WHITE_KING
        } else {
            GameState.CHECKMATE_TO_BLACK_KING
        }
    }
}