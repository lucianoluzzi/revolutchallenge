package br.com.lucianoluzzi.currencyrateconverter.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.currencyrateconverter.R
import br.com.lucianoluzzi.currencyrateconverter.databinding.CurrencyItemBinding
import br.com.lucianoluzzi.currencyrateconverter.model.Currency
import java.math.BigDecimal

class CurrencyAdapter(
    private val context: Context,
    val currencies: MutableList<Currency>,
    private var baseCurrency: MutableLiveData<String>,
    private var baseCurrencyValue: MutableLiveData<BigDecimal>
) :
    RecyclerView.Adapter<CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding =
            DataBindingUtil.inflate<CurrencyItemBinding>(layoutInflater, R.layout.currency_item, parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencies[position], baseCurrency, baseCurrencyValue)
    }

    override fun getItemId(position: Int): Long {
        return currencies[position].id
    }
}