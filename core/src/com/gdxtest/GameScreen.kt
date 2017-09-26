package com.gdxtest

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Input.Keys;

class GameScreen : Screen {
  var gameState = GameState()
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
    Gdx.gl.glClearColor(.06f, .64f, .92f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    val input = createInput()
    gameState.screenHeight = Gdx.graphics.height.toFloat()
    gameState.screenWidth = Gdx.graphics.width.toFloat()

    player.update(input, gameState)

    batch.begin()
    world.render(gameState, batch)
    player.render(gameState, batch)
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

  private fun createInput(): Input {
    return Input(
        Gdx.graphics.getDeltaTime(),
        Gdx.input.isKeyPressed(Keys.LEFT),
        Gdx.input.isKeyPressed(Keys.RIGHT),
        Gdx.input.isKeyPressed(Keys.UP),
        Gdx.input.isKeyPressed(Keys.DOWN))
  }
}