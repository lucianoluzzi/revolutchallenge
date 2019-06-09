package br.com.lucianoluzzi.currencyrateconverter.view

import br.com.lucianoluzzi.currencyrateconverter.model.Currency

interface CurrencyItemListener {
    fun onValueChanged(currency: Currency)
    fun onCurrencySelected(currency: Currency)
}