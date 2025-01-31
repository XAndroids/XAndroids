package com.android.xknowledge.sdk.storage

import com.android.xknowledge.ListActivity
import com.android.xknowledge.sdk.storage.sharedpreferences.SharedPreferencesActivity

/**
 * 存储相关
 */
class StorageActivity : ListActivity(){
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem(
                "SharedPreferences",
                "SharedPreferences存储相关",
                SharedPreferencesActivity::class.java
            ), ListItem(
                "ExteralStorage",
                "外部存储相关",
                ExteralStorageActivity::class.java
            )
        )
    }
}