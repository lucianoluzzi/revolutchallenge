package br.com.lucianoluzzi.currencyrateconverter.view.binding_adapters

import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.currencyrateconverter.model.Currency
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

    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            val diffBundle = Bundle()
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            if (oldItem.value == newItem.value)
                diffBundle.putDouble(Currency.CURRENCY_VALUE_KEY, newItem.value.toDouble())

            return diffBundle
        }

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size
    }, true)

    currencyAdapter.currencies.clear()
    currencyAdapter.currencies.addAll(newList)
    diff.dispatchUpdatesTo(currencyAdapter)
}