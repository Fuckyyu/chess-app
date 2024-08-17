package piece

import chess.*

class Bishop(color: Color, coordinates: Coordinates) : LongRangePiece(color, coordinates), IBishop {
    override fun getPieceMoves(): Set<CoordinatesShift> {
        return getBishopMoves()
    }
}