package com.gdxtest

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
  private val stone = TextureRegion(textures, 0, 0, BLOCK_WIDTH, BLOCK_HEIGHT)
  private val grass = TextureRegion(textures, 3 * BLOCK_WIDTH, 0, BLOCK_WIDTH, BLOCK_HEIGHT)
  private val ground = TextureRegion(textures, 2 * BLOCK_WIDTH, 0, BLOCK_WIDTH, BLOCK_HEIGHT)

  private val width: Int
  private val height: Int
  private val offsetX: Int
  private val offsetY: Int
  private val world: Array<Block>

  init {
    width = 800
    height = 600
    offsetX = 0
    offsetY = 0
    world = Array(width * height, { _ -> Block.EMPTY })

    loadWorld()
  }

  override fun show(batch: SpriteBatch) {
    for (x in offsetX..(width - 1)) {
      for (y in offsetY..(height - 1)) {
        val block = world[x + y * width]
        renderBlock(block, batch, x, y)
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
      world[x + 0 * width] = Block.GROUND
      world[x + 1 * width] = if (x.rem(2) != 0) Block.STONE else Block.GROUND
      world[x + 2 * width] = Block.GROUND
      world[x + 3 * width] = Block.GRASS
    }
  }

  private fun loadWorldFile() {
    var x: Int = 0

    File(WORLD_FILE_NAME).forEachLine { line ->
      var y: Int = 0
      line.forEach { c ->
        when(c) {
          ' ' -> world[x + y * width] = Block.EMPTY
          'G' -> world[x + y * width] = Block.GROUND
          'g' -> world[x + y * width] = Block.GRASS
          'S' -> world[x + y * width] = Block.STONE
        }

        ++y
      }
      ++x
    }
  }

  private fun renderBlock(block: Block, batch: SpriteBatch, x: Int, y: Int) {
    when (block) {
      Block.STONE -> batch.draw(stone, x * 16f, y * 16f)
      Block.GROUND -> batch.draw(ground, x * 16f, y * 16f)
      Block.GRASS -> batch.draw(grass, x * 16f, y * 16f)
    }
  }
}
