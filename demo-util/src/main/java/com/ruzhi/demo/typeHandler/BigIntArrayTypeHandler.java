/*
The MIT License (MIT)

Copyright (c) 2014 Manni Wood

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package com.ruzhi.demo.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

/**
 * @author lukang
 */
@MappedJdbcTypes(JdbcType.OTHER)
@MappedTypes(Long[].class)
public class BigIntArrayTypeHandler extends BaseTypeHandler<Long[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
            Long[] parameter, JdbcType jdbcType) throws SQLException {
        Connection c = ps.getConnection();
        Array inArray = c.createArrayOf("bigint", parameter);
        ps.setArray(i, inArray);
    }

    @Override
    public Long[] getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Array outputArray = rs.getArray(columnName);
        if (outputArray == null) {
            return null;
        }
        return (Long[])outputArray.getArray();
    }

    @Override
    public Long[] getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Array outputArray = rs.getArray(columnIndex);
        if (outputArray == null) {
            return null;
        }
        return (Long[])outputArray.getArray();
    }

    @Override
    public Long[] getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Array outputArray = cs.getArray(columnIndex);
        if (outputArray == null) {
            return null;
        }
        return (Long[])outputArray.getArray();
    }
}