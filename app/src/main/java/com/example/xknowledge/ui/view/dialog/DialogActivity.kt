package com.example.xknowledge.ui.view.dialog

import com.example.xknowledge.ListActivity

class DialogActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("WebViewDialog", "实现在全屏完全透明的DialogFragment中，嵌套全屏完全透明的WebView", DialogWebviewActivity::class.java)
        )
    }
}
