package br.com.lucianoluzzi.currencyrateconverter.view.binding_adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.currencyrateconverter.model.Currency
import br.com.lucianoluzzi.currencyrateconverter.view.CurrencyAdapter

@BindingAdapter("currencies", requireAll = false)
fun RecyclerView.setCurrencies(currencies: List<Currency>?) {
    this.adapter = currencies?.let {
        CurrencyAdapter(context, it)
    } ?: run {
        CurrencyAdapter(context, listOf())
    }
    layoutManager = LinearLayoutManager(context)
}