package chess

import board.Board
import board.BoardConsoleRenderer
import board.BoardFactory
import game.Game


fun main() {
    val board: Board = (BoardFactory()).boardFromFEM(

        "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
    )
    val renderer = BoardConsoleRenderer()
    val game = Game(board)
    game.gameLoop()
}


