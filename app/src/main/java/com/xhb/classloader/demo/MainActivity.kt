package com.xhb.classloader.demo

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.xhb.classloader.helloandroid.ISayHello
import dalvik.system.DexClassLoader
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private var iSayHello: ISayHello? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var classLoader = MainActivity::class.java.classLoader
        while (classLoader != null) {
            Log.d(TAG, classLoader.toString())
            classLoader = classLoader.parent
        }

        buttonPanel.setOnClickListener {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        }

        val properties = System.getProperties()
        properties.list(System.out)
        Log.i(TAG, "property java.ext.dirs = ${System.getProperty("java.ext.dirs")}")
        Log.i(TAG, "property java.class.path = ${System.getProperty("java.class.path")}")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            100 -> {
                dump()
            }
        }
    }

    private fun dump() {
        val hello = iSayHello
        if (hello != null) {
            Toast.makeText(this, hello.say(), Toast.LENGTH_SHORT).show()
        } else {
            val file = File(Environment.getExternalStorageDirectory(), "helloAndroid")
            val dexFile = File(file, "helloAndroid_dex.jar")

            if (dexFile.exists() && dexFile.isFile) {
                val odexDir = if (Build.VERSION.SDK_INT < 21) {
                    this@MainActivity.getDir("dex", 0)
                } else {
                    file
                }

                val dexClassLoader =
                    DexClassLoader(dexFile.absolutePath, odexDir.absolutePath, null, classLoader)

                try {   // 加载 HelloAndroid 类
                    val clazz = dexClassLoader.loadClass("com.xhb.classloader.helloandroid.HelloAndroid")
                    // 强转成 ISayHello, 注意 ISayHello 的包名需要和 jar 包中的一致
                    iSayHello = clazz.newInstance() as ISayHello?
                    dump()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, "helloAndroid_dex.jar not exist", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
