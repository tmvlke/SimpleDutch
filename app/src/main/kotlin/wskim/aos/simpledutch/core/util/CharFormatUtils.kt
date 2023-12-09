package wskim.aos.simpledutch.core.util

import java.text.NumberFormat
import java.util.Locale

object CharFormatUtils {
    fun amount(amount: Any?): String {
        // null 이면 빈값으로 반환함
        if (amount == null) {
            return ""
        }

        return try {
            val amountToLong = amount.toString().toLong()
            val formatter = NumberFormat.getNumberInstance(Locale("ko_KR"))
            val result = formatter.format(amountToLong)

            return if (
                amountToLong > 1000 &&
                !result.contains(",")
            ) {
                NumberFormat.getNumberInstance(Locale.getDefault()).format(amountToLong)
            } else {
                result
            }
        } catch (e: NumberFormatException) {
            amount.toString()
        }
    }
}