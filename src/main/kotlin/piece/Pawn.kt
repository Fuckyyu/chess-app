package piece

import board.Board
import board.BoardUtils
import chess.Color
import chess.Coordinates
import chess.CoordinatesShift
import kotlin.math.abs

class Pawn(color: Color, coordinates: Coordinates) : Piece(color, coordinates) {
    override fun getPieceMoves(): Set<CoordinatesShift> {
        val result = mutableSetOf<CoordinatesShift>()

        if (color == Color.WHITE) {
            result.add(CoordinatesShift(0, 1))
            if (coordinates.rank == 2) {
                result.add(CoordinatesShift(0, 2))
            }

            result.add(CoordinatesShift(-1, 1))
            result.add(CoordinatesShift(1, 1))
        } else {
            result.add(CoordinatesShift(0, -1))

            if (coordinates.rank == 7) {
                result.add(CoordinatesShift(0, -2))
            }

            result.add(CoordinatesShift(-1, -1))
            result.add(CoordinatesShift(1, -1))
        }
        return result
    }

    override fun getPieceAttacks(): Set<CoordinatesShift> {
        val result: MutableSet<CoordinatesShift> = HashSet()

        if (color == Color.WHITE) {
            result.add(CoordinatesShift(-1, 1))
            result.add(CoordinatesShift(1, 1))
        } else {
            result.add(CoordinatesShift(-1, -1))
            result.add(CoordinatesShift(1, -1))
        }
        return result
    }

    override fun isSquareAvailableForMove(coordinates: Coordinates, board: Board): Boolean {
        return if (this.coordinates.file == coordinates.file) {
            val rankShift = abs(this.coordinates.rank - coordinates.rank)

            if (rankShift == 2) {
                val between = BoardUtils.getVerticalCoordinatesBetween(this.coordinates, coordinates)

                return (board.isSquareEmpty(between[0])) && board.isSquareEmpty(coordinates)
            } else {
                board.isSquareEmpty(coordinates)
            }
        } else {
            if (board.isSquareEmpty(coordinates)) {
                false
            } else {
                board.getPiece(coordinates)!!.color != color
            }
        }
    }
}