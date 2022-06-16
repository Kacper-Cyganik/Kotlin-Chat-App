package com.example.chatapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd

class LoadingScreenActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)

        val imageView = findViewById<ImageView>(R.id.arrow)

        val moveRight = ObjectAnimator.ofFloat(imageView, "translationX", 1150f).apply{duration = 3500}
        val moveLeft = ObjectAnimator.ofFloat(imageView, "translationX", 10f).apply{duration = 3500}
        val rotate = ObjectAnimator.ofFloat(imageView, "rotationY", 0.0f, 180f).apply { duration = 100 }

        val runner = AnimatorSet().apply {
            play(moveRight)
            play(moveLeft).after(moveRight).with(rotate)
        }
        val intent = Intent(this, LoginActivity::class.java)
        AnimatorSet().apply {
            play(runner)
            start()
            doOnEnd {
                startActivity(intent)
                finish()
            }
        }
    }
}