package br.com.lucianoluzzi.currencyrateconverter.repository

import br.com.lucianoluzzi.currencyrateconverter.repository.dto.RatesDTO

interface CurrenciesRepository {
    fun getCurrenciesRate(baseCurrency: String): RatesDTO?
}