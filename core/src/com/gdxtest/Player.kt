package com.gdxtest

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Player {
  private val texture = Texture("Player.png")
  var g = -90f
  var vy = 0f
  var time = 0f
  var x = 0f
  var y = 0f

  fun update(input: Input, gameState: GameState) {
    if(input.keyRight) {
      x += 1f
    }

    if(input.keyLeft) {
      x -= 1f
    }


    if (input.keyUp) {
      jump()
    } else {
      calcNextPos(input.deltaTime)
    }
  }

  fun render(gameState: GameState, batch: SpriteBatch) {
    batch.draw(texture, gameState.offsetX.toFloat() + x, gameState.offsetY.toFloat() + y)
  }

  private fun jump() {
    vy = 200f
    time = 0f
  }

  private fun calcNextPos(deltaT: Float) {
    time += deltaT
    vy += g * deltaT
    y += (g * time + vy) * deltaT;

    if (y < 0f) {
      y = 0f
      vy = 0f
      time = 0f
    }
  }

}
