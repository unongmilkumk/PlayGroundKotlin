package com.unongmilk.myownapi

fun String.isInt(): Boolean {
    return try { this.toInt(); true } catch (e: NumberFormatException) { false }
}

fun String.isDouble(): Boolean {
    return try { this.toDouble(); true } catch (e: NumberFormatException) { false }
}

fun String.isFloat(): Boolean {
    return try { this.toFloat(); true } catch (e: NumberFormatException) { false }
}

fun String.isNumber(): Boolean {
    return this.isInt() || this.isDouble() || this.isFloat()
}