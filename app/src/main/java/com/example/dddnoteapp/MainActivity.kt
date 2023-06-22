package com.example.dddnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.dddnoteapp.di.appModules
import com.example.dddnoteapp.ui.theme.DDDNoteAppTheme
import com.example.ui.navigation.NoteApp
import com.example.ui.notedetails.NoteDetailViewModel
import com.example.ui.notelist.NoteListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity(), KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        startKoin {
//            androidContext(this@MainActivity)
//            modules(appModules)
//        }

        setContent {
            DDDNoteAppTheme {
                val navController = rememberNavController()
                val noteListViewModel: NoteListViewModel = viewModel()
                val noteDetailViewModel: NoteDetailViewModel = viewModel()

                NoteApp(
                    notesViewModel = noteListViewModel,
                    noteDetailViewModel = noteDetailViewModel,
                    navController = navController
                )

//                NoteApp(
//                    notesViewModel = NoteListViewModel(get(), get())
//                )
            }
        }


    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DDDNoteAppTheme {
        Greeting("Android")
    }
}