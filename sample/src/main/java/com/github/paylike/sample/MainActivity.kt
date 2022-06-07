package com.github.paylike.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.paylike.kotlin_currencies.PaylikeCurrencies
import com.github.paylike.kotlin_currencies.PaylikeCurrency
import com.github.paylike.kotlin_currencies.generated.CurrencyCode
import com.github.paylike.kotlin_money.Money
import com.github.paylike.kotlin_money.PaymentAmount
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val eur: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.EUR)
        val amount: PaymentAmount = Money.fromDouble(eur, 12.5)
        Log.i("PaymentAmount", amount.toRepresentationString())
        Log.i("PaymentAmount", Json.encodeToString(amount))
    }
}
