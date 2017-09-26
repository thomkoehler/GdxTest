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
  private val stoneBlock = TextureRegionBlock(TextureRegion(textures, 0, 0, BLOCK_WIDTH, BLOCK_HEIGHT))
  private val grassBlock = TextureRegionBlock(TextureRegion(textures, 3 * BLOCK_WIDTH, 0, BLOCK_WIDTH, BLOCK_HEIGHT))
  private val groundBlock = TextureRegionBlock(TextureRegion(textures, 2 * BLOCK_WIDTH, 0, BLOCK_WIDTH, BLOCK_HEIGHT))
  private val emptyBlock = EmptyBlock()

  private val world: Array<IBlock> = Array(WORLD_WIDTH * WORLD_HEIGHT, { _ -> emptyBlock })

  init {
    loadWorld()
  }

  override fun render(gameState: GameState, batch: SpriteBatch) {
    for (x in gameState.offsetX..(WORLD_HEIGHT - 1)) {
      for (y in gameState.offsetY..(WORLD_HEIGHT - 1)) {
        val block = world[x + y * WORLD_WIDTH]
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
    for (x in 0..(WORLD_WIDTH - 1)) {
      world[x + 0 * WORLD_WIDTH] = groundBlock
      world[x + 1 * WORLD_WIDTH] = if (x.rem(2) != 0) stoneBlock else groundBlock
      world[x + 2 * WORLD_WIDTH] = groundBlock
      world[x + 3 * WORLD_WIDTH] = grassBlock
    }
  }

  private fun loadWorldFile() {
    var x: Int = 0

    Gdx.files.internal(WORLD_FILE_NAME).reader().useLines { lines ->
      lines.forEach { line ->
        var y: Int = 0
        line.forEach { c ->
          when (c) {
            ' ' -> world[x + y * WORLD_WIDTH] = emptyBlock
            'G' -> world[x + y * WORLD_WIDTH] = groundBlock
            'g' -> world[x + y * WORLD_WIDTH] = grassBlock
            'S' -> world[x + y * WORLD_WIDTH] = stoneBlock
          }

          ++y
        }
        ++x
      }
    }
  }
}
