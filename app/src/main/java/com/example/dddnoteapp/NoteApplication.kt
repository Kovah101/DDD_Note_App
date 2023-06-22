package com.example.dddnoteapp

import android.app.Application
import com.example.dddnoteapp.di.appModules
import com.example.dddnoteapp.di.dataModule
import com.example.dddnoteapp.di.domainModule
import com.example.dddnoteapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class NoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NoteApplication)
            modules(appModules)
        }
    }
}
