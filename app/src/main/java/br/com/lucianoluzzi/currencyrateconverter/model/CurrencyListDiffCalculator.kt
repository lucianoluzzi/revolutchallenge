package br.com.lucianoluzzi.currencyrateconverter.model

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

class CurrencyListDiffCalculator(private val oldList: List<Currency>, private val newList: List<Currency>) {
    fun calculateDiff(): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
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
                if (oldItem.value != newItem.value)
                    diffBundle.putDouble(Currency.CURRENCY_VALUE_KEY, newItem.value.toDouble())

                return diffBundle
            }

            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = newList.size
        })
    }
}