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
  lateinit var world: IWorld

  override fun create() {
    batch = SpriteBatch()
    world = World()
  }

  override fun render() {
    Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    world.show(batch)
    batch.end()
  }

  override fun dispose() {
    batch.dispose()
  }
}