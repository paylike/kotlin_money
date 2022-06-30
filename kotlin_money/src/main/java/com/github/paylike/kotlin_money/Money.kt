package com.github.paylike.kotlin_money

object Money {
    private const val maxInt: Long = 9007199254740991L

    private fun isInSafeRange(n: Double): Boolean = (n <= maxInt && n >= -maxInt)

    fun fromDouble(currency: String, n: Double): PaymentAmount {
        if (!isInSafeRange(n) || !n.isFinite()) {
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
