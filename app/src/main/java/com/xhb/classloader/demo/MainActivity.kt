package com.xhb.classloader.demo

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.xhb.classloader.helloandroid.ISayHello
import dalvik.system.BaseDexClassLoader
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
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            100 -> {
                val hello = iSayHello
                if (hello != null) {
                    Toast.makeText(this, hello.say(), Toast.LENGTH_SHORT).show()
                } else {
                    val file = File(Environment.getExternalStorageDirectory(), "helloAndroid")
                    val dexFile = File(file, "helloAndroid_dex.jar")

                    if (dexFile.exists() && dexFile.isFile) {
                        val dexClassLoader =
                            DexClassLoader(dexFile.absolutePath, file.absolutePath, null, getClassLoader())

                        try {// 加载 HelloAndroid 类
                            val clazz = dexClassLoader.loadClass("com.xhb.classloader.helloandroid.HelloAndroid")
                            // 强转成 ISayHello, 注意 ISayHello 的包名需要和 jar 包中的一致
                            iSayHello = clazz.newInstance() as ISayHello?
                            iSayHello?.let {
                                Toast.makeText(this, it.say(), Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    } else {
                        throw RuntimeException("helloAndroid_dex.jar not exist")
                    }
                }
            }
        }
    }
}
