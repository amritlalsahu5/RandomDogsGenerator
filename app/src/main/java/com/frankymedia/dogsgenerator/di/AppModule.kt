package com.frankymedia.dogsgenerator.di

import org.koin.dsl.module


/**
 *  See https://start.insert-koin.io/#/quickstart/android
 **/
val appModule = module{

    // Provide NoteDatabase
    //single{ Room.databaseBuilder(get(), NoteDatabase::class.java, NOTE_DATABASE_NAME).build() }

    // Provide NoteDao
    //single{ get<NoteDatabase>().noteDao() }

    // Provide NoteRepository
    //single<NoteRepository> { NoteRepositoryImpl(get()) }

    // Provide NoteViewModel
    //viewModel { NoteViewModel(get()) }
    //single{ Room.databaseBuilder(get(), NoteDatabase::class.java, NOTE_DATABASE_NAME).build() }
    //viewModel { NoteViewModel(get()) }


}