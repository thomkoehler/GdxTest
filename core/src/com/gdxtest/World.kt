package com.gdxtest

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import java.io.File

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

  private val width: Int = 800
  private val height: Int = 600
  override var offsetX: Int = 0
    set(value) {
      field = if (value < 0) 0 else value
    }

  override var offsetY: Int = 0
    set(value) {
      field = if (value < 0) 0 else value
    }

  private val world: Array<IBlock> = Array(width * height, { _ -> emptyBlock })

  init {
    loadWorld()
  }

  override fun show(batch: SpriteBatch) {
    for (x in offsetX..(width - 1)) {
      for (y in offsetY..(height - 1)) {
        val block = world[x + y * width]
        block.show(batch, x - offsetX, y - offsetY)
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
    for (x in offsetX..(width - 1)) {
      world[x + 0 * width] = groundBlock
      world[x + 1 * width] = if (x.rem(2) != 0) stoneBlock else groundBlock
      world[x + 2 * width] = groundBlock
      world[x + 3 * width] = grassBlock
    }
  }

  private fun loadWorldFile() {
    var x: Int = 0

    Gdx.files.internal(WORLD_FILE_NAME).reader().useLines { lines ->
      lines.forEach { line ->
        var y: Int = 0
        line.forEach { c ->
          when (c) {
            ' ' -> world[x + y * width] = emptyBlock
            'G' -> world[x + y * width] = groundBlock
            'g' -> world[x + y * width] = grassBlock
            'S' -> world[x + y * width] = stoneBlock
          }

          ++y
        }
        ++x
      }
    }
  }
}
