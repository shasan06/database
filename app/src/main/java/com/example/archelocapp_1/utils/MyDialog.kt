package com.example.archelocapp_1.utils

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.example.archelocapp_1.R

class MyDialog(private val context: Context) {


    fun cancel(click: onClick){

        val dialog = Dialog(context, R.style.AppTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        val yes = dialog.findViewById(R.id.yes) as TextView
        val no = dialog.findViewById(R.id.No) as TextView

        yes.setOnClickListener {
            click.invoke(true)
        }

        no.setOnClickListener {
          dialog.dismiss()
        }

    }


}

typealias onClick = (Boolean) -> Unit
