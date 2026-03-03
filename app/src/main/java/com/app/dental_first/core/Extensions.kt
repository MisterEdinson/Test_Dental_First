package com.app.dental_first.core

fun Int.toPriceFormat(): String {
    return String.format("%,d", this)
        .replace(',', ' ') + " ₽"
}