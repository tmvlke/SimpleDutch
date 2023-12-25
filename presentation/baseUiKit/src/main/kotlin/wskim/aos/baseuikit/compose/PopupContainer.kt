package wskim.aos.baseuikit.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import wskim.aos.baseuikit.theme.WHITE

@Composable
fun PopupContainer(
    onChangeState : () -> Unit,
    dialogContent : @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = { onChangeState() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {
        Surface(
            Modifier.fillMaxWidth().padding(30.dp).wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            color = WHITE
        ) {
            dialogContent()
        }
    }
}