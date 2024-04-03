package ca.qc.cstj.bottomnavigation.core

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateHelper {

    fun toSystemDefaultDateTime(timestamp: Long, timezone: Int): LocalDateTime {
        val formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss") //val instant = Instant.ofEpochSecond(timestamp).atZone(ZoneOffset.UTC).plusSeconds(timezone.toLong())

        val instant = Instant.ofEpochSecond(timestamp).atZone(ZoneOffset.UTC)
        val dateTimeString = formatter.format(instant)

        return LocalDateTime.parse(dateTimeString)

    }

    fun toCurrentWeatherLocationDateTime(timestamp: Long, timezone: Int) : LocalDateTime {
        val formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

        val instant = Instant.ofEpochSecond(timestamp).atZone(ZoneOffset.UTC).plusSeconds(timezone.toLong())
        val dateTimeString = formatter.format(instant)

        return LocalDateTime.parse(dateTimeString)

    }

}

fun LocalDateTime.toZonedDateTime() : ZonedDateTime {

    return ZonedDateTime.of(this.toJavaLocalDateTime(), ZoneId.of("UTC"))
        .withZoneSameLocal(ZoneId.systemDefault())

}

fun LocalDateTime.toWeatherTimeZone() : ZonedDateTime {
    return ZonedDateTime.of(this.toJavaLocalDateTime(), ZoneId.of("UTC"))
        .withZoneSameLocal(ZoneId.systemDefault())
}

fun LocalDateTime.format(formatPattern :String = Constants.DATETIME_PATTERN) :String {
    return DateTimeFormatter.ofPattern(formatPattern).format(this.toJavaLocalDateTime())
}

fun LocalDateTime.toLocalDateTimeFormat() : String {

    val currentZoneOffset = ZoneId.systemDefault().rules.getOffset(this.toJavaLocalDateTime())

    val dateTimeWithOffset = OffsetDateTime.of(this.toJavaLocalDateTime(), ZoneOffset.UTC)
        .withOffsetSameInstant(currentZoneOffset)

    return DateTimeFormatter.ofPattern(Constants.DATETIME_PATTERN).format(dateTimeWithOffset)

}