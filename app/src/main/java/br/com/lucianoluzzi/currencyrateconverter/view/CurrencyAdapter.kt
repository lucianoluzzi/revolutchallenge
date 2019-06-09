package br.com.lucianoluzzi.currencyrateconverter.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.lucianoluzzi.currencyrateconverter.R
import br.com.lucianoluzzi.currencyrateconverter.databinding.CurrencyItemBinding
import br.com.lucianoluzzi.currencyrateconverter.model.Currency

class CurrencyAdapter(
    private val context: Context,
    val currencies: MutableList<Currency>,
    private val itemListener: CurrencyItemListener
) :
    RecyclerView.Adapter<CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding =
            DataBindingUtil.inflate<CurrencyItemBinding>(layoutInflater, R.layout.currency_item, parent, false)
        return CurrencyViewHolder(binding, itemListener)
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.setCurrency(currencies[position])
    }

    override fun getItemId(position: Int): Long {
        return currencies[position].id
    }
}