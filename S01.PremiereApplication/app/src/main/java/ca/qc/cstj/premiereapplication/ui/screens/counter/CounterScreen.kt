package ca.qc.cstj.premiereapplication.ui.screens.counter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.premiereapplication.R
import ca.qc.cstj.premiereapplication.ui.theme.PremiereApplicationTheme
import ca.qc.cstj.premiereapplication.ui.theme.YannickColor

@Composable
fun CounterScreen(viewModel: CounterScreenViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Log.d("CounterScreen", counter.toString())
        Text(
            text = uiState.value.counter.toString(),
            fontSize = 36.sp,
            color = YannickColor,
            fontWeight = FontWeight.ExtraBold
        )
        Row {
            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = { viewModel.modify(1) }
            ) {
                Text(text = stringResource(id = R.string.add_1))
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.modify(-1)
                }
            ) {
                Text(text = stringResource(id = R.string.minus_1))
            }

        }
    }
}

@Composable
fun CounterScreenV1() {

    //var => On peut refaire une affectation (let en js)
    //val => On ne peut pas refaire de = après le premier =. Pas de réaffectation (const en js)

//    val counter = remember {
//        mutableIntStateOf(0)
//    }

    var counter by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Log.d("CounterScreen", counter.toString())
        Text(
            text = counter.toString(),
            fontSize = 36.sp,
            color = YannickColor,
            fontWeight = FontWeight.ExtraBold
        )
        Row {
            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = { counter++ }
            ) {
                Text(text = stringResource(id = R.string.add_1))
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (counter == 0) return@Button
                    counter--
                }
            ) {
                Text(text = stringResource(id = R.string.minus_1))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    PremiereApplicationTheme {
        CounterScreen()
    }
}