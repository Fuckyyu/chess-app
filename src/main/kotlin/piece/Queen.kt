package piece

import chess.Color
import chess.Coordinates
import chess.CoordinatesShift

class Queen(color: Color, coordinates: Coordinates) : LongRangePiece(color, coordinates), IBishop, IRook {
    override fun getPieceMoves(): Set<CoordinatesShift> {
        val moves: MutableSet<CoordinatesShift> = getBishopMoves().toMutableSet()
        moves.addAll(getRookMoves())

        return moves
    }
}
