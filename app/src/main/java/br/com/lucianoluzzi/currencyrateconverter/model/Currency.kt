package br.com.lucianoluzzi.currencyrateconverter.model

import java.math.BigDecimal

data class Currency(val id: Long, val name: String, val value: BigDecimal) {
    companion object {
        const val CURRENCY_VALUE_KEY = "CURRENCY_VALUE_KEY"
    }

    val exibitValue = value.toString()

    override fun equals(other: Any?): Boolean {
        if (other !is Currency)
            return false

        return this.id == other.id &&
                this.name == other.name &&
                this.value == other.value
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + value.hashCode()
        result = 31 * result + exibitValue.hashCode()
        return result
    }
}