package piece

import chess.*

class Rook(color: Color, coordinates: Coordinates) : LongRangePiece(color, coordinates), IRook {
    override fun getPieceMoves(): Set<CoordinatesShift> {
        return getRookMoves()
    }
}