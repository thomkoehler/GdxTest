package com.gdxtest

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface IWorld {
  var offsetX: Int
  val offsetY: Int

  fun show(batch: SpriteBatch)
}
