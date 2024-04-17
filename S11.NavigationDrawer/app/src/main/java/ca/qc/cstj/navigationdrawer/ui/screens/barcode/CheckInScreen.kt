package ca.qc.cstj.navigationdrawer.ui.screens.barcode

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.navigationdrawer.R
import ca.qc.cstj.navigationdrawer.ui.components.ErrorMessage
import ca.qc.cstj.navigationdrawer.ui.components.LoadingAnimation
import ca.qc.cstj.navigationdrawer.ui.screens.barcode.components.CheckInCard
import com.ramcosta.composedestinations.annotation.Destination
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig


@Destination
@Composable
fun CheckInScreen(viewModel : CheckInViewModel = viewModel()) {

    //TODO: Lifecycle

    //TODO: QRCodeLauncher

    when(val uiState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        CheckInState.Loading -> LoadingAnimation()
        is CheckInState.Error -> ErrorMessage(ex = uiState.exception)
        is CheckInState.Success -> {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                LazyColumn(contentPadding = PaddingValues(4.dp)) {
                    items(uiState.checkIns) {
                        CheckInCard(checkIn = it)
                    }
                }
                Button(onClick = {
                    //TODO: Démarrer le scan de code barre
                }) {
                    Text(text = stringResource(R.string.check_in))
                }

            }
        }

    }
}