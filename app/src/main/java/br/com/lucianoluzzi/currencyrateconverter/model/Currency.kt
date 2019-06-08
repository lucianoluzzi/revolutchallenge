package br.com.lucianoluzzi.currencyrateconverter.model

import java.math.BigDecimal

data class Currency(val name: String, val value: BigDecimal) {
    val exibitValue = value.toString()
}