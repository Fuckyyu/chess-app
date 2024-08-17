package board

import chess.Coordinates
import chess.File
import chess.PieceFactory


class BoardFactory {
    private val pieceFactory: PieceFactory = PieceFactory()

    fun boardFromFEM(fen: String): Board {
        val board = Board(fen)

        val parts = fen.split(" ")
        val piecePositions = parts[0]

        val fenRows = piecePositions.split("/")

        for (i in fenRows.indices) {
            val row = fenRows[i]
            val rank = 8 - i

            var fileIndex = 0
            for (j in row.indices) {
                val fenChar = row[j]

                if (Character.isDigit(fenChar)) {
                    fileIndex += Character.getNumericValue(fenChar)
                } else {
                    val file = File.entries[fileIndex]
                    val coordinates = Coordinates(file, rank)

                    board.setPiece(coordinates, pieceFactory.fromFenChar(fenChar, coordinates))
                    fileIndex++
                }
            }
        }

        return board
    }

    fun copy(source: Board): Board {
        val clone = boardFromFEM(source.startingFen)

        for (move in source.moves) {
            clone.makeMove(move)
        }

        return clone
    }
}
