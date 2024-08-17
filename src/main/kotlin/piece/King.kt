package piece

import board.Board
import chess.Color
import chess.Coordinates
import chess.CoordinatesShift

class King(color: Color, coordinates: Coordinates) : Piece(color, coordinates) {
    override fun getPieceMoves(): Set<CoordinatesShift> {
        val result: MutableSet<CoordinatesShift> = HashSet()

        for (fileShift in -1..1) {
            for (rankShift in -1..1) {
                if ((fileShift == 0 && rankShift == 0)) {
                    continue
                }

                result.add(CoordinatesShift(fileShift, rankShift))
            }
        }

        return result
    }

    override fun isSquareAvailableForMove(coordinates: Coordinates, board: Board): Boolean {
        val result = super.isSquareAvailableForMove(coordinates, board)

        if (result) {
            return !board.isSquareAttackedByColor(coordinates, color.opposite())
        }
        return false
    }
}