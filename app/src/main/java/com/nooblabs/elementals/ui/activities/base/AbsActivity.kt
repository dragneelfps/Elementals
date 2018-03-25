package com.nooblabs.elementals.ui.activities.base

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import java.util.*

/**
 * Base Activity for all activities.
 * ask user for permissions
 * Implement onRequestPermissionResult() in the derived classes
 */
abstract class AbsActivity: AppCompatActivity() {

    companion object {
        private val PERMISSION_REQ_CODE = Random().nextInt()
    }

    var hadPermission: Boolean = false
    lateinit var permissions: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissions = getPermissionsToRequest()
        hadPermission = hasPermissions()
    }

    /**
     * Requests for permission if not granted
     */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if(!hasPermissions()){
            requestPermissions()
        }
    }

    /**
     * Checks if permissions has changed after the activity lost focus and do something on change
     */
    override fun onResume() {
        super.onResume()
        val hasPermissions = hasPermissions()
        if(hasPermissions != hadPermission){
            hadPermission = hasPermissions
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                onPermissionChanged(hasPermissions)
            }
        }
    }

    fun getPermissionsToRequest() =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WAKE_LOCK)
            } else {
                emptyArray()
            }

    fun hasPermissions(): Boolean{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            permissions.forEach {
                if(ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
                    return false
            }
        }
        return true
    }

    fun requestPermissions(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permissions, PERMISSION_REQ_CODE)
        }
    }

    /**
     * Does something but in future
     */
    fun onPermissionChanged(hasPermissions: Boolean){
        //Implement it in the future
    }

}