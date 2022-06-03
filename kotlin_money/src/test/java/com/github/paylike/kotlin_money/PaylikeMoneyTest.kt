package com.github.paylike.kotlin_money

import com.github.paylike.kotlin_currencies.PaylikeCurrencies
import com.github.paylike.kotlin_currencies.PaylikeCurrency
import com.github.paylike.kotlin_currencies.generated.CurrencyCode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PaylikeMoneyTest {
    @Test
    fun moneyFromDoubleTest() {
        val eur: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.EUR)
        assertEquals(PaymentAmount(eur, 0,0), Money.fromDouble(eur, 0.0))
        assertEquals(PaymentAmount(eur, 0,0), Money.fromDouble(eur, -0.0))
        assertEquals(PaymentAmount(eur, 10,1), Money.fromDouble(eur, 1.0))
        assertEquals(PaymentAmount(eur, -10,1), Money.fromDouble(eur, -1.0))


        assertEquals(PaymentAmount(eur, 120000,1), Money.fromDouble(eur, 12000.0))
        assertEquals(PaymentAmount(eur, 12000,1), Money.fromDouble(eur, 1200.0))
        assertEquals(PaymentAmount(eur, 1200,1), Money.fromDouble(eur, 120.0))
        assertEquals(PaymentAmount(eur, 120,1), Money.fromDouble(eur, 12.0))
        assertEquals(PaymentAmount(eur, 12,1), Money.fromDouble(eur, 1.2))
        assertEquals(PaymentAmount(eur, 12,2), Money.fromDouble(eur, 0.12))
        assertEquals(PaymentAmount(eur, 12,3), Money.fromDouble(eur, 0.012))
        assertEquals(PaymentAmount(eur, 12,4), Money.fromDouble(eur, 0.0012))
        assertEquals(PaymentAmount(eur, -120000,1), Money.fromDouble(eur, -12000.0))
        assertEquals(PaymentAmount(eur, -12000,1), Money.fromDouble(eur, -1200.0))
        assertEquals(PaymentAmount(eur, -1200,1), Money.fromDouble(eur, -120.0))
        assertEquals(PaymentAmount(eur, -120,1), Money.fromDouble(eur, -12.0))
        assertEquals(PaymentAmount(eur, -12,1), Money.fromDouble(eur, -1.2))
        assertEquals(PaymentAmount(eur, -12,2), Money.fromDouble(eur, -0.12))
        assertEquals(PaymentAmount(eur, -12,3), Money.fromDouble(eur, -0.012))
        assertEquals(PaymentAmount(eur, -12,4), Money.fromDouble(eur, -0.0012))
    }
    @Test
    fun exceptionMoneyFromDoubleTest() {
        val eur: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.EUR)
        var unsafeNumberException = assertThrows(UnsafeNumberException::class.java) {

            Money.fromDouble(eur, Double.MAX_VALUE + 1.0)
        }
        assertEquals(true, unsafeNumberException is UnsafeNumberException)
        assertEquals("Number is not in safe range ${Double.MAX_VALUE + 1.0}", unsafeNumberException.message)

        var exception = assertThrows(Exception::class.java) {
            Money.fromDouble(eur, Double.POSITIVE_INFINITY)
        }
        assertEquals("Non finite number Infinity", exception.message)
        exception = assertThrows(Exception::class.java) {
            Money.fromDouble(eur, Double.NEGATIVE_INFINITY)
        }
        assertEquals("Non finite number -Infinity", exception.message)
    }
    @Test
    fun paymentAmountToRepresentationalStringtest() {
        val eur: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.EUR)

        assertEquals("EUR 0", Money.fromDouble(eur, 0.0).toRepresentationString())

        assertEquals("EUR 0", PaymentAmount(eur, 0, 0).toRepresentationString())
        assertEquals("EUR 1", PaymentAmount(eur, 1, 0).toRepresentationString())
        assertEquals("EUR 0.1", PaymentAmount(eur, 1, 1).toRepresentationString())
        assertEquals("EUR 10", PaymentAmount(eur, 10, 0).toRepresentationString())
        assertEquals("EUR -1", PaymentAmount(eur, -1, 0).toRepresentationString())
        assertEquals("EUR -0.1", PaymentAmount(eur, -1, 1).toRepresentationString())
        assertEquals("EUR -10", PaymentAmount(eur, -10, 0).toRepresentationString())

        assertEquals("EUR 0.01", Money.fromDouble(eur, 0.01).toRepresentationString())
        assertEquals("EUR 10", PaymentAmount(eur, 1, -1).toRepresentationString())
        assertEquals("EUR 100", PaymentAmount(eur, 10, -1).toRepresentationString())

        assertEquals("EUR 120", PaymentAmount(eur, 12, -1).toRepresentationString())
        assertEquals("EUR 12", PaymentAmount(eur, 12, 0).toRepresentationString())
        assertEquals("EUR 1.2", PaymentAmount(eur, 12, 1).toRepresentationString())
        assertEquals("EUR 0.12", PaymentAmount(eur, 12, 2).toRepresentationString())
        assertEquals("EUR 0.012", PaymentAmount(eur, 12, 3).toRepresentationString())

        assertEquals("EUR 1.23", PaymentAmount(eur, 123, 2).toRepresentationString(PaymentAmountStringOptions(padFractions = 0)))
        assertEquals("EUR 1.23", PaymentAmount(eur, 123, 2).toRepresentationString(PaymentAmountStringOptions(padFractions = 2)))
        assertEquals("EUR 1.230", PaymentAmount(eur, 123, 2).toRepresentationString(PaymentAmountStringOptions(padFractions = 3)))
        assertEquals("EUR 0.010", PaymentAmount(eur, 1, 2).toRepresentationString(PaymentAmountStringOptions(padFractions = 3)))
        assertEquals("EUR 1.000", PaymentAmount(eur, 1, 0).toRepresentationString(PaymentAmountStringOptions(padFractions = 3)))

        assertEquals("EUR 1.23", PaymentAmount(eur, 123, 2).toRepresentationString(PaymentAmountStringOptions(padIntegers = 0)))
        assertEquals("EUR  1.23", PaymentAmount(eur, 123, 2).toRepresentationString(PaymentAmountStringOptions(padIntegers = 2)))
        assertEquals("EUR   1.23", PaymentAmount(eur, 123, 2).toRepresentationString(PaymentAmountStringOptions(padIntegers = 3)))
        assertEquals("EUR   0.01", PaymentAmount(eur, 1, 2).toRepresentationString(PaymentAmountStringOptions(padIntegers = 3)))
        assertEquals("EUR 1", PaymentAmount(eur, 1, 0).toRepresentationString(PaymentAmountStringOptions(padIntegers = 0)))
        assertEquals("EUR  1", PaymentAmount(eur, 1, 0).toRepresentationString(PaymentAmountStringOptions(padIntegers = 2)))

        assertEquals("1", PaymentAmount(eur, 1, 0).toRepresentationString(PaymentAmountStringOptions(currency = false)))
        assertEquals("EUR 1", PaymentAmount(eur, 1, 0).toRepresentationString(PaymentAmountStringOptions(currency = true)))
    }
    @Test
    fun paymentAmountToStringtest() {
        val eur: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.EUR)
        assertEquals("EUR 0", PaymentAmount(eur, 0, 0).toString())
        assertEquals("EUR 1", PaymentAmount(eur, 1, 0).toString())
        assertEquals("EUR 0.1", PaymentAmount(eur, 1, 1).toString())
        assertEquals("EUR 10", PaymentAmount(eur, 10, 0).toString())
        assertEquals("EUR -1", PaymentAmount(eur, -1, 0).toString())
        assertEquals("EUR -0.1", PaymentAmount(eur, -1, 1).toString())
        assertEquals("EUR -10", PaymentAmount(eur, -10, 0).toString())
    }
    @Test
    fun paymentAmountEqualsTest() {
        val usd: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.USD)
        val usdPaymentAmount = PaymentAmount(usd, 10, 0)

        val eur: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.EUR)
        val eurPaymentAmount = PaymentAmount(eur, 10, 0)
        val identicalEurPaymentAmount = PaymentAmount(eur, 10, 0)
        val otherEurPaymentAmount = PaymentAmount(eur, 11, 0)
        val furtherEurPaymentAmount = PaymentAmount(eur, 11, 1)

        assertEquals(true, eurPaymentAmount.equals(eurPaymentAmount))
        assertEquals(true, eurPaymentAmount.equals(identicalEurPaymentAmount))
        assertEquals(false, eurPaymentAmount.equals(otherEurPaymentAmount))
        assertEquals(false, eurPaymentAmount.equals(furtherEurPaymentAmount))
        assertEquals(false, eurPaymentAmount.equals(usdPaymentAmount))

        assertEquals(true, eurPaymentAmount == eurPaymentAmount)
        assertEquals(true, eurPaymentAmount == identicalEurPaymentAmount)
        assertEquals(false, eurPaymentAmount == otherEurPaymentAmount)
        assertEquals(false, eurPaymentAmount == furtherEurPaymentAmount)
        assertEquals(false, eurPaymentAmount == usdPaymentAmount)
    }
    @Test
    fun paymentAmountHashCodeTest() {
        val usd: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.USD)
        val usdPaymentAmount = PaymentAmount(usd, 10, 0)

        val eur: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.EUR)
        val eurPaymentAmount = PaymentAmount(eur, 10, 0)
        val identicalEurPaymentAmount = PaymentAmount(eur, 10, 0)
        val otherEurPaymentAmount = PaymentAmount(eur, 11, 0)
        val furtherEurPaymentAmount = PaymentAmount(eur, 11, 1)

        assertEquals(true, eurPaymentAmount.hashCode() == eurPaymentAmount.hashCode())
        assertEquals(true, eurPaymentAmount.hashCode() == identicalEurPaymentAmount.hashCode())
        assertEquals(false, eurPaymentAmount.hashCode() == otherEurPaymentAmount.hashCode())
        assertEquals(false, eurPaymentAmount.hashCode() == furtherEurPaymentAmount.hashCode())
        assertEquals(false, eurPaymentAmount.hashCode() == usdPaymentAmount.hashCode())
    }
}
