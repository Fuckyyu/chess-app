package piece

import chess.CoordinatesShift

interface IRook {
    fun getRookMoves(): Set<CoordinatesShift> {
        val result = mutableSetOf<CoordinatesShift>()
        for (i in -7..7) {
            if (i == 0) continue
            result.add(CoordinatesShift(i, 0))
        }
        // bottom to top
        for (i in -7..7) {
            if (i == 0) continue
            result.add(CoordinatesShift(0, i))
        }
        return result
    }
}