package io.paylike.kotlin_money

data class PaymentAmountStringOptions(
    val padIntegers: Int = 0,
    val padFractions: Int = 0,
    val currency: Boolean = true,
)
