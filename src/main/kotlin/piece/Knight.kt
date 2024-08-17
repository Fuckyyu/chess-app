package piece

import chess.Color
import chess.Coordinates
import chess.CoordinatesShift
import kotlin.collections.HashSet

class Knight(color: Color, coordinates: Coordinates) : Piece(color, coordinates) {
    override fun getPieceMoves(): Set<CoordinatesShift> {
        return HashSet(
            listOf(
                CoordinatesShift(1, 2),
                CoordinatesShift(2, 1),

                CoordinatesShift(2, -1),
                CoordinatesShift(1, -2),

                CoordinatesShift(-2, -1),
                CoordinatesShift(-1, -2),

                CoordinatesShift(-2, 1),
                CoordinatesShift(-1, 2)
            )
        )
    }

}