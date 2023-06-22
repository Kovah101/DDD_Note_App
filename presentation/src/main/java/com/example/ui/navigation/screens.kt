package com.example.ui.navigation

sealed class Screen(val route: String) {
    object NoteList : Screen("noteList")
    object NoteDetail : Screen("noteDetail")
}