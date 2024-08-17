package board

import chess.Color
import chess.Coordinates
import chess.File
import piece.*

class Board(val startingFen: String) {
    private var pieces: MutableMap<Coordinates, Piece> = HashMap()

    val moves: MutableList<Move> = ArrayList()

    fun setPiece(coordinates: Coordinates, piece: Piece) {
        piece.coordinates = coordinates
        pieces[coordinates] = piece
    }

    private fun removePiece(coordinates: Coordinates) {
        pieces.remove(coordinates)
    }

    fun makeMove(move: Move) {
        val piece = getPiece(move.from)
        removePiece(move.from)
        if (piece != null) {
            setPiece(move.to, piece)
        }
        moves.add(move)
    }

    fun isSquareEmpty(coordinates: Coordinates): Boolean {
        return !pieces.containsKey(coordinates)
    }

    fun getPiece(coordinates: Coordinates): Piece? {
        return pieces[coordinates]
    }

    fun setupDefaultPiecesPositions() {
        for (file in File.entries) {
            File.entries.forEach { setPiece(Coordinates(it, 2), Pawn(Color.WHITE, Coordinates(it, 2))) }
            File.entries.forEach { setPiece(Coordinates(it, 7), Pawn(Color.BLACK, Coordinates(it, 7))) }

            // set rooks
            setPiece(Coordinates(File.A, 1), Rook(Color.WHITE, Coordinates(File.A, 1)))
            setPiece(Coordinates(File.H, 1), Rook(Color.WHITE, Coordinates(File.H, 1)))
            setPiece(Coordinates(File.A, 8), Rook(Color.BLACK, Coordinates(File.A, 8)))
            setPiece(Coordinates(File.H, 8), Rook(Color.BLACK, Coordinates(File.H, 8)))

            // set knights
            setPiece(Coordinates(File.B, 1), Knight(Color.WHITE, Coordinates(File.B, 1)))
            setPiece(Coordinates(File.G, 1), Knight(Color.WHITE, Coordinates(File.G, 1)))
            setPiece(Coordinates(File.B, 8), Knight(Color.BLACK, Coordinates(File.B, 8)))
            setPiece(Coordinates(File.G, 8), Knight(Color.BLACK, Coordinates(File.G, 8)))

            // set bishops
            setPiece(Coordinates(File.C, 1), Bishop(Color.WHITE, Coordinates(File.C, 1)))
            setPiece(Coordinates(File.F, 1), Bishop(Color.WHITE, Coordinates(File.F, 1)))
            setPiece(Coordinates(File.C, 8), Bishop(Color.BLACK, Coordinates(File.C, 8)))
            setPiece(Coordinates(File.F, 8), Bishop(Color.BLACK, Coordinates(File.F, 8)))

            // set queens
            setPiece(Coordinates(File.D, 1), Queen(Color.WHITE, Coordinates(File.D, 1)))
            setPiece(Coordinates(File.D, 8), Queen(Color.BLACK, Coordinates(File.F, 8)))

            // set kings
            setPiece(Coordinates(File.E, 1), King(Color.WHITE, Coordinates(File.E, 1)))
            setPiece(Coordinates(File.E, 8), King(Color.BLACK, Coordinates(File.E, 8)))
        }
    }

    companion object {
        fun isSquareDark(coordinates: Coordinates): Boolean {
            return ((coordinates.file.ordinal + 1) + coordinates.rank) % 2 == 0
        }
    }


    fun getPiecesByColor(color: Color): List<Piece> {

        val result = mutableListOf<Piece>()

        for (piece in pieces.values) {
            if (piece.color == color) {
                result.add(piece)
            }
        }
        return result
    }

    fun isSquareAttackedByColor(coordinates: Coordinates, color: Color): Boolean {

        val pieces = getPiecesByColor(color)
        return pieces.any { it.getAttackedSquares(this).contains(coordinates) }
    }
}