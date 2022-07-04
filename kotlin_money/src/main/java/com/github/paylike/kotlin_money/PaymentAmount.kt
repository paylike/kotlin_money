package com.github.paylike.kotlin_money

import kotlinx.serialization.Serializable

@Serializable
class PaymentAmount(val currency: String, val value: Long, val exponent: Int) {
    /**
     * equals only checks if the two object are equal in every field.
     * Objects what are equal numerically but not exactly the same in every field wont be equal.
     * For example:
     * { currency: 'EUR', value: 150, exponent: 1 }
     * will not be equal with
     * { currency: 'EUR', value: 15, exponent: 0 }
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as PaymentAmount
        if (this.currency != other.currency) return false
        return this.value == other.value && this.exponent == other.exponent
    }

    override fun hashCode(): Int = currency.hashCode() + value.hashCode() + exponent.hashCode()

    override fun toString(): String = toRepresentationString(PaymentAmountStringOptions())

    fun toRepresentationString(options: PaymentAmountStringOptions = PaymentAmountStringOptions()): String {
        val wholes: String
        val fraction: String
        val negative: Boolean = value < 0
        val integer: String = (if (negative) -value else value).toString()
        if (exponent <= 0) {
            wholes = integer + "".padEnd(-exponent, '0')
            fraction = ""
        } else if (integer.length <= exponent) {
            wholes = "0"
            fraction = integer.padStart(exponent, '0')
        } else {
            wholes = integer.substring(0, integer.length - exponent)
            fraction = integer.substring(integer.length - exponent)
        }
        val paddedWholes: String = wholes.padStart(options.padIntegers, ' ')
        val paddedFraction: String = fraction.padEnd(options.padFractions, '0')
        val currencyString: String = if (options.currency) "$currency " else ""
        return currencyString + (if (negative) "-" else "") + paddedWholes + if (paddedFraction == "") "" else ".$paddedFraction"
    }
}
