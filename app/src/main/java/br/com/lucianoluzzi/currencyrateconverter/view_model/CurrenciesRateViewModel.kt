package br.com.lucianoluzzi.currencyrateconverter.view_model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import br.com.lucianoluzzi.currencyrateconverter.model.Currency
import br.com.lucianoluzzi.currencyrateconverter.repository.CurrenciesRepository
import br.com.lucianoluzzi.currencyrateconverter.repository.dto.RatesDTO
import br.com.lucianoluzzi.currencyrateconverter.view.CurrencyAdapter
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

class CurrenciesRateViewModel(private val applicationContext: Context, private val repository: CurrenciesRepository) :
    BaseViewModel() {
    val currencyAdapter: CurrencyAdapter by lazy {
        CurrencyAdapter(
            applicationContext,
            mutableListOf(),
            baseCurrency,
            baseCurrencyValue
        )
    }

    val currenciesRates: MutableLiveData<MutableList<Currency>> = MutableLiveData<MutableList<Currency>>().apply {
        mutableListOf<List<Currency>>()
    }
    var baseCurrency: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = RatesDTO::eur.name.toUpperCase()
    }
    private var baseCurrencyValue: MutableLiveData<BigDecimal> = MutableLiveData<BigDecimal>().apply {
        value = BigDecimal(1)
    }

    private fun getBaseCurrency() = baseCurrency.value?.let {
        it
    } ?: run {
        RatesDTO::eur.name.toUpperCase()
    }

    private fun getBaseCurrencyValue() = baseCurrencyValue.value?.let {
        it
    } ?: run {
        BigDecimal(1)
    }

    suspend fun startFetchingRates() = withContext(Dispatchers.IO) {
        val interval = Observable.interval(0, 1, TimeUnit.SECONDS)
        val disposable = interval
            .subscribe {
                val currenciesRate = repository.getCurrenciesRate(getBaseCurrency().toUpperCase())
                currenciesRates.postValue(
                    Currency.getCurrencyListFromRatesDTO(getBaseCurrency(), getBaseCurrencyValue(), currenciesRate)
                )
            }
        addDisposable(disposable)
    }

    fun stopFetching() {
        onCleared()
    }
}