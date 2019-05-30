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
package com.manbuyun.catalyst.scalar;

import com.manbuyun.catalyst.utils.SliceUtils;
import io.airlift.slice.Slice;
import io.prestosql.spi.function.ScalarOperator;
import io.prestosql.spi.function.SqlNullable;
import io.prestosql.spi.function.SqlType;
import io.prestosql.spi.type.StandardTypes;

import static io.prestosql.spi.function.OperatorType.BETWEEN;
import static io.prestosql.spi.function.OperatorType.EQUAL;
import static io.prestosql.spi.function.OperatorType.GREATER_THAN;
import static io.prestosql.spi.function.OperatorType.GREATER_THAN_OR_EQUAL;
import static io.prestosql.spi.function.OperatorType.LESS_THAN;
import static io.prestosql.spi.function.OperatorType.LESS_THAN_OR_EQUAL;
import static io.prestosql.spi.function.OperatorType.NOT_EQUAL;

/**
 * @author jinhai
 * @date 2019/05/20
 */
public class ExtIntegerOperators
{
    private ExtIntegerOperators() {}

    @ScalarOperator(EQUAL)
    @SqlType(StandardTypes.BOOLEAN)
    @SqlNullable
    public static Boolean equal(@SqlType(StandardTypes.VARCHAR) Slice left, @SqlType(StandardTypes.INTEGER) long right)
    {
        return SliceUtils.toLong(left) == right;
    }

    @ScalarOperator(EQUAL)
    @SqlType(StandardTypes.BOOLEAN)
    @SqlNullable
    public static Boolean equal(@SqlType(StandardTypes.INTEGER) long left, @SqlType(StandardTypes.VARCHAR) Slice right)
    {
        return left == SliceUtils.toLong(right);
    }

    @ScalarOperator(NOT_EQUAL)
    @SqlType(StandardTypes.BOOLEAN)
    @SqlNullable
    public static Boolean notEqual(@SqlType(StandardTypes.VARCHAR) Slice left, @SqlType(StandardTypes.INTEGER) long right)
    {
        return SliceUtils.toLong(left) != right;
    }

    @ScalarOperator(NOT_EQUAL)
    @SqlType(StandardTypes.BOOLEAN)
    @SqlNullable
    public static Boolean notEqual(@SqlType(StandardTypes.INTEGER) long left, @SqlType(StandardTypes.VARCHAR) Slice right)
    {
        return left != SliceUtils.toLong(right);
    }

    @ScalarOperator(LESS_THAN)
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean lessThan(@SqlType(StandardTypes.VARCHAR) Slice left, @SqlType(StandardTypes.INTEGER) long right)
    {
        return SliceUtils.toLong(left) < right;
    }

    @ScalarOperator(LESS_THAN)
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean lessThan(@SqlType(StandardTypes.INTEGER) long left, @SqlType(StandardTypes.VARCHAR) Slice right)
    {
        return left < SliceUtils.toLong(right);
    }

    @ScalarOperator(LESS_THAN_OR_EQUAL)
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean lessThanOrEqual(@SqlType(StandardTypes.VARCHAR) Slice left, @SqlType(StandardTypes.INTEGER) long right)
    {
        return SliceUtils.toLong(left) <= right;
    }

    @ScalarOperator(LESS_THAN_OR_EQUAL)
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean lessThanOrEqual(@SqlType(StandardTypes.INTEGER) long left, @SqlType(StandardTypes.VARCHAR) Slice right)
    {
        return left <= SliceUtils.toLong(right);
    }

    @ScalarOperator(GREATER_THAN)
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean greaterThan(@SqlType(StandardTypes.VARCHAR) Slice left, @SqlType(StandardTypes.INTEGER) long right)
    {
        return SliceUtils.toLong(left) > right;
    }

    @ScalarOperator(GREATER_THAN)
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean greaterThan(@SqlType(StandardTypes.INTEGER) long left, @SqlType(StandardTypes.VARCHAR) Slice right)
    {
        return left > SliceUtils.toLong(right);
    }

    @ScalarOperator(GREATER_THAN_OR_EQUAL)
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean greaterThanOrEqual(@SqlType(StandardTypes.VARCHAR) Slice left, @SqlType(StandardTypes.INTEGER) long right)
    {
        return SliceUtils.toLong(left) >= right;
    }

    @ScalarOperator(GREATER_THAN_OR_EQUAL)
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean greaterThanOrEqual(@SqlType(StandardTypes.INTEGER) long left, @SqlType(StandardTypes.VARCHAR) Slice right)
    {
        return left >= SliceUtils.toLong(right);
    }

    @ScalarOperator(BETWEEN)
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean between(@SqlType(StandardTypes.VARCHAR) Slice value, @SqlType(StandardTypes.INTEGER) long min, @SqlType(StandardTypes.INTEGER) long max)
    {
        return min <= SliceUtils.toLong(value) && SliceUtils.toLong(value) <= max;
    }

    @ScalarOperator(BETWEEN)
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean between(@SqlType(StandardTypes.INTEGER) long value, @SqlType(StandardTypes.VARCHAR) Slice min, @SqlType(StandardTypes.VARCHAR) Slice max)
    {
        return SliceUtils.toLong(min) <= value && value <= SliceUtils.toLong(max);
    }
}
