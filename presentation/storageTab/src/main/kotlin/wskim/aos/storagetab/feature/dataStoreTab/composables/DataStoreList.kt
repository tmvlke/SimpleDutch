package wskim.aos.storagetab.feature.dataStoreTab.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wskim.aos.domain.utils.CharFormatUtils
import wskim.aos.domain.proguardSafeZone.vo.DutchHistoryListItemVO
import wskim.aos.domain.proguardSafeZone.vo.buildDutchHistoryListItemVOPreview
import wskim.aos.baseuikit.theme.LightBlue
import wskim.aos.baseuikit.theme.LightGray


@Preview(showBackground = true)
@Composable
fun DataStoreListPreview() {
    arrayListOf<DutchHistoryListItemVO>().let { list ->
        repeat(3) {
            list.add(buildDutchHistoryListItemVOPreview())
        }
        DataStoreList(list)
    }
}

@Preview(showBackground = true)
@Composable
fun DataStoreListEmptyPreview() {
    DataStoreList(arrayListOf())
}

@Composable
fun DataStoreList(list: MutableList<DutchHistoryListItemVO>) {
    // 더치 페이 목록
    if (list.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp
                    )
                )
                .background(LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "리스트를 추가해보세요.")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGray),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(list) {
                DataStoreListItem(it)
            }
        }
    }
}

@Composable
fun DataStoreListItem(item: DutchHistoryListItemVO) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = LightBlue
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = item.regDate,
                modifier = Modifier.padding(bottom = 5.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "총 가격: ${CharFormatUtils.amount(item.totalAmount)}",
                modifier = Modifier.padding(bottom = 15.dp),
                fontSize = 16.sp,
            )

            Divider(
                modifier = Modifier
            )

            item.personList.onEach {
                Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
                    Text(
                        text = "참여자: ${it.name}",
                        modifier = Modifier
                    )
                    Text(
                        text = "참여 횟수: ${it.count}회",
                        modifier = Modifier
                    )
                    Text(
                        text = "참여 가격: ${CharFormatUtils.amount(it.amount)}",
                        modifier = Modifier
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}