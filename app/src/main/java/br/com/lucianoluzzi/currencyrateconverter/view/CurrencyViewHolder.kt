package br.com.lucianoluzzi.currencyrateconverter.view

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.currencyrateconverter.databinding.CurrencyItemBinding
import br.com.lucianoluzzi.currencyrateconverter.model.Currency

class CurrencyViewHolder(private val binding: CurrencyItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(currency: Currency, baseCurrency: MutableLiveData<String>) {
        binding.container.setOnClickListener {
            binding.currency?.let {
                baseCurrency.value = it.name
            }
        }

        binding.currency = currency
        binding.baseCurrency = baseCurrency.value
    }
}