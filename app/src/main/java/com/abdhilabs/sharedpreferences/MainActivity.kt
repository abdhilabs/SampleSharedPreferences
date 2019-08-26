package com.abdhilabs.sharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tNama.text = Preferences.getLoggedInUser(baseContext)

        btnLogOut.setOnClickListener {
            Preferences.clearLoggedInUser(baseContext)
            startActivity(Intent(baseContext,LoginActivity::class.java))
            finish()
        }
    }
}
