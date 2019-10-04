package m.vk.k025_moveviewtouchlistener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTouch.setOnTouchListener(MovVieGesTouchListener(rlRoot,tvTouch))
        tvTouch2.setOnTouchListener(MovVieTouchListener(rlRoot,tvTouch2))
        rlTouch3.setOnTouchListener(MovVieTouchListener(rlRoot,rlTouch3))

        tvTouch.setOnClickListener{
            Toast.makeText(this@MainActivity,"onClick G",Toast.LENGTH_LONG).show()
        }
        tvTouch2.setOnClickListener{
            Log.e("Check","onClick T")
            Toast.makeText(this@MainActivity,"onClick T",Toast.LENGTH_LONG).show()
        }
        rlTouch3.setOnClickListener{
            Log.e("Check","onClick M")
            Toast.makeText(this@MainActivity,"onClick M",Toast.LENGTH_LONG).show()
        }
    }
}
