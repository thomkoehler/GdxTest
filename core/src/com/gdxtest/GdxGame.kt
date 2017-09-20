package com.gdxtest

import com.badlogic.gdx.Game

class GdxGame : Game() {
  override fun create() {
    setScreen(GameScreen())
  }
}