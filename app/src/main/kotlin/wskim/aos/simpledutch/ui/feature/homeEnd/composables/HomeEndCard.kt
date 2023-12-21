package wskim.aos.simpledutch.ui.feature.homeEnd.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wskim.aos.simpledutch.core.util.CharFormatUtils
import wskim.aos.simpledutch.progaurdSafeZone.DutchEndListItemVO
import wskim.aos.simpledutch.progaurdSafeZone.buildDutchEndListItemVOPreview
import wskim.aos.simpledutch.ui.theme.Blue
import wskim.aos.simpledutch.ui.theme.Gray

@Preview
@Composable
fun HomeEndCard(
    listItem: DutchEndListItemVO = buildDutchEndListItemVOPreview(),
    endButtonClicked: () -> Unit = {}
) {
    Card {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(text = "참여자 명: ")
                Text(text = listItem.name)
            }

            Divider(modifier = Modifier.padding(bottom = 10.dp))

            listItem.list.onEach { item ->
                Row(
                    modifier = Modifier.padding(bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "${item.title}: ${CharFormatUtils.amount(item.amount.toInt() / item.enterPersonList.size)}")
                }
            }

            Button(
                onClick = endButtonClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!listItem.isEnd) Blue else Gray
                )
            ) {
                Box(
                    modifier = Modifier.height(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = if (listItem.isEnd) "완료" else "정산", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}