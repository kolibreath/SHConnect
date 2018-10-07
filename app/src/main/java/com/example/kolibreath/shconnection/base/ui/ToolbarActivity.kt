package com.example.kolibreath.shconnection.base.ui

import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.kolibreath.shconnection.R
import com.example.kolibreath.shconnection.R.id
import com.example.kolibreath.shconnection.base.RxBus
import com.example.kolibreath.shconnection.base.ScanEvent
import com.example.kolibreath.shconnection.extensions.decode
import org.jetbrains.anko.find
import rx.Subscriber
import rx.Subscription

open class ToolbarActivity : BaseActivity() {

  private lateinit var mSubscription: Subscription
  private lateinit var mClassId : String

  private lateinit var mToolbar: Toolbar

  private lateinit var mActionBar : ActionBar

  override fun setContentView(layoutResID: Int) {
    super.setContentView(layoutResID)
    initToolbar()
  }


  private fun initToolbar(){

    mToolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(mToolbar)

    mToolbar.title = ""

    mActionBar = supportActionBar!!
    enableBack()
  }

  protected fun enableBack(boolean: Boolean = true) = mActionBar.setDisplayHomeAsUpEnabled(boolean)

  private fun setTitle(title:String){
    mToolbar.title = ""
      val textView:TextView = mToolbar.find(id.tv_title)
    textView.text = title
    setSupportActionBar(mToolbar)

  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId){
      android.R.id.home ->{
        onBackPressed()
      }

    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_scan,menu)
    return true
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onDestroy() {
    mSubscription.unsubscribe()
    super.onDestroy()
  }
  override fun onBackPressed() {
    if(supportFragmentManager.backStackEntryCount > 0)
      supportFragmentManager.popBackStack()
    else
      super.onBackPressed()
  }
}
