package com.example.catchthecat.application

import android.app.Application
import com.example.catchthecat.data.db.AppDatabase
import com.example.catchthecat.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Classe responsável pela criação das injeções de dependência usando Koin.
 */
class AppApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        // Start the library
        startKoin {
            androidContext(this@AppApplication)
            // AppModule.kt reference
            modules(viewModelModule)
        }
    }
}