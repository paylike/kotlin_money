package com.github.paylike.kotlin_money

    /**
     * Describes the options for string representation of PaymentAmount
     */
data class PaymentAmountStringOptions(
    /**
     * Describes adding for the integers
     */
    val padIntegers: Int = 0,

    /**
     * Describes padding for the fractions
     */
    val padFractions: Int = 0,

    /**
     * Indicates to include currency or not
     */
    val currency: Boolean = true,
)
