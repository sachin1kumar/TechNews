package com.tech.ashort.short_technews.View

/**
 * Created by sachin on 1/1/18.
 */
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.tech.ashort.short_technews.R


/**
 * Created by sachin on 12/11/17.
 */

class CustomDialog(context: Context?) : Dialog(context){

    private var mSubmit: Button? = null
    private var mProgressBar: ProgressDialog?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutParams = window!!.attributes
        layoutParams.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL

        window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        window!!.attributes = layoutParams

        mProgressBar = ProgressDialog(context!!)
        mProgressBar!!.setCancelable(false)
        mProgressBar!!.setMessage("Please wait...")
        mProgressBar!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.cust_dialog)

        mSubmit = findViewById(R.id.submit)

        setCancelable(false)

        mSubmit!!.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    openMainActivity()
                }
            })
    }

    fun openMainActivity(){
        mProgressBar!!.show()
        val handler = Handler()
        val run = Runnable {
            val `in` = Intent(context!!, MainActivity::class.java)
            `in`.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(`in`)
            dismiss()
        }
        handler.post(run)
    }
}
