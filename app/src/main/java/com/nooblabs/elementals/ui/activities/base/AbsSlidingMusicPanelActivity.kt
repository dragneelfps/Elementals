package com.nooblabs.elementals.ui.activities.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import com.nooblabs.elementals.R
import kotlinx.android.synthetic.main.sliding_music_panel_layout.view.*

/**
 * Created by sourabh on 25/3/18.
 */
abstract class AbsSlidingMusicPanelActivity : AbsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
    }

    abstract fun getContentView(): View



    fun wrapSlidingMusicPanel(@LayoutRes resId: Int): View{
        val contentView = layoutInflater.inflate(R.layout.sliding_music_panel_layout, null)
        layoutInflater.inflate(resId, contentView.content_container)
        return contentView
    }
}