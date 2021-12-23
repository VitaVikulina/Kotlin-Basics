package tictactoe

// create an empty grid
fun initGrid(): MutableList<MutableList<Char>> {
    return mutableListOf(
        mutableListOf('_', '_', '_'),
        mutableListOf('_', '_', '_'),
        mutableListOf('_', '_', '_')
    )
}

// display the status of the field
fun printGrid(grid: MutableList<MutableList<Char>>) {
    println("""
        ---------
        | ${grid[0][0]} ${grid[0][1]} ${grid[0][2]} |
        | ${grid[1][0]} ${grid[1][1]} ${grid[1][2]} |
        | ${grid[2][0]} ${grid[2][1]} ${grid[2][2]} |
        ---------
    """.trimIndent())
}

// check if there are any letters in the user's input
fun checkString(input: MutableList<String>): Boolean {
    for (i in input.indices) {
        for (j in input[i]) {
            if (j !in '0'..'9') {
                return true
            }
        }
    }
    return false
}

// check if the player wins
fun checkWin(grid: MutableList<MutableList<Char>>, x: Int, y: Int): Boolean {
    // horizontal check
    if (grid[x][0] == grid[x][1] && grid[x][1] == grid[x][2]) return true
    // vertical check
    if (grid[0][y] == grid[1][y] && grid[1][y] == grid[2][y]) return true
    // check on the first diagonal
    if (x == y) {
        if(grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) return true
    }
    // check on the second diagonal
    if (x == 0 && y == 2 || x == 1 && y == 1 || x == 2 && y == 0) {
        if(grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) return true
    }
    return false
}

fun main() {
    val grid = initGrid()
    val range = 0..2
    var player = 'X'

    printGrid(grid)

    while (true) {
        println("Enter the coordinates: ")
        val input = readLine()!!.split(" ").map { it }.toMutableList()

        // if a user has not entered two numbers
        if (checkString(input) || input.size != 2) {
            println("You should enter numbers!")
            continue
        }

        val x = input[0].toInt() - 1
        val y = input[1].toInt() - 1

        // if the coordinates are out of range
        if (x !in range || y !in range) {
            println("Coordinates should be from 1 to 3!")
            continue
        }

        // if the cell is occupied
        if (grid[x][y] != '_') {
            println("This cell is occupied! Choose another one!")
            continue
        }

        grid[x][y] = player
        printGrid(grid)

        if (checkWin(grid, x, y)) { // if the player wins, end the game
            println("$player wins")
            break
        } else { // otherwise, the game goes on and the player changes
            player = if (player == 'X') 'O' else 'X'
        }
    }
}