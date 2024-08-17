package game

import board.Board
import board.BoardConsoleRenderer
import chess.*

class Game(private val board: Board) {
    private val renderer: BoardConsoleRenderer = BoardConsoleRenderer()
    private val checkers: List<GameStateChecker> = listOf(
        StalemateGameStateChecker(),
        CheckmateGameStateChecker()
    )

    fun gameLoop() {
        var colorToMove: Color = Color.WHITE

        var state: GameState = determineGameState(board, colorToMove)
        while (state == GameState.ONGOING) {

            renderer.render(board)
            if (colorToMove == Color.WHITE) {
                println("White to move")
            } else {
                println("Black to move")
            }

            val move = InputCoordinates.inputMove(board, colorToMove, renderer)

            // make move
            board.makeMove(move)
            // pass move
            colorToMove = colorToMove.opposite()

            state = determineGameState(board, colorToMove)
        }
        renderer.render(board)
        println("Game ended with state = $state")
    }

    private fun determineGameState(board: Board, color: Color): GameState {
        for (checker in checkers) {
            val state = checker.check(board, color)

            if (state != GameState.ONGOING) {
                return state
            }
        }
        return GameState.ONGOING
    }
}


