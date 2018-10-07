package com.example.kolibreath.shconnection.extensions

import android.content.Context
import android.preference.PreferenceManager

fun <T>Context.putValue(key:String,value:T){
  val editor = PreferenceManager.getDefaultSharedPreferences(this).edit()

  when(value){
    is Int -> {
      editor.putInt(key,value)
    }
    is Boolean -> {
      editor.putBoolean(key,value)
    }
    is Float -> {
      editor.putFloat(key,value)
    }
    is String -> {
      editor.putString(key,value)
    }
    is Long -> {
      editor.putLong(key,value)
    }
    else ->{
    throw IllegalArgumentException("no such type")
    }
  }

  editor.apply()
}


fun <T> Context.getValue(key:String,default:T):T = with(PreferenceManager.getDefaultSharedPreferences(this)){
  val value:Any = when(default){
    is Int ->  getInt(key,default)
    is Long -> getLong(key,default)
    is Boolean -> getBoolean(key,default)
    is String -> getString(key,default)
    is Float -> getFloat(key,default)
    else->{
      throw Exception("error element")
    }
  }
  value as T
}


