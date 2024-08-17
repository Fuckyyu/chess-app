package piece

import board.Board
import board.BoardUtils
import chess.Color
import chess.Coordinates

abstract class LongRangePiece(color: Color, coordinates: Coordinates) : Piece(color, coordinates) {
    override fun isSquareAvailableForMove(coordinates: Coordinates, board: Board): Boolean {
        val result = super.isSquareAvailableForMove(coordinates, board)

        return if (result) {
            isSquareAvailableForAttack(coordinates, board)
        } else {
            false
        }
    }

    override fun isSquareAvailableForAttack(coordinates: Coordinates, board: Board): Boolean {
        val coordinatesBetween: List<Coordinates> = when {
            this.coordinates.file == coordinates.file -> BoardUtils.getVerticalCoordinatesBetween(
                this.coordinates,
                coordinates
            )

            this.coordinates.rank == coordinates.rank -> BoardUtils.getHorizontalCoordinatesBetween(
                this.coordinates,
                coordinates
            )

            else -> BoardUtils.getDiagonalCoordinatesBetween(this.coordinates, coordinates)
        }

        for (c in coordinatesBetween) {
            if (!board.isSquareEmpty(c)) {
                return false
            }
        }

        return true
    }
}
