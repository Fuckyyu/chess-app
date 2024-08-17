package piece

import chess.CoordinatesShift

interface IBishop {
    fun getBishopMoves(): Set<CoordinatesShift> {
        val result = mutableSetOf<CoordinatesShift>()
        // bottom-left to top-right
        for (i in -7..7) {
            if (i == 0) continue
            result.add(CoordinatesShift(i, i))
        }
        // top-left to bottom-right
        for (i in -7..7) {
            if (i == 0) continue
            result.add(CoordinatesShift(i, -i))
        }
        return result
    }
}