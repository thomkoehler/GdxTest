package com.gdxtest

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import javax.swing.plaf.synth.Region


class GdxGame : ApplicationAdapter() {

  lateinit var batch: SpriteBatch
  lateinit var img: Texture
  lateinit var textures: Texture

  lateinit var stone: TextureRegion
  lateinit var ground: TextureRegion

  override fun create() {
    batch = SpriteBatch()
    img = Texture("badlogic.jpg")
    textures = Texture("Textures16.png")

    stone = TextureRegion(textures, 0, 0, 16, 16)
    ground = TextureRegion(textures, 0, 2 * 16, 16, 16)
  }

  override fun render() {
    Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()

    batch.draw(img, 0f, 0f)

    for(x in 0 ..10) {
      for(y in 0 .. 10) {
        batch.draw(stone, x * 16f, y * 16f)
      }
    }

    for(x in 11 ..20) {
      for(y in 11 .. 20) {
        batch.draw(ground, x * 16f, y * 16f)
      }
    }

    batch.end()
  }

  override fun dispose() {
    batch.dispose()
    img.dispose()
    textures.dispose()
  }
}