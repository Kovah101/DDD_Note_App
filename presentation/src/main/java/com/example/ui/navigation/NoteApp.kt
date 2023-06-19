package com.example.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ui.notedetails.NoteDetailScreen
import com.example.ui.notedetails.NoteDetailViewModel
import com.example.ui.notelist.NoteListScreen
import com.example.ui.notelist.NoteListViewModel
import org.koin.androidx.compose.get

@Composable
fun NoteApp(notesViewModel: NoteListViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.NoteList.route) {
        composable(Screen.NoteList.route) {
            NoteListScreen(notesViewModel)
        }
        composable(
            Screen.NoteDetail.route + "/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
            val viewModel : NoteDetailViewModel = get()
            viewModel.setNoteId(noteId)
            NoteDetailScreen(viewModel)
        }
    }
}