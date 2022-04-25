package edu.fzu.mobius

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import edu.fzu.mobius.ui.navigation.ComposeNavigation
import edu.fzu.mobius.ui.theme.MobiusTheme
import android.view.WindowManager

import android.os.Build
import android.view.View
import android.view.Window

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobiusTheme {
               ComposeNavigation()
            }
        }
//        setTransparent(window = window)
        window.setStatusBarColor(0xFFF2F1F6.toInt())
        adaptAndroidP(window = window)
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }
    private fun hideSystemUI() {
        val decorView: View = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY //(修改这个选项，可以设置不同模式)
                //使用下面三个参数，可以使内容显示在system bar的下面，防止system bar显示或
                //隐藏时，Activity的大小被resize。
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // 隐藏导航栏和状态栏
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    /**
     * 1.使状态栏透明
     * @param window
     */
    private fun setTransparent(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            window.setStatusBarColor(Color.TRANSPARENT)
        }
    }

    private fun adaptAndroidP(window: Window) {
        // 适配刘海屏,全屏去黑状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val layoutParams = window.attributes
            layoutParams.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = layoutParams
        }
    }

//重写Activity该方法，当窗口焦点变化时自动隐藏system bar，这样可以排除在弹出dialog和menu时，
//system bar会重新显示的问题(弹出dialog时似乎还是可以重新显示的0.0)。

}







