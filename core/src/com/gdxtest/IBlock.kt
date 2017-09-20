package com.gdxtest

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface IBlock {
  fun show(batch: SpriteBatch, x: Int, y: Int)
}