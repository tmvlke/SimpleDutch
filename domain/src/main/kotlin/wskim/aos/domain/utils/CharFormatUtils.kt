package wskim.aos.domain.utils

import java.text.NumberFormat
import java.util.Locale

object CharFormatUtils {
    fun amount(amount: Any?): String {
        // null 이면 빈값으로 반환함
        if (amount == null) {
            return "0원"
        }

        return try {
            val amountToLong = amount.toString().toLong()
            val formatter = NumberFormat.getNumberInstance(Locale("ko_KR"))
            val result = formatter.format(amountToLong)

            return if (
                amountToLong > 1000 &&
                !result.contains(",")
            ) {
                NumberFormat.getNumberInstance(Locale.getDefault()).format(amountToLong) + "원"
            } else {
                result + "원"
            }
        } catch (e: NumberFormatException) {
            "0원"
        }
    }
}