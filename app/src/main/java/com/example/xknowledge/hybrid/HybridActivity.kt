package com.example.xknowledge.hybrid

import com.example.xknowledge.ListActivity
import com.example.xknowledge.hybrid.webview.WebviewActivity

class HybridActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Webview", "Webview的基本使用", WebviewActivity::class.java)
        );
    }
}
