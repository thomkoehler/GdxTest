package com.gdxtest

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Player {
  private val texture = Texture("Player.png")
  private var x = 100f
  private var y = 100f

  fun show(batch: SpriteBatch, offsetX: Int, offsetY: Int) {
    batch.draw(texture, offsetX.toFloat() + x, offsetY.toFloat() + y)
  }
}
