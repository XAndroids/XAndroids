package com.android.xknowledge.test.robolectric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.xknowledge.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
