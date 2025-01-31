package com.android.xknowledge.sdk.ui.view

import com.android.xknowledge.ListActivity
import com.android.xknowledge.sdk.ui.view.covered.CoveredActivity
import com.android.xknowledge.sdk.ui.view.recyclerview.RecyclerViewActivity
import com.android.xknowledge.sdk.ui.view.textview.TextViewActivity
import com.android.xknowledge.sdk.ui.view.toast.ToastActivity
import com.android.xknowledge.sdk.ui.view.uiupdate.UIUpdateActivity
import com.android.xknowledge.sdk.ui.view.viewpager.ViewPagerCacheActivity
import com.android.xknowledge.sdk.ui.view.viewstub.ViewStubActivity

class ViewActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("RecyclerView", "RecyclerView相关", RecyclerViewActivity::class.java),
            ListItem("ViewPager", "ViewPager缓存相关", ViewPagerCacheActivity::class.java),
            ListItem("Covered", "View遮挡检测相关", CoveredActivity::class.java),
            ListItem("ViewStub", "ViewStub使用相关", ViewStubActivity::class.java),
            ListItem("Toast", "Toast使用相关", ToastActivity::class.java),
            ListItem("TextView", "TextView使用相关", TextViewActivity::class.java),
            ListItem("UIUpdateActivity", "子线程更新UI", UIUpdateActivity::class.java)
        )
    }
}