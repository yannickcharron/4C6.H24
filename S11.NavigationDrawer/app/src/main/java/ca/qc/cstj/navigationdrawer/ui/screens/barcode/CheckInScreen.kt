package ca.qc.cstj.navigationdrawer.ui.screens.barcode

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
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
import androidx.lifecycle.Lifecycle.State.*
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
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(lifecycleState) {
        when(lifecycleState) {
            DESTROYED -> Unit
            INITIALIZED -> Unit
            CREATED -> Unit
            STARTED -> Unit
            RESUMED -> {
                viewModel.loadCheckIn()
            }
        }

    }


    //TODO: QRCodeLauncher
    val scanQRCodeLauncher = rememberLauncherForActivityResult(ScanCustomCode()) { codeBarResult ->
        when(codeBarResult) {
            is QRResult.QRError -> {
                //Afficher un message d'erreur à l'utilisateur
            }
            QRResult.QRMissingPermission -> {
                //Afficher un message poli à l'utisateur
            }
            is QRResult.QRSuccess -> {
                viewModel.addCheckIn(codeBarResult.content.rawValue!!)
            }
            QRResult.QRUserCanceled -> {}
        }
    }

    when(val uiState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        CheckInState.Loading -> LoadingAnimation()
        is CheckInState.Error -> ErrorMessage(ex = uiState.exception)
        is CheckInState.Success -> {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Button(onClick = {
                    scanQRCodeLauncher.launch(
                        ScannerConfig.build {
                            setBarcodeFormats(listOf(BarcodeFormat.FORMAT_QR_CODE))
                            setOverlayStringRes(R.string.scan_the_id)
                            setShowCloseButton(true)
                        }
                    )
                }) {
                    Text(text = stringResource(R.string.check_in))
                }
                LazyColumn(modifier = Modifier.height(400.dp), contentPadding = PaddingValues(4.dp)) {
                    items(uiState.checkIns) {
                        CheckInCard(checkIn = it)
                    }
                }


            }
        }

    }
}