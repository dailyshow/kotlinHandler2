package com.cis.kotlinhandler2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var isRunning = false
    var handlerUi : HandlerUI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener { view ->
            val time = System.currentTimeMillis()
            tv1.text = "버튼 클릭 : ${time}"
        }

        handlerUi = HandlerUI()

        isRunning = true
        val thread = ThreadClass()
        thread.start()

    }

    override fun onStop() {
        super.onStop()
        isRunning = false
    }

    inner class ThreadClass : Thread() {
        override fun run() {
            var a1 = 10
            var a2 = 20

            while (isRunning) {
//                val time = System.currentTimeMillis()
//                Log.d("time", "thread: ${time}")
//                tv2.text = "thread : ${time}"
//                handlerUi?.sendEmptyMessage(0)

                // handler에 메세지를 담아서 보낼 수 있다.
//                val msg = Message()
//                msg.what = 0
//                msg.obj = time
//                handlerUi?.sendMessage(msg)

                val msg2 = Message()
                msg2.what = 1
                msg2.arg1 = ++a1
                msg2.arg2 = ++a2
                msg2.obj = "hello~~"
                handlerUi?.sendMessage(msg2)
            }
        }
    }

    // 개발자가 발생시킨 thread 에서 화면에 관련된 처리를 하고자 할 때는 handler 를 활용한다.
    // 여기서는 로직처리를 하지 않는다. 화면 관련된 내용만 처리해준다.
    inner class HandlerUI : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

//            var time = System.currentTimeMillis()
//            tv2.text = "Handler : ${time}"

            if (msg.what == 0) {
                tv2.text = "Handler what == 0 : ${msg.obj}"
            } else if (msg.what == 1) {
                tv2.text = "Handler what == 1 : ${msg.arg1}, ${msg.arg2}, ${msg.obj}"
            }
        }
    }
}