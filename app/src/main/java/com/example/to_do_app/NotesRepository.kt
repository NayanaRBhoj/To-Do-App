package com.example.to_do_app

import androidx.lifecycle.LiveData

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class NotesRepository(private val wordDao: NotesDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Notes>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Notes) {
        wordDao.insert(word)
    }
}
