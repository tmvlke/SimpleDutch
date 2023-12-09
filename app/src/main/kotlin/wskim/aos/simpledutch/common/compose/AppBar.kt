package wskim.aos.simpledutch.common.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import wskim.aos.simpledutch.R
import wskim.aos.simpledutch.ui.theme.WHITE

@Composable
@Preview
fun PreviewAppBar() {
    AppBar(
        startImage = R.drawable.arrow_back,
        startImageClickAction = { }
    )
}

@Composable
fun AppBar(
    text : String? = null,
    startImage: Int = R.drawable.arrow_back,
    endImage : Int? = null,
    startImageClickAction : (() -> Unit)? = null,
    endImageClickAction : (() -> Unit)? = null,
    backgroundColor : Color = WHITE,
) {
    ConstraintLayout(Modifier.background(backgroundColor).fillMaxWidth().height(50.dp)) {
        val (startImg, centerTitle, endImg) = createRefs()

        AppBarImageSquare(
            modifier = Modifier.constrainAs(startImg) {
                start.linkTo(parent.start, 20.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            image = startImage,
            clickAction = { startImageClickAction?.invoke() }
        )

        if (text != null) {
            Text(
                modifier = Modifier.constrainAs(centerTitle) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top, 11.dp)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 11.dp)
                },
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        if (endImage != null) {
            AppBarImageSquare(
                modifier = Modifier.constrainAs(endImg) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end, 4.dp)
                    bottom.linkTo(parent.bottom)
                },
                image = endImage,
                clickAction = { endImageClickAction?.invoke() }
            )
        }
    }
}

@Composable
fun AppBarImageSquare(
    modifier: Modifier,
    image : Int,
    clickAction : (() -> Unit)
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .build(),
        placeholder = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .width(24.dp)
            .height(24.dp)
            .singleClick { clickAction.invoke() }
    )
}