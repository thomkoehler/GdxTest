package com.gdxtest

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Input.Keys;

class GameScreen : Screen {
  var batch = SpriteBatch()
  var world: IWorld = World()
  val player = Player()

  init {
    val c = batch.getColor()
    batch.setColor(c.r, c.g, c.b, 1f)
  }

  override fun hide() {
  }

  override fun show() {
  }

  override fun render(delta: Float) {
    val deltaTime = Gdx.graphics.getDeltaTime()

    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      world.offsetX -= (75 * deltaTime).toInt();
    }

    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      world.offsetX += (75 * deltaTime).toInt();
    }

    Gdx.gl.glClearColor(.06f, .64f, .92f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    world.show(batch)

    if (Gdx.input.isKeyPressed(Keys.UP)) {
      player.jump();
    }

    player.calcNextPos(deltaTime)
    player.show(batch, 10, 10)
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