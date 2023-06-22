package com.example.dddnoteapp

import com.example.dddnoteapp.di.appModules
import com.example.dddnoteapp.di.dataModule
import com.example.dddnoteapp.di.domainModule
import com.example.dddnoteapp.di.presentationModule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class CheckModulesTest : KoinTest {

    @Test
    fun checkDataModules() {
        dataModule.verify()
    }

    @Test
    fun checkDomainModules() {
        domainModule.verify()
    }

    @Test
    fun checkPresentationModules() {
        presentationModule.verify()
    }
}