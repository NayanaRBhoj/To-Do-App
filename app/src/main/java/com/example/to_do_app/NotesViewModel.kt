package com.example.to_do_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */
class NotesViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: NotesRepository
    // LiveData gives us updated words when they change.
    val allWords: LiveData<List<Notes>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val wordsDao = MyDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = NotesRepository(wordsDao)
        allWords = repository.allWords
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on the mainthread, blocking
     * the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called viewModelScope which we
     * can use here.
     */
    fun insert(word: Notes) = viewModelScope.launch {
        repository.insert(word)
    }
}
