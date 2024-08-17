package board

import chess.Coordinates
import chess.File

class BoardUtils {
    companion object {
        fun getDiagonalCoordinatesBetween(source: Coordinates, target: Coordinates): List<Coordinates> {
            // допущение - клетки лежат на одной диагонали
            val result = mutableListOf<Coordinates>()

            val fileShift = if (source.file.ordinal < target.file.ordinal) 1 else -1
            val rankShift = if (source.rank < target.rank) 1 else -1

            var fileIndex = source.file.ordinal + fileShift
            var rank = source.rank + rankShift

            while (fileIndex != target.file.ordinal || rank != target.rank) {
                result.add(Coordinates(File.entries[fileIndex], rank))
                fileIndex += fileShift
                rank += rankShift
            }

            return result
        }

        fun getVerticalCoordinatesBetween(source: Coordinates, target: Coordinates): List<Coordinates> {
            // допущение - клетки лежат на одной вертикали
            val result = mutableListOf<Coordinates>()

            val rankShift = if (source.rank < target.rank) 1 else -1

            var rank = source.rank + rankShift
            while (rank != target.rank) {
                result.add(Coordinates(source.file, rank))
                rank += rankShift
            }

            return result
        }

        fun getHorizontalCoordinatesBetween(source: Coordinates, target: Coordinates): List<Coordinates> {
            // допущение - клетки лежат на одной горизонтали
            val result = mutableListOf<Coordinates>()

            val fileShift = if (source.file.ordinal < target.file.ordinal) 1 else -1

            var fileIndex = source.file.ordinal + fileShift
            while (fileIndex != target.file.ordinal) {
                result.add(Coordinates(File.entries[fileIndex], source.rank))
                fileIndex += fileShift
            }
            return result
        }
    }
}