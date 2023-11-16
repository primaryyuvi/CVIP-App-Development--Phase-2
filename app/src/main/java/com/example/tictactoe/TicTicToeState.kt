package com.example.tictactoe

data class TicTicToeState(
    val buttonValues: Array<String> = arrayOf("-","-","-","-","-","-","-","-","-"),
    val buttonWinners: Array<Boolean> = arrayOf(false,false,false,false,false,false,false,false,false),
    val isXTurn : Boolean = true,
    val victor : String? = null
)
