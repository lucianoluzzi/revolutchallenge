package br.com.lucianoluzzi.currencyrateconverter.model

import br.com.lucianoluzzi.currencyrateconverter.repository.dto.RatesDTO
import java.math.BigDecimal
import kotlin.reflect.full.memberProperties

data class Currency(val id: Long, val name: String, var value: BigDecimal) {
    companion object {
        const val CURRENCY_VALUE_KEY = "CURRENCY_VALUE_KEY"

        fun getCurrencyListFromRatesDTO(baseCurrency: String, rates: RatesDTO?): MutableList<Currency> {
            val currencies = mutableListOf<Currency>()

            rates?.let {
                currencies.add(Currency(0, baseCurrency, BigDecimal(1)))
                it::class.memberProperties.forEachIndexed { index, member ->
                    if (member.getter.call(it) != null) {
                        val currency = Currency(
                            index.toLong() + 1,
                            member.name.toUpperCase(),
                            member.getter.call(it) as BigDecimal
                        )
                        currencies.add(currency)
                    }
                }
            }

            return currencies
        }
    }

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
        return result
    }
}