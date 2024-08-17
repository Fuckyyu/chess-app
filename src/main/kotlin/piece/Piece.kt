package piece

import board.Board
import chess.Color
import chess.Coordinates
import chess.CoordinatesShift

abstract class Piece(val color: Color, var coordinates: Coordinates) {
    fun getAvailableMoveSquares(board: Board): Set<Coordinates> {
        val result: MutableSet<Coordinates> = HashSet()
        for (shift in getPieceMoves()) {
            if (coordinates.canShift(shift)) {
                val newCoordinates = coordinates.shift(shift)

                if (isSquareAvailableForMove(newCoordinates, board)) {

                    result.add(newCoordinates)
                }
            }
        }
        return result
    }

    protected open fun isSquareAvailableForMove(coordinates: Coordinates, board: Board): Boolean {
        return board.isSquareEmpty(coordinates) || board.getPiece(coordinates)?.color != color
    }

    protected abstract fun getPieceMoves(): Set<CoordinatesShift>

    protected open fun getPieceAttacks(): Set<CoordinatesShift> {
        return getPieceMoves()
    }

    fun getAttackedSquares(board: Board): Set<Coordinates> {
        val pieceAttacks = getPieceAttacks()
        val result = mutableSetOf<Coordinates>()

        for (pieceAttack in pieceAttacks) {
            if (coordinates.canShift(pieceAttack)) {
                val shiftedCoordinates = coordinates.shift(pieceAttack)

                if (isSquareAvailableForAttack(shiftedCoordinates, board)) {
                    result.add(shiftedCoordinates)
                }
            }
        }

        return result
    }

    protected open fun isSquareAvailableForAttack(coordinates: Coordinates, board: Board): Boolean {
        return true
    }
}

