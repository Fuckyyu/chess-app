package chess

class Coordinates(val file: File, val rank: Int) {
    fun shift(shift: CoordinatesShift): Coordinates {
        return Coordinates(File.entries[this.file.ordinal + shift.fileShift], this.rank + shift.rankShift)
    }

    fun canShift(shift: CoordinatesShift): Boolean {
        val f: Int = file.ordinal + shift.fileShift
        val r: Int = rank + shift.rankShift

        if ((f < 0) || (f > 7)) return false
        if ((r < 1) || (r > 8)) return false

        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coordinates

        if (file != other.file) return false
        if (rank != other.rank) return false

        return true
    }

    override fun hashCode(): Int {
        var result = file.hashCode()
        result = 31 * result + rank
        return result
    }

    override fun toString(): String {
        return file.toString() + rank.toString()
    }
}