package br.com.lucianoluzzi.currencyrateconverter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewModelScope
import br.com.lucianoluzzi.currencyrateconverter.R
import br.com.lucianoluzzi.currencyrateconverter.databinding.ActivityMainBinding
import br.com.lucianoluzzi.currencyrateconverter.model.CurrenciesRateViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val currenciesViewModel: CurrenciesRateViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.adapter = CurrencyAdapter(this, mutableListOf())
        binding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
        binding.viewModel = currenciesViewModel
        getRates()
    }

    private fun getRates() {
        currenciesViewModel.viewModelScope.launch {
            currenciesViewModel.startFetchingRates("EUR")
        }
    }

    override fun onPause() {
        super.onPause()
        currenciesViewModel.stopFetching()
    }
}
