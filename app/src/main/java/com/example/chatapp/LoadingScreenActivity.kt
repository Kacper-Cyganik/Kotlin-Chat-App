package com.example.chatapp


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoadingScreenActivity : AppCompatActivity()
{
    private lateinit var appLogo: ImageView
    private lateinit var loadingText: TextView
    private lateinit var appName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)

        val topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation)
        val bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        appLogo = findViewById(R.id.app_logo)
        loadingText = findViewById(R.id.loading_text)
        appName = findViewById(R.id.app_name)

        appLogo.startAnimation(topAnimation)
        appName.startAnimation(middleAnimation)
        loadingText.startAnimation(bottomAnimation)

        val loadingScreenTimeOut = 3500
        val intent = Intent(this@LoadingScreenActivity, LoginActivity::class.java)
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, loadingScreenTimeOut.toLong())

    }
}