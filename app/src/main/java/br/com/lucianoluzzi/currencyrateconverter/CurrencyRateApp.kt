package br.com.lucianoluzzi.currencyrateconverter

import android.app.Application
import br.com.lucianoluzzi.currencyrateconverter.model.CurrenciesRateViewModel
import br.com.lucianoluzzi.currencyrateconverter.repository.CurrenciesRepositoryImpl
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CurrencyRateApp : Application() {
    private val modules = module {
        viewModel {
            CurrenciesRateViewModel(CurrenciesRepositoryImpl())
        }

        single {
            CurrenciesRepositoryImpl()
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(modules)
        }
        RxJavaPlugins.setErrorHandler {}
    }
}