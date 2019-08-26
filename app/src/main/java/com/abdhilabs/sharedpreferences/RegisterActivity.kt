package com.abdhilabs.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        /* Menjalankan Method razia() jika merasakan tombol SignUp di keyboard disentuh */
        eRepassword.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                razia()
                return@OnEditorActionListener true
            }
            false
        })

        button_signupSignup.setOnClickListener {
            razia()
        }

    }

    private fun razia() {
        eUser?.error = null
        ePassword?.error = null
        eRepassword?.error = null
        var fokus: View? = null
        var cancel = false

        val user = eUser.text.toString()
        val password = ePassword.text.toString()
        val rePassword = eRepassword.text.toString()

        /* Jika form user kosong atau MEMENUHI kriteria di Method cekUser() maka, Set error di Form-
         * User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(user)) {
            eUser.error = "Harus di isi"
            fokus = eUser
            cancel = true
        } else if (cekUser(user)) {
            eUser.error = "Sudah ada"
            fokus = eUser
            cancel = true
        }

        /* Jika form password kosong dan MEMENUHI kriteria di Method cekPassword maka,
         * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)) {
            ePassword.error = "Harus di isi"
            fokus = ePassword
            cancel = true
        } else if (!cekPassword(password, rePassword)) {
            eRepassword.error = "Passwornya salah"
            fokus = eRepassword
            cancel = true
        }

        /** Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
         * Kembali ke LoginActivity dan Set User dan Password untuk data yang terdaftar  */
        if (cancel) {
            fokus?.requestFocus()
        } else {
            Preferences.setRegisteredUser(baseContext, user)
            Preferences.setRegisteredPass(baseContext, password)
            finish()
        }
    }

    private fun cekPassword(password: String, rePassword: String): Boolean {
        return password == rePassword
    }

    private fun cekUser(user: String): Boolean {
        return user == Preferences.getRegisteredUser(baseContext)
    }
}
