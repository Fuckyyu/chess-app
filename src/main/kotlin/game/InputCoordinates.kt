package game

import board.Board
import board.BoardConsoleRenderer
import board.BoardFactory
import board.Move
import chess.*
import piece.King
import piece.Piece
import java.util.Scanner

class InputCoordinates {
    companion object {
        private val scanner = Scanner(System.`in`)

        private fun input(): Coordinates {
            while (true) {
                println("Please enter coordinates (ex. a1)")

                val line = scanner.nextLine()
                if (line.length != 2) {
                    println("Invalid format")
                    continue
                }

                val fileChar: Char = line[0]
                val rankChar: Char = line[1]
                if (!fileChar.isLetter()) {
                    println("Invalid format")
                    continue
                }
                if (!rankChar.isDigit()) {
                    println("Invalid format")
                    continue
                }

                val rank = Character.getNumericValue(rankChar)
                if (rank < 1 || rank > 8) {
                    println("Invalid format")
                    continue
                }

                val file: File? = File.fromChar(fileChar)
                if (file == null) {
                    println("Invalid format")
                    continue
                }
                return Coordinates(file, rank)
            }
        }

        fun inputPieceCoordinatesForColor(color: Color, board: Board): Coordinates {
            while (true) {
                println("Enter coordinates for a piece to move")
                val coordinates: Coordinates = input()

                if (board.isSquareEmpty(coordinates)) {
                    println("Empty square")
                    continue
                }

                val piece: Piece? = board.getPiece(coordinates)
                if (piece != null) {
                    if (piece.color != color) {
                        println("Wrong color")
                        continue
                    }

                    val availableMoveSquares = piece.getAvailableMoveSquares(board)
                    if (availableMoveSquares.isEmpty()) {
                        println("Blocked piece")
                        continue
                    }
                    return coordinates
                }
            }
        }


        fun inputAvailableSquare(coordinates: Set<Coordinates>?): Coordinates {
            while (true) {
                println("Enter your move for selected piece ")
                val input: Coordinates = input()

                if (coordinates != null) {
                    if (!coordinates.contains(input)) {
                        println("Non-available square")
                        continue
                    }
                }

                return input
            }
        }

        fun inputMove(board: Board, color: Color, renderer: BoardConsoleRenderer): Move {
            while (true) {
                // input
                val sourceCoordinates = inputPieceCoordinatesForColor(color, board)

                val piece = board.getPiece(sourceCoordinates)
                val availableMoveSquares = piece?.getAvailableMoveSquares(board) ?: emptySet()

                renderer.render(board, piece)
                val targetCoordinates = InputCoordinates.inputAvailableSquare(availableMoveSquares)

                val move = Move(sourceCoordinates, targetCoordinates)

                if (validateIfKingInCheckAfterMove(board, color, move)) {
                    println("Your king is under attack!")
                    continue
                }

                return Move(sourceCoordinates, targetCoordinates)
            }
        }

        private fun validateIfKingInCheckAfterMove(board: Board, color: Color, move: Move): Boolean {
            val copy: Board = BoardFactory().copy(board)
            copy.makeMove(move)

            // we trust that there is king on the board
            val king = copy.getPiecesByColor(color).first { it is King }
            return copy.isSquareAttackedByColor(king.coordinates, color.opposite())
        }
    }
}


