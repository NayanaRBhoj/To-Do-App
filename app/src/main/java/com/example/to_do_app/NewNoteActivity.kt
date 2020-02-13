package com.example.to_do_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity for entering a word.
 */

class NewNoteActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var editDesc: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_word_layout)
        editTitle = findViewById(R.id.editTitle)
        editDesc = findViewById(R.id.editDesc)

        val saveNoteBtn = findViewById<Button>(R.id.save_btn)
        saveNoteBtn.setOnClickListener {
            checkAndSave()
        }
    }


    fun checkAndSave(){
        val replyIntent = Intent()
        val title = editTitle.text.toString()
        val desc = editDesc.text.toString()

        if (TextUtils.isEmpty(title)) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {

            replyIntent.putExtra(TITLE, title)
            replyIntent.putExtra(DESC, desc)
            setResult(Activity.RESULT_OK, replyIntent)
        }
        finish()

    }



    companion object {
        const val TITLE = "TITLE"
        const val DESC = "DESC"
    }
}

