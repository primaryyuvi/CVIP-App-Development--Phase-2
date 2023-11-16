package com.example.tictactoe

import TicTacToeTheme
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicTacToeScreen(
    modifier: Modifier = Modifier,
    viewModel: TicTacToeViewModel
){
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TicTacToeTopBar()
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val turn = if (state.isXTurn) "X's Turn" else "O's Turn"
            val turnMessage = "It is $turn"
            val winner = state.victor
            val winnerMessage = "$winner wins"
            Text(
                text = if (winner != null) winnerMessage else turnMessage,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(top = 16.dp),
                fontSize = 44.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Card (
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 16.dp
                )
            ){
                BuildRow(rowId = 1, viewModel = viewModel)
                BuildRow(rowId = 2, viewModel = viewModel)
                BuildRow(rowId = 3, viewModel = viewModel)
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { viewModel.resetBoard() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {
                Text(text = "Reset Game", fontSize = 32.sp )
            }

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicTacToeTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Row (horizontalArrangement = Arrangement.spacedBy(16.dp)){
                Image(
                    painter = painterResource(
                        id = R.drawable.tic_tac_toe
                    ),
                    contentDescription = "Logo",
                    modifier = Modifier.size(44.dp)
                )
                Text(
                    text = "Tic Tac Toe",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    )
}
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BuildRow(
    rowId: Int,
    modifier: Modifier = Modifier,
    viewModel: TicTacToeViewModel
){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(16.dp)
    ) {
        val third = (rowId * 3) - 1
        val second = third - 1
        val first = second - 1
        val buttonColors = viewModel.state.value.buttonWinners
        val buttonValues = viewModel.state.value.buttonValues
        TicTacToeButton(buttonValues[first],buttonColors[first]) { viewModel.setButton(first)}
        TicTacToeButton(buttonValues[second],buttonColors[second]) { viewModel.setButton(second)}
        TicTacToeButton(buttonValues[third],buttonColors[third]) { viewModel.setButton(third)}
    }
}


@Composable
fun TicTacToeButton(
    button: String,
    shouldChangeColor: Boolean,
    onClick: () -> Unit,
){
    val color = if(shouldChangeColor) MaterialTheme.colorScheme.tertiary
                    else MaterialTheme.colorScheme.secondaryContainer
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = if(shouldChangeColor) MaterialTheme.colorScheme.onTertiary
            else MaterialTheme.colorScheme.onSecondaryContainer
        ),
        modifier = Modifier.padding(16.dp)
    ){
        Text(text = button, fontSize = 50.sp)
    }
}
@Preview(showBackground = true)
@Composable
fun SPreview(){
    TicTacToeTheme(darkTheme = true) {
        TicTacToeScreen(viewModel = TicTacToeViewModel())
    }
}













