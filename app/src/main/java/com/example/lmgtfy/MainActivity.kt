package com.example.lmgtfy

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {

//    set up variables as references to pieces we set up in activity_main
//    variable_name: type
//    lateinit means not initialized yet but will be done asap
    private lateinit var searchText: EditText
    private lateinit var searchButton: Button
    private lateinit var searchConfirmation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      initialized variables
        searchText = findViewById(R.id.enter_search_text)
        searchButton = findViewById(R.id.search_button)
        searchConfirmation = findViewById(R.id.show_search_text)

//        first event handler - one that echoes text in text view
        searchText.doAfterTextChanged {
            echoUserSearchTerm()
        }

        searchButton.setOnClickListener{
            launchSearch()
        }
    }

    private fun launchSearch() {
        val text = searchText.text
        // check if isBlank vs. isEmpty because whitespace will count as not empty
        // but will also count as blank so use blank
        if (text.isBlank()) {
            // show user a message to enter text
            Toast.makeText(this, getString(R.string.no_text_error_message), Toast.LENGTH_SHORT).show()
        } else {
            // show a Toast confirmation
            Toast.makeText(this, getString(R.string.searching_confirmation_message, text), Toast.LENGTH_LONG).show()
            googleSearch(text.toString())
        }
    }

    private fun googleSearch(text: String) {
        // launch web browser to search Google
        // not using a string resource because it's not text the user will see
        val webAddress = "https://www.google.com/search?q=$text"
        // uri = uniform resource indicator
        val uri = Uri.parse(webAddress)
        // intent of take action of open this uri - parse this web address
        // android will be able to identify that this is a web address and
        // will send it to an app that can handle web addresses
        val browserIntent = Intent(Intent.ACTION_VIEW, uri)
        // tell android to start an activity based on the browserIntent variable
        startActivity(browserIntent)
    }

    private fun echoUserSearchTerm() {
        val text = searchText.text
        // set conditional to check if search box is blank and dictate text
        // of search_display_text based on that evaluation
        if (text.isNotBlank()) {
            searchConfirmation.text = getString(R.string.search_display_text, text)
        } else {
            searchConfirmation.text = ""
        }
    }
}