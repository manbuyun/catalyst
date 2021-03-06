/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manbuyun.catalyst.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimePrinter;
import org.joda.time.format.ISODateTimeFormat;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jinhai
 * @date 2019/05/20
 */
public class DateTimeUtils
{
    private DateTimeUtils() {}

    private static final DateTimeFormatter STANDARD_TIME_FORMATTER;
    private static final int MILLIS_SHIFT = 12;

    private static final Pattern DAY_PATTERN = Pattern.compile("^(\\d{4}-\\d{1,2}-\\d{1,2})");

    static {
        DateTimeParser[] parsers = {
                DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
                DateTimeFormat.forPattern("yyyy-MM-dd HH").getParser(),
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").getParser(),
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").getParser(),
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS").getParser(),
                ISODateTimeFormat.dateTimeParser().getParser()
        };
        DateTimePrinter printer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").getPrinter();
        STANDARD_TIME_FORMATTER = new DateTimeFormatterBuilder()
                .append(printer, parsers)
                .toFormatter();
    }

    /**
     * `yyyy-m[m]-d[d]*`
     * @param value
     * @return
     */
    public static String extractDay(String value)
    {
        Matcher matcher = DAY_PATTERN.matcher(value);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static DateTime parseDateTime(String value)
    {
        try {
            return STANDARD_TIME_FORMATTER.parseDateTime(value);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static LocalDate parseLocalDate(String value)
    {
        try {
            DateTime dt = STANDARD_TIME_FORMATTER.parseDateTime(value);
            return LocalDate.of(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth());
        }
        catch (Exception e) {
            return null;
        }
    }

    public static LocalDate parseLocalDate(long instant)
    {
        DateTime dt = new DateTime(instant);
        return LocalDate.of(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth());
    }

    public static LocalDate parseLocalDateZone(long dateTimeWithTimeZone)
    {
        DateTime dt = new DateTime(unpackMillisUtc(dateTimeWithTimeZone));
        return LocalDate.of(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth());
    }

    public static String print(long instant)
    {
        return STANDARD_TIME_FORMATTER.print(instant);
    }

    public static long unpackMillisUtc(long dateTimeWithTimeZone)
    {
        return dateTimeWithTimeZone >> MILLIS_SHIFT;
    }
}
