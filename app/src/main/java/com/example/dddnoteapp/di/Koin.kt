package com.example.dddnoteapp.di

import androidx.navigation.NavController
import androidx.room.Room
import com.example.data.daos.NoteDao
import com.example.data.database.NoteDatabase
import com.example.data.implementations.NoteRepositoryImpl
import com.example.data.mappers.NoteMapper
import com.example.domain.repositories.NoteRepository
import com.example.ui.notedetails.NoteDetailScreen
import com.example.ui.notedetails.NoteDetailViewModel
import com.example.ui.notelist.NoteListScreen
import com.example.ui.notelist.NoteListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.viewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

private const val NOTE_DATABASE_NAME = "note_database"

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            NoteDatabase::class.java,
            NOTE_DATABASE_NAME
        ).build()
    }
    single {
        val database = get<NoteDatabase>()
        database.noteDao()
    }
    factory { NoteMapper() }
    single<NoteRepository> { NoteRepositoryImpl(noteDao = get(), noteMapper = get()) }
}

val domainModule = module {

}

val presentationModule = module {
    viewModel { NoteListViewModel(noteRepository = get()) }
    viewModel { NoteDetailViewModel(noteRepository = get()) }

}

val appModules = listOf(dataModule, domainModule, presentationModule)