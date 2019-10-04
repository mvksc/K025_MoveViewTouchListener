package m.vk.k025_moveviewtouchlistener


import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class MovVieTouchListener : View.OnTouchListener {
    private var mView: View
    private var screenHeight = 0
    private var screenWidth = 0
    private var dX: Float = 0.toFloat()
    private var dY: Float = 0.toFloat()
    var prevY = 0
    var prevX = 0
    constructor(mRoot: View,mView: View){
        this.mView = mView
        mRoot.viewTreeObserver
            .addOnGlobalLayoutListener {
                screenHeight = mRoot.height
                screenWidth = mRoot.width
            }
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        var newX: Float
        var newY: Float
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                dX = view!!.x - event.rawX
                dY = view.y - event.rawY
                prevY = event.rawY.toInt()
                prevX = event.rawX.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                newX = event.rawX + dX
                newY = event.rawY + dY
                // check if the view out of screen
                if (newX <= 0) {
                    newX = 0f
                }
                if (newX >= screenWidth - view!!.width) {
                    newX = (screenWidth - view.width).toFloat()
                }
                if (newY <= 0) {
                    newY = 0f
                }
                if (newY >= screenHeight - view.height) {
                    newY = (screenHeight - view.height).toFloat()
                }
                Log.e("Check","$newX : $newY")
                view.x = newX
                view.y = newY
            }

            MotionEvent.ACTION_UP -> {
                var y = event.rawY.toInt()
                var x = event.rawX.toInt()
                val diffY = abs(prevY - y)
                val diffX = abs(prevX - x)
                return !(diffX in 0..10 && diffY in 0..10)
            }
        }
        return false
    }
}