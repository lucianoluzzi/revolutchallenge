package br.com.lucianoluzzi.currencyrateconverter.view.binding_adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.currencyrateconverter.model.Currency
import br.com.lucianoluzzi.currencyrateconverter.model.CurrencyListDiffCalculator
import br.com.lucianoluzzi.currencyrateconverter.view.CurrencyAdapter

@BindingAdapter("rates_adapter", requireAll = false)
fun RecyclerView.setRatesAdapter(adapter: CurrencyAdapter?) {
    adapter?.let {
        it.setHasStableIds(true)
        this.adapter = it
        layoutManager = LinearLayoutManager(context)
    }
}

@BindingAdapter("currencies", requireAll = false)
fun RecyclerView.setCurrencies(currencies: List<Currency>?) {
    val currencyAdapter = adapter as CurrencyAdapter
    val oldList = currencyAdapter.currencies
    val newList = currencies.orEmpty()

    val diff = CurrencyListDiffCalculator(oldList, newList).calculateDiff()
    currencyAdapter.currencies.clear()
    currencyAdapter.currencies.addAll(newList)

    diff.dispatchUpdatesTo(currencyAdapter)
}