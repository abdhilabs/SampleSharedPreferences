package com.abdhilabs.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object Preferences {

    /**
     * Pendeklarasian key-data berupa String, untuk sebagai wadah penyimpanan data.
     * Jadi setiap data mempunyai key yang berbeda satu sama lain
     */
    private var KEY_USER_TEREGISTER = "user"
    private var KEY_PASS_TEREGISTER = "pass"
    private var KEY_USERNAME_SEDANG_LOGIN = "Username_logged_in"
    private var KEY_STATUS_SEDANG_LOGIN = "Status_logged_in"

    /**
     * Pendlakarasian Shared Preferences yang berdasarkan paramater context
     */
    private fun getSharedPreference(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    /**
     * Deklarasi Edit Preferences dan mengubah data
     * yang memiliki key isi KEY_USER_TEREGISTER dengan parameter username
     */
    fun setRegisteredUser(context: Context, username: String) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_USER_TEREGISTER, username)
        editor.apply()
    }

    /**
     * Mengembalikan nilai dari key KEY_USER_TEREGISTER berupa String
     */
    fun getRegisteredUser(context: Context): String? {
        return getSharedPreference(context).getString(KEY_USER_TEREGISTER, "")
    }

    /**
     * Deklarasi Edit Preferences dan mengubah data
     * yang memiliki key KEY_PASS_TEREGISTER dengan parameter password
     */
    fun setRegisteredPass(context: Context, password: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_PASS_TEREGISTER, password)
        editor.apply()
    }

    /**
     * Mengembalikan nilai dari key KEY_PASS_TEREGISTER berupa String
     */
    fun getRegisteredPass(context: Context): String? {
        return getSharedPreference(context).getString(KEY_PASS_TEREGISTER, "")
    }

    /**
     * Deklarasi Edit Preferences dan mengubah data
     * yang memiliki key KEY_USERNAME_SEDANG_LOGIN dengan parameter username
     */
    fun setLoggedInUser(context: Context, username: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_USERNAME_SEDANG_LOGIN, username)
        editor.apply()
    }

    /**
     * Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String
     */
    fun getLoggedInUser(context: Context): String? {
        return getSharedPreference(context).getString(KEY_USERNAME_SEDANG_LOGIN, "")
    }

    /**
     * Deklarasi Edit Preferences dan mengubah data
     * yang memiliki key KEY_STATUS_SEDANG_LOGIN dengan parameter status
     */
    fun setLoggedInStatus(context: Context, status: Boolean) {
        val editor = getSharedPreference(context).edit()
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN, status)
        editor.apply()
    }

    /**
     * Mengembalikan nilai dari key KEY_STATUS_SEDANG_LOGIN berupa boolean
     */
    fun getLoggedInStatus(context: Context): Boolean {
        return getSharedPreference(context).getBoolean(KEY_STATUS_SEDANG_LOGIN, false)
    }

    /**
     * Deklarasi Edit Preferences dan menghapus data, sehingga menjadikannya bernilai default
     * khusus data yang memiliki key KEY_USERNAME_SEDANG_LOGIN dan KEY_STATUS_SEDANG_LOGIN
     */
    fun clearLoggedInUser(context: Context) {
        val editor = getSharedPreference(context).edit()
        editor.remove(KEY_USERNAME_SEDANG_LOGIN)
        editor.remove(KEY_STATUS_SEDANG_LOGIN)
        editor.apply()
    }
}