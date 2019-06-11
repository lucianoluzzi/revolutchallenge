package br.com.lucianoluzzi.currencyrateconverter.view

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.currencyrateconverter.databinding.CurrencyItemBinding
import br.com.lucianoluzzi.currencyrateconverter.model.Currency
import java.math.BigDecimal

class CurrencyViewHolder(private val binding: CurrencyItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        currency: Currency,
        baseCurrency: MutableLiveData<String>,
        baseCurrencyValue: MutableLiveData<BigDecimal>
    ) {
        setListeners(baseCurrency, baseCurrencyValue)
        setBindingVariables(currency, baseCurrency.value)
        binding.executePendingBindings()
    }

    private fun setListeners(baseCurrency: MutableLiveData<String>, baseCurrencyValue: MutableLiveData<BigDecimal>) {
        binding.container.setOnClickListener {
            binding.currency?.let {
                baseCurrency.value = it.name
            }
        }
        binding.value.addTextChangedListener(getTextChangedListener(baseCurrencyValue))
    }

    private fun setBindingVariables(currency: Currency, baseCurrency: String?) {
        binding.currency = currency
        binding.baseCurrency = baseCurrency
    }

    private fun getTextChangedListener(baseCurrencyValue: MutableLiveData<BigDecimal>): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(inputedValue: Editable?) {
                if (binding.value.hasFocus()) {
                    if (inputedValue.toString().isEmpty())
                        baseCurrencyValue.value = BigDecimal(1)
                    else
                        baseCurrencyValue.value = BigDecimal(inputedValue.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }
}