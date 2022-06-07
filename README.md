# kotlin-money

<a href="https://jitpack.io/#paylike/kotlin_money" target="_blank">
    <img src="https://jitpack.io/v/paylike/kotlin_money.svg" />
</a>

Small utility library to help with working payment amounts in the paylike ecosystem.

## Features

Currently this package only supports a fraction of our JavaScript library. We may extend
functionality if we can find use-cases for it.

## Usage

```kotlin 
// ...
import com.github.paylike.kotlin_currencies.PaylikeCurrencies
import com.github.paylike.kotlin_currencies.PaylikeCurrency
import com.github.paylike.kotlin_currencies.generated.CurrencyCode
import com.github.paylike.kotlin_money.Money
import com.github.paylike.kotlin_money.PaymentAmount
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
// ...

    // ...
    val eur: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.EUR)
    val amount: PaymentAmount = Money.fromDouble(eur, 12.5)
    Log.i("PaymentAmount", amount.toRepresentationString())
    Log.i("PaymentAmount", amount.toJsonBody().toString())
    // ...
```
