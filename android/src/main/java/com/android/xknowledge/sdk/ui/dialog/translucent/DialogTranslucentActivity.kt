package com.android.xknowledge.sdk.ui.dialog.translucent

import android.os.Bundle
import android.widget.Button
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class DialogTranslucentActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_translucent)

        findViewById<Button>(R.id.fulldialog_button_notranslucent).setOnClickListener {
            val noTranslucentDialog =
                NoTranslucentDialog()
            noTranslucentDialog.show(supportFragmentManager, "NoTranslucentDialog")
        }

        findViewById<Button>(R.id.fulldialog_button_translucent).setOnClickListener {
            val translucentDialog =
                TranslucentDialog()
            translucentDialog.show(supportFragmentManager, "TranslucentDialog")
        }
    }
}
