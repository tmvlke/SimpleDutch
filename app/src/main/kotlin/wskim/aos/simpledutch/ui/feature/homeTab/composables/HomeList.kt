package wskim.aos.simpledutch.ui.feature.homeTab.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import wskim.aos.simpledutch.progaurdSafeZone.HomeDutchListItem
import wskim.aos.simpledutch.ui.theme.LightGray


@Preview(showBackground = true)
@Composable
fun HomeListPreview() {
    arrayListOf<HomeDutchListItem>().let { list ->
        repeat(3) {
            list.add(HomeDutchListItem(name = "${it}차", price = 1000 * it, enterCount = 1 + it))
        }
        HomeList(list)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeListEmptyPreview() {
    HomeList(arrayListOf())
}

@Composable
fun HomeList(list: MutableList<HomeDutchListItem>) {
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
                HomeListItem(it)
            }
        }
    }
}

@Composable
fun HomeListItem(item: HomeDutchListItem) {

    Column {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (name, price, enterCount, divider) = createRefs()

            Text(
                text = item.name,
                modifier = Modifier.constrainAs(name) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )

            Text(
                text = "${item.price}원",
                modifier = Modifier.constrainAs(price) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(enterCount.top)
                }
            )

            Text(
                text = "${item.enterCount}명",
                modifier = Modifier.constrainAs(enterCount) {
                    end.linkTo(parent.end)
                    top.linkTo(price.bottom)
                    bottom.linkTo(parent.bottom)
                }
            )
        }

        Divider(
            modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)
        )
    }
}