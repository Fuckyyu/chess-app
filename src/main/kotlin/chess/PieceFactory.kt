package chess

import piece.*

class PieceFactory {
    fun fromFenChar(fenChar: Char, coordinates: Coordinates): Piece {
        when (fenChar) {
            'p' -> return Pawn(Color.BLACK, coordinates)
            'P' -> return Pawn(Color.WHITE, coordinates)

            'r' -> return Rook(Color.BLACK, coordinates)
            'R' -> return Rook(Color.WHITE, coordinates)

            'n' -> return Knight(Color.BLACK, coordinates)
            'N' -> return Knight(Color.WHITE, coordinates)

            'b' -> return Bishop(Color.BLACK, coordinates)
            'B' -> return Bishop(Color.WHITE, coordinates)

            'q' -> return Queen(Color.BLACK, coordinates)
            'Q' -> return Queen(Color.WHITE, coordinates)

            'k' -> return King(Color.BLACK, coordinates)
            'K' -> return King(Color.WHITE, coordinates)

            else -> throw RuntimeException("Unknown FEN char")
        }
    }
}