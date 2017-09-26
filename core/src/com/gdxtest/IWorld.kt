package com.gdxtest

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface IWorld {
  fun render(gameState: GameState, batch: SpriteBatch)
}
