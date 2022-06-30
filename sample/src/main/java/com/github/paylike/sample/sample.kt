package com.github.paylike.sample

import com.github.paylike.kotlin_money.Money
import com.github.paylike.kotlin_money.PaymentAmount
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main()
{
    val amount: PaymentAmount = Money.fromDouble("EUR", 12.5)
    println(amount.toRepresentationString())
    println(Json.encodeToString(amount))
}
