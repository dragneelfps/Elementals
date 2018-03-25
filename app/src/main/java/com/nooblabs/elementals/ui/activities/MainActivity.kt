package com.nooblabs.elementals.ui.activities

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.nooblabs.elementals.R
import com.nooblabs.elementals.ui.activities.base.AbsSlidingMusicPanelActivity
import kotlinx.android.synthetic.main.activity_drawer_layout.*
import kotlinx.android.synthetic.main.activity_drawer_layout.view.*

/**
 * Created by sourabh on 25/3/18.
 */
class MainActivity : AbsSlidingMusicPanelActivity(){

    @LayoutRes val drawerLayoutRes = R.layout.activity_drawer_layout
    @LayoutRes val mainActivityRes = R.layout.activity_main_layout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpDrawerLayout()
    }

    fun setUpDrawerLayout(){
        setUpToolBar()
        actionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawer_layout, R.string.close_drawer,
                R.string.open_drawer){}

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.library -> {
                    TODO("Open library activity")
                }
            }
            false
        }

        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    fun setUpToolBar(){
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_48dp)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Returns the contentView of the layout activity is using.
     * It is used in setContentView() in the base activity
     */
    override fun getContentView(): View {
        val contentView = layoutInflater.inflate(drawerLayoutRes, null)
        contentView.drawer_content_container.addView(wrapSlidingMusicPanel(mainActivityRes))
        return contentView
    }

}