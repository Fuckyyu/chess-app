package board

import chess.Color
import chess.Coordinates
import chess.File
import piece.Piece

class BoardConsoleRenderer {
    companion object {
        const val ANSI_RESET: String = "\u001B[0m"
        const val ANSI_WHITE_PIECE_COLOR: String = "\u001B[97m"
        const val ANSI_BLACK_PIECE_COLOR: String = "\u001B[30m"

        const val ANSI_WHITE_SQUARE_BACKGROUND: String = "\u001B[47m"
        const val ANSI_BLACK_SQUARE_BACKGROUND: String = "\u001B[0;100m"
        const val ANSI_HIGHLIGHTED_SQUARE_BACKGROUND: String = "\u001B[45m"
    }

    fun render(board: Board, pieceToMove: Piece?) {
        val availableMoveSquares = pieceToMove?.getAvailableMoveSquares(board) ?: emptySet()

        for (rank in 8 downTo 1) {
            var line = " "
            for (file in File.entries) {
                val coordinates = Coordinates(file, rank)
                val isHighlight = availableMoveSquares.contains(coordinates)
                line += if (board.isSquareEmpty(coordinates)) {
                    getSpriteForEmptySquare(coordinates, isHighlight)
                } else {
                    getPieceSprite(board.getPiece(coordinates)!!, isHighlight)
                }
            }
            line += ANSI_RESET
            println(line)
        }
    }

    fun render(board: Board) {
        render(board, null)
    }

    private fun colorizeSprite(sprite: String, pieceColor: Color, isSquareDark: Boolean, isHighlight: Boolean): String {
        var result = sprite
        result = if (pieceColor == Color.WHITE) {
            ANSI_WHITE_PIECE_COLOR + result
        } else {
            ANSI_BLACK_PIECE_COLOR + result
        }
        result = if (isHighlight) {
            ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result
        } else if (isSquareDark) {
            ANSI_BLACK_SQUARE_BACKGROUND + result
        } else {
            ANSI_WHITE_SQUARE_BACKGROUND + result
        }
        return result
    }

    private fun getSpriteForEmptySquare(coordinates: Coordinates, isHighlight: Boolean): String {
        return colorizeSprite(
            "   ",
            Color.WHITE,
            Board.isSquareDark(coordinates),
            isHighlight
        )
    }

    private fun selectUnicodeSpriteForPiece(piece: Piece): String {
        return when (piece::class.simpleName) {
            "Pawn" -> "♟"
            "Knight" -> "♞"
            "Bishop" -> "♝"
            "Rook" -> "♜"
            "Queen" -> "♛"
            "King" -> "♚"
            else -> " "
        }
    }

    private fun getPieceSprite(piece: Piece, isHighlight: Boolean): String {
        return colorizeSprite(
            " " + selectUnicodeSpriteForPiece(piece) + " ",
            piece.color,
            Board.isSquareDark(piece.coordinates),
            isHighlight
        )
    }
}
