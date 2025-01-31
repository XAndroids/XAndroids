package com.android.xknowledge.sdk.component

import com.android.xknowledge.ListActivity
import com.android.xknowledge.sdk.component.activity.ActivityActivity
import com.android.xknowledge.sdk.component.broadcast.BroadcastActivity
import com.android.xknowledge.sdk.component.service.ServiceActivity

class ComponentActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Activity", "Activity相关", ActivityActivity::class.java),
            ListItem("Broadcast", "Broadcast相关", BroadcastActivity::class.java),
            ListItem("Service", "Service相关", ServiceActivity::class.java)
        )
    }
}