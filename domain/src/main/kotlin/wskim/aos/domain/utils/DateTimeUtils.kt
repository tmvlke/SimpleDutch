package wskim.aos.domain.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeUtils {
    private val dateTimeSimpleFormat: SimpleDateFormat
        get() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    private val dateFormat: SimpleDateFormat
        get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    fun convertDate(date: Date): String {
        return SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA).format(date)
    }

    fun todayToDash(): String {
        return try {
            dateFormat.format(Date())
        } catch (e: ParseException) {
            "2020-01-01 00:00:00"
        }
    }
}
