package com.abdhilabs.sharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ePasswordLogIn.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                razia()
                return@OnEditorActionListener true
            }
            false
        })

        btnSignIn.setOnClickListener { razia() }
        btnSignUp.setOnClickListener {
            startActivity(
                Intent(
                    baseContext,
                    RegisterActivity::class.java
                )
            )
        }
    }

    /** ke MainActivity jika data Status Login dari Data Preferences bernilai true  */
    override fun onStart() {
        super.onStart()
        if (Preferences.getLoggedInStatus(baseContext)) {
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        }
    }

    /** Men-check inputan User dan Password dan Memberikan akses ke MainActivity  */
    private fun razia() {
        eUserLogIn.error = null
        ePasswordLogIn.error = null
        var fokus: View? = null
        var cancel = false

        val user = eUserLogIn.text.toString()
        val password = ePasswordLogIn.text.toString()

        if (TextUtils.isEmpty(user)) {
            eUserLogIn.error = "Harus di isi"
            fokus = eUserLogIn
            cancel = true
        } else if (!cekUser(user)) {
            eUserLogIn.error = "Tidak ditemukan"
            fokus = eUserLogIn
            cancel = true
        }

        if (TextUtils.isEmpty(password)) {
            ePasswordLogIn.error = "Harus di isi"
            fokus = ePasswordLogIn
            cancel = true
        } else if (!cekPassword(password)) {
            ePasswordLogIn.error = "Salah le"
            fokus = ePasswordLogIn
            cancel = true
        }

        if (cancel) {
            fokus?.requestFocus()
        } else {
            masuk()
        }

    }

    private fun masuk() {
        Preferences.setLoggedInUser(baseContext, Preferences.getRegisteredUser(baseContext))
        Preferences.setLoggedInStatus(baseContext, true)
        startActivity(Intent(baseContext, MainActivity::class.java))
        finish()
    }

    private fun cekPassword(password: String): Boolean {
        return password == Preferences.getRegisteredPass(baseContext)
    }

    private fun cekUser(user: String): Boolean {
        return user == Preferences.getRegisteredUser(baseContext)
    }
}
