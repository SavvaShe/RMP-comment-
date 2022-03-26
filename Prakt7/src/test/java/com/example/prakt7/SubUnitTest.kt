package com.example.prakt7

import org.junit.Test

import org.junit.Assert.*
import kotlin.math.E

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SubUnitTest {
    @Test
    fun div_isCorrect() {
        var subNumber = SubNumber()
        assertEquals(model(0.0,EnumRes.AGreaterZeroBEquallyZero),subNumber.subNumber(1.0,0.0))
        assertEquals(model(4.0,EnumRes.AGreater),subNumber.subNumber(1.0,-4.0))
        assertEquals(model(3.0,EnumRes.ALess),subNumber.subNumber(-5.0,15.0))
        assertEquals(model(-3.0,EnumRes.AGreater),subNumber.subNumber(2.0,6.0))
        assertEquals(model(-3.0,EnumRes.ALess),subNumber.subNumber(-2.0,-6.0))
        assertEquals(model(0.0,EnumRes.ABEquallyZeroBLessZero),subNumber.subNumber(0.0,0.0))
        assertEquals(model(0.0,EnumRes.ABEquallyZeroBLessZero),subNumber.subNumber(0.0,-1.0))
        assertEquals(model(0.0,EnumRes.ALessZeroBEquallyZero),subNumber.subNumber(-10.0,0.0))
        assertEquals(model(0.0,EnumRes.OverflowDouble),subNumber.subNumber(Double.MIN_VALUE, Double.MAX_VALUE))
        assertEquals(model(0.0,EnumRes.AEquallyZeroBGreaterZero),subNumber.subNumber(0.0, 5.5))
        assertEquals(model(0.0,EnumRes.OverflowDouble),subNumber.subNumber(Double.MAX_VALUE, Double.MIN_VALUE))
    }
}