package wskim.aos.simpledutch.common.compose.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import wskim.aos.domain.utils.CharFormatUtils

object AmountTransformation : VisualTransformation{
    override fun filter(text: AnnotatedString): TransformedText {

        return TransformedText(
            text = AnnotatedString(CharFormatUtils.amount(text.text)),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return CharFormatUtils.amount(text.text).length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return text.length
                }
            }
        )
    }
}