package br.com.lucianoluzzi.currencyrateconverter.model.extension

fun <T> List<T>.swap(firstItemIndex: Int, secondItemIndex: Int): List<T> = this
    .toMutableList()
    .also {
        it[firstItemIndex] = this[secondItemIndex]
        it[secondItemIndex] = this[firstItemIndex]
    }