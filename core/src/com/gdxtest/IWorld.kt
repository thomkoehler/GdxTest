package com.gdxtest

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface IWorld {
  fun render(gameState: GameState, batch: SpriteBatch)
  fun getXCollision(x0: Float, x1: Float): Float?
  fun getYCollision(y0: Float, y1: Float): Float?
}
