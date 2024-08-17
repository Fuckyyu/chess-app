package chess

import board.Board

abstract class GameStateChecker {
    abstract fun check(board: Board, color: Color): GameState
}