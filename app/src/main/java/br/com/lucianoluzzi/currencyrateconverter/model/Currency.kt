package br.com.lucianoluzzi.currencyrateconverter.model

import br.com.lucianoluzzi.currencyrateconverter.repository.dto.RatesDTO
import java.math.BigDecimal
import kotlin.reflect.full.memberProperties

data class Currency(val id: Long, val name: String, var value: BigDecimal) {
    companion object {
        const val CURRENCY_VALUE_KEY = "CURRENCY_VALUE_KEY"

        fun getCurrencyListFromRatesDTO(
            baseCurrency: String,
            baseCurrencyValue: BigDecimal,
            rates: RatesDTO?
        ): MutableList<Currency> {
            val currencies = mutableListOf<Currency>()

            rates?.let {
                currencies.add(Currency(0, baseCurrency, baseCurrencyValue))
                it::class.memberProperties.forEachIndexed { _, member ->
                    if (member.getter.call(it) != null) {
                        val currency = Currency(
                            member.name.hashCode().toLong(),
                            member.name.toUpperCase(),
                            baseCurrencyValue.multiply(member.getter.call(it) as BigDecimal)
                        )
                        currencies.add(currency)
                    }
                }
            }

            return currencies
        }
    }
}