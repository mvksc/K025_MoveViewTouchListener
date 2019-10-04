package m.vk.k025_moveviewtouchlistener

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class MovVieGesTouchListener : View.OnTouchListener {
    var mGestureDetector: GestureDetector
    lateinit var mView: View
    private var screenHeight = 0
    private var screenWidth = 0


    constructor(mRoot: View,mView: View) {
        mGestureDetector = GestureDetector(mView.context,mGestureListener)
        this.mView = mView
        mRoot.viewTreeObserver
            .addOnGlobalLayoutListener{
                screenHeight = mRoot.height
                screenWidth = mRoot.width
        }
    }


    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return mGestureDetector.onTouchEvent(p1)
    }

    private val mGestureListener = object : GestureDetector.SimpleOnGestureListener() {
        private var dX: Float = 0.toFloat()
        private var dY: Float = 0.toFloat()

        override fun onDown(e: MotionEvent): Boolean {
            dX = mView.x - e.rawX
            dY = mView.y - e.rawY
            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            super.onLongPress(e)
            Toast.makeText(mView.context,"onLongClick onLongPress", Toast.LENGTH_LONG).show()
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            Toast.makeText(mView.context,"onClick onSingleTapUp", Toast.LENGTH_LONG).show()
            return false
        }



        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            var newX = e2.rawX + dX
            var newY = e2.rawY + dY
            // check if the view out of screen
            if (newX <= 0) {
                newX = 0f
            }
            if (newX >= screenWidth - mView!!.width) {
                newX = (screenWidth - mView.width).toFloat()
            }
            if (newY <= 0) {
                newY = 0f
            }
            if (newY >= screenHeight - mView.height) {
                newY = (screenHeight - mView.height).toFloat()
            }

            mView.x = newX
            mView.y = newY
            return true
        }
    }
}