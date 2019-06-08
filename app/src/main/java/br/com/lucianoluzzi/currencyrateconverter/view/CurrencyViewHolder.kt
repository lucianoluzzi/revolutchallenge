package br.com.lucianoluzzi.currencyrateconverter.view

import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.currencyrateconverter.databinding.CurrencyItemBinding
import br.com.lucianoluzzi.currencyrateconverter.model.Currency

class CurrencyViewHolder(private val binding: CurrencyItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setCurrency(currency: Currency) {
        binding.currency = currency
    }
}