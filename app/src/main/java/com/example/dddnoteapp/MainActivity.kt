package com.example.dddnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
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
import org.koin.androidx.compose.get
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            DDDNoteAppTheme {
                val navController = rememberNavController()

                NoteApp(
                    notesViewModel = NoteListViewModel(get()),
                    noteDetailViewModel = NoteDetailViewModel(get()),
                    navController = navController
                )
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