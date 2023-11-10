package wskim.aos.simpledutch.common.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import wskim.aos.simpledutch.R

@Composable
fun NetworkError(
    modifier: Modifier = Modifier,
    onRetryButtonClick: () -> Unit,
) {
   Column(
       modifier = modifier.fillMaxWidth().wrapContentHeight(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally,
   ) {
       Text(
           text = stringResource(R.string.network_error_title),
           style = MaterialTheme.typography.headlineLarge,
           textAlign = TextAlign.Center,
       )

       Text(
           text = stringResource(R.string.network_error_description),
           style = MaterialTheme.typography.bodyMedium,
           modifier = Modifier.padding(16.dp),
           textAlign = TextAlign.Center,
       )

       Button(onClick = { onRetryButtonClick() }) {
           Text(
               text = stringResource(R.string.network_error_retry_button_text).uppercase()
           )
       }
   }
}

@Preview(showBackground = true)
@Composable
fun NetworkErrorPreview() {
    NetworkError(modifier = Modifier, onRetryButtonClick = {})
}