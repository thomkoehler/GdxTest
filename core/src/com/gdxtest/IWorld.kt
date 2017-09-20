package com.gdxtest

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface IWorld {
  fun show(batch: SpriteBatch)

  fun changeX(deltaX: Int)
}
