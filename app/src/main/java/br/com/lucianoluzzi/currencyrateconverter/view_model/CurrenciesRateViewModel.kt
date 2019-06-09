package br.com.lucianoluzzi.currencyrateconverter.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lucianoluzzi.currencyrateconverter.model.Currency
import br.com.lucianoluzzi.currencyrateconverter.model.extension.swap
import br.com.lucianoluzzi.currencyrateconverter.repository.CurrenciesRepository
import br.com.lucianoluzzi.currencyrateconverter.repository.dto.RatesDTO
import br.com.lucianoluzzi.currencyrateconverter.view.CurrencyItemListener
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class CurrenciesRateViewModel(private val repository: CurrenciesRepository) : BaseViewModel() {
    private val _currenciesRates = MutableLiveData<MutableList<Currency>>().apply {
        mutableListOf<List<Currency>>()
    }
    val currenciesRates: LiveData<MutableList<Currency>> = _currenciesRates
    private var baseCurrency: String = RatesDTO::eur.name.toUpperCase()

    suspend fun startFetchingRates() = withContext(Dispatchers.IO) {
        val interval = Observable.interval(0, 1, TimeUnit.SECONDS)
        val disposable = interval
            .subscribe {
                val currenciesRate = repository.getCurrenciesRate(baseCurrency.toUpperCase())
                _currenciesRates.postValue(
                    Currency.getCurrencyListFromRatesDTO(baseCurrency, currenciesRate)
                )
            }
        addDisposable(disposable)
    }

    fun stopFetching() {
        onCleared()
    }

    val currencyItemListener: CurrencyItemListener
        get() {
            return object : CurrencyItemListener {
                override fun onValueChanged(currency: Currency) {

                }

                override fun onCurrencySelected(currency: Currency) {
                    if (currency.name != baseCurrency) {
                        val currencyActualIndex = _currenciesRates.value?.indexOf(currency)
                        currencyActualIndex?.let {
                            _currenciesRates.value?.swap(it, 0)
                        } ?: run {
                            _currenciesRates.value?.remove(currency)
                            _currenciesRates.value?.add(0, currency)
                        }

                        _currenciesRates.postValue(_currenciesRates.value)
                        baseCurrency = currency.name.toUpperCase()
                    }
                }
            }
        }
}