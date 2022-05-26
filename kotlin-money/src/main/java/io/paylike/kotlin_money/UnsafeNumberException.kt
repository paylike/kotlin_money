package io.paylike.kotlin_money

class UnsafeNumberException(var n: Double) : Exception() {
    override val message: String = "Number is not in safe range $n"
    // TODO: which one should i use?
    override fun toString(): String {
        return "Number is not in safe range $n"
    }
}