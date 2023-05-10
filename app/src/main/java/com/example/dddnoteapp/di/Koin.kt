package com.example.dddnoteapp.di

import com.example.data.database.NoteDatabase
import com.example.data.implementations.NoteRepositoryImpl
import com.example.data.mappers.NoteMapper
import com.example.domain.repositories.NoteRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val dataModule = module {
    single { NoteDatabase.buildDatabase(androidApplication()) }
    factory { get<NoteDatabase>().noteDao() }
    factory { NoteMapper() }
    factory<NoteRepository> { NoteRepositoryImpl(get(), get()) }
}

val domainModule = module {
    viewModel { NoteListViewModel(get()) }
    viewModel { NoteDetailViewModel(get()) }
}

val presentationModule = module {
    factory { NoteListScreen(get()) }
    factory { NoteDetailScreen(get()) }
}