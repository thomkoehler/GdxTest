package com.gdxtest

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Player {
  private val texture = Texture("Player.png")
  var g = -90f
  var vy = 0f
  var time = 0f
  var x = 100f
  var y = 200f

  fun jump() {
    vy = 100f
    time = 0f
  }

  fun calcNextPos(deltaT: Float) {
    time += deltaT
    vy += g * deltaT
    y += (g * time + vy) * deltaT;

    if(y == 0f) {
      y = 0f
      vy = 0f
      time = 0f
    }
  }

  fun show(batch: SpriteBatch, offsetX: Int, offsetY: Int) {
    batch.draw(texture, offsetX.toFloat() + x, offsetY.toFloat() + y)
  }
}
