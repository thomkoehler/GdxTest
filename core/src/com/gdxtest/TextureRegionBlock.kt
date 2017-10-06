package com.gdxtest

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion

class TextureRegionBlock(val texture: TextureRegion) : IBlock {
  override fun show(batch: SpriteBatch, x: Float, y: Float) {
    batch.draw(texture, x * BLOCK_WIDTH, y * BLOCK_HEIGHT)
  }
}
