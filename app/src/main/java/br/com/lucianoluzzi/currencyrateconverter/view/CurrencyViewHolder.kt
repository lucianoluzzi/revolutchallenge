package br.com.lucianoluzzi.currencyrateconverter.view

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.currencyrateconverter.databinding.CurrencyItemBinding
import br.com.lucianoluzzi.currencyrateconverter.model.Currency
import java.math.BigDecimal

class CurrencyViewHolder(private val binding: CurrencyItemBinding, private val itemListener: CurrencyItemListener) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.value.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.currency?.let {
                    it.value = BigDecimal(s.toString())
                    itemListener.onValueChanged(it)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        binding.value.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            
        }

        binding.container.setOnClickListener {
            binding.currency?.let {
                itemListener.onCurrencySelected(it)
            }
        }
    }

    fun setCurrency(currency: Currency) {
        binding.currency = currency
    }
}