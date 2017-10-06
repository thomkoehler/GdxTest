package com.gdxtest

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion

enum class Block {
  EMPTY,
  STONE,
  GROUND,
  GRASS
}

class World : IWorld {
  private val textures = Texture("Textures16.png")
  private val stoneBlock = TextureRegionBlock(TextureRegion(textures, 0, 0, BLOCK_WIDTH.toInt(), BLOCK_HEIGHT.toInt()))
  private val grassBlock = TextureRegionBlock(TextureRegion(textures, 3 * BLOCK_WIDTH.toInt(), 0, BLOCK_WIDTH.toInt(), BLOCK_HEIGHT.toInt()))
  private val groundBlock = TextureRegionBlock(TextureRegion(textures, 2 * BLOCK_WIDTH.toInt(), 0, BLOCK_WIDTH.toInt(), BLOCK_HEIGHT.toInt()))
  private val emptyBlock = EmptyBlock()

  private val world: Array<IBlock> = Array((WORLD_WIDTH * WORLD_HEIGHT).toInt(), { _ -> emptyBlock })

  init {
    loadWorld()
  }

  override fun render(gameState: GameState, batch: SpriteBatch) {
    for (x in gameState.offsetX.toInt()..(WORLD_HEIGHT - 1f).toInt()) {
      for (y in gameState.offsetY.toInt()..(WORLD_HEIGHT - 1f).toInt()) {
        val block = world[x + y * WORLD_WIDTH.toInt()]
        block.show(batch, x - gameState.offsetX, y - gameState.offsetY)
      }
    }
  }

  fun dispose() {
    textures.dispose()
  }

  private fun loadWorld() {
    loadDefaultWorld()
    loadWorldFile()
  }

  private fun loadDefaultWorld() {
    for (x in 0..(WORLD_WIDTH.toInt() - 1)) {
      world[x + 0 * WORLD_WIDTH.toInt()] = groundBlock
      world[x + 1 * WORLD_WIDTH.toInt()] = if (x.rem(2) != 0) stoneBlock else groundBlock
      world[x + 2 * WORLD_WIDTH.toInt()] = groundBlock
      world[x + 3 * WORLD_WIDTH.toInt()] = grassBlock
    }
  }

  private fun loadWorldFile() {
    var x: Int = 0

    Gdx.files.internal(WORLD_FILE_NAME).reader().useLines { lines ->
      lines.forEach { line ->
        var y: Int = 0
        line.forEach { c ->
          when (c) {
            ' ' -> world[x + y * WORLD_WIDTH.toInt()] = emptyBlock
            'G' -> world[x + y * WORLD_WIDTH.toInt()] = groundBlock
            'g' -> world[x + y * WORLD_WIDTH.toInt()] = grassBlock
            'S' -> world[x + y * WORLD_WIDTH.toInt()] = stoneBlock
          }

          ++y
        }
        ++x
      }
    }
  }
}
