package com.gdxtest

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Player {
  private val texture = Texture("Player.png")
  var g = -90f
  var vy = 0f
  var vx = 0f
  var time = 0f
  var x = 0f
  var y = 0f

  fun update(input: Input, gameState: GameState) {
    if (input.keyRight) {
      vx += 1f
    }

    if (input.keyLeft) {
      vx -= 1f
    }

    if (input.keyUp) {
      jump()
    } else {
      calcNextPos(input.deltaTime)
    }

    updateGameState(gameState)
  }

  fun render(gameState: GameState, batch: SpriteBatch) {
    batch.draw(texture, x - gameState.offsetX.toFloat() * BLOCK_WIDTH, y - gameState.offsetY.toFloat() * BLOCK_HEIGHT)
  }

  private fun updateGameState(gameState: GameState) {
    var offsetX = x - gameState.screenWidth / 2
    var offsetY = y - gameState.screenHeight / 2
    val maxX = WORLD_WIDTH.toFloat() * BLOCK_WIDTH
    val maxY = WORLD_HEIGHT.toFloat() * BLOCK_HEIGHT

    if (offsetX < 0f) {
      offsetX = 0f
    } else if (offsetX > maxX) {
      offsetX = maxX - gameState.screenWidth
    }

    if (offsetY < 0f) {
      offsetY = 0f
    } else if (offsetY > maxY) {
      offsetY = maxY - gameState.screenHeight
    }

    gameState.offsetX = (offsetX / BLOCK_WIDTH).toInt()
    gameState.offsetY = (offsetY / BLOCK_HEIGHT).toInt()
  }

  private fun jump() {
    vy = 200f
    time = 0f
  }

  private fun calcNextPos(deltaT: Float) {
    time += deltaT

    x += vx * deltaT

    if (x < 0f) {
      x = 0f
      vx = 0f
    }

    vy += g * deltaT
    y += (g * time + vy) * deltaT;

    if (y < 0f) {
      y = 0f
      vy = 0f
      time = 0f
    }
  }
}
