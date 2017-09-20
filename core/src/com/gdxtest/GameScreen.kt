package com.gdxtest

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class GameScreen : Screen {
  lateinit var batch: SpriteBatch
  lateinit var world: IWorld

  init {
    batch = SpriteBatch()
    world = World()
  }

  override fun hide() {
  }

  override fun show() {
  }

  override fun render(delta: Float) {
    Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    world.show(batch)
    batch.end()
  }

  override fun pause() {
  }

  override fun resume() {
  }

  override fun resize(width: Int, height: Int) {
  }

  override fun dispose() {
    batch.dispose()
  }
}