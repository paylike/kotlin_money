package com.github.paylike.kotlin_money

import com.github.paylike.kotlin_currencies.PaylikeCurrency


class PaymentAmount(_currency: PaylikeCurrency, _value: Long, _exponent: Int) {
    val currency: PaylikeCurrency = _currency
    val value: Long = _value
    val exponent: Int = _exponent

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as PaymentAmount
        if (this.currency.code != other.currency.code) return false
        return this.value == other.value && this.exponent == other.exponent
    }

    override fun hashCode(): Int = currency.hashCode() + value.hashCode() + exponent.hashCode()

    override fun toString(): String = toRepresentationString(PaymentAmountStringOptions()) // TODO now returns the default of toRepresentationString

    fun toRepresentationString(options: PaymentAmountStringOptions = PaymentAmountStringOptions()): String {
        val wholes: String
        val fraction: String
        val negative: Boolean = value < 0
        //
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
        val currencyString: String = if (options.currency) currency.code + " " else ""
        return currencyString + (if (negative) "-" else "") + paddedWholes + if (paddedFraction == "") "" else ".$paddedFraction"
    }

    fun toJsonBody(): Map<String, Any> {
        val map = mapOf( // TODO JsonObject, we will see...
            "currency" to currency,
            "value" to value,
            "exponent" to exponent,
        )
        return map
    }
}
