package com.fasterxml.jackson.databind.node;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
 * Numeric node that contains simple 64-bit integer values.
 */
public final class BigIntegerNode
    extends NumericNode
{
    private final static BigInteger MIN_INTEGER = BigInteger.valueOf(Integer.MIN_VALUE);
    private final static BigInteger MAX_INTEGER = BigInteger.valueOf(Integer.MAX_VALUE);
    private final static BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    private final static BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    
    final protected BigInteger _value;
    
    /*
    /**********************************************************
    /* Construction
    /**********************************************************
     */

    public BigIntegerNode(BigInteger v) { _value = v; }

    public static BigIntegerNode valueOf(BigInteger v) { return new BigIntegerNode(v); }

    /* 
    /**********************************************************
    /* Overrridden JsonNode methods
    /**********************************************************
     */

    @Override
    public JsonToken asToken() { return JsonToken.VALUE_NUMBER_INT; }

    @Override
    public JsonParser.NumberType getNumberType() { return JsonParser.NumberType.BIG_INTEGER; }

    @Override
    public boolean isIntegralNumber() { return true; }

    @Override
    public boolean isBigInteger() { return true; }

    @Override public boolean canConvertToInt() {
        return (_value.compareTo(MIN_INTEGER) >= 0) && (_value.compareTo(MAX_INTEGER) <= 0);
    }
    @Override public boolean canConvertToLong() {
        return (_value.compareTo(MIN_LONG) >= 0) && (_value.compareTo(MAX_LONG) <= 0);
    }
    
    @Override
    public Number getNumberValue() {
        return _value;
    }

    @Override
    public int getIntValue() { return _value.intValue(); }

    @Override
    public long getLongValue() { return _value.longValue(); }

    @Override
    public BigInteger getBigIntegerValue() { return _value; }

    @Override
    public double getDoubleValue() { return _value.doubleValue(); }

    @Override
    public BigDecimal getDecimalValue() { return new BigDecimal(_value); }

    /* 
    /**********************************************************
    /* General type coercions
    /**********************************************************
     */
    
    @Override
    public String asText() {
        return _value.toString();
    }

    @Override
    public boolean asBoolean(boolean defaultValue) {
        return !BigInteger.ZERO.equals(_value);
    }
    
    @Override
    public final void serialize(JsonGenerator jg, SerializerProvider provider)
        throws IOException, JsonProcessingException
    {
        jg.writeNumber(_value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() != getClass()) { // final class, can do this
            return false;
        }
        return ((BigIntegerNode) o)._value == _value;
    }

    @Override
    public int hashCode() {
        return _value.hashCode();
    }
}
