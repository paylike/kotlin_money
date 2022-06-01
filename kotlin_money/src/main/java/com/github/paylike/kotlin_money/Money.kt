package com.github.paylike.kotlin_money

import com.github.paylike.kotlin_currencies.PaylikeCurrency

object Money {
    private const val maxInt: Long = Long.MAX_VALUE // 9223372036854775807L

    private fun isInSafeRange(n: Double): Boolean = (n <= maxInt && n >= -maxInt)

    fun fromDouble(currency: PaylikeCurrency, n: Double): PaymentAmount {
        if (!n.isFinite()) {
            throw Exception("Non finite number $n")
        }
        if (!isInSafeRange(n)) {
            throw UnsafeNumberException(n)
        }
        val split = n.toString().split('.')
        val wholes = split[0]
        val fraction: String = split[1]
        val value: Long = Integer.parseInt(wholes + fraction).toLong()
        val exponent: Int = if (value == 0L) {
            0
        } else {
            fraction.length
        }
        return PaymentAmount(
            currency,
            value,
            exponent
        )
    }
}
