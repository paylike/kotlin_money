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
import com.github.paylike.kotlin_money.Money
import com.github.paylike.kotlin_money.PaymentAmount
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
// ...

    // ...
    val amount: PaymentAmount = Money.fromDouble("EUR", 12.5)
    println("PaymentAmount", amount.toRepresentationString())
    println("PaymentAmount", amount.toJsonBody().toString())
    // ...
```
