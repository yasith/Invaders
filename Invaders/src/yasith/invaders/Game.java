package yasith.invaders;

import java.util.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Starts the invaders game, and handles updating the game loop, and display.
 */
public class Game implements ApplicationListener {
	
	GameState gameState;
	
	private float mDelta; // The time since the last frame was drawn
	private SpriteBatch mBatch; // Use the sprite batch to draw the Sprites.

	private Hud mHud;
	private Ship mShip;
	private ArrayList<Invader> mInvaders; // Holds the invaders
	
	@Override
	public void create() {
		
		mShip = new Ship();
		mShip.setPosition( Gdx.graphics.getWidth() * 0.5f, 50.0f);
	
		// Adding invaders to the list of invaders
		mInvaders = new ArrayList<Invader>();
		
		float startX = GameConstants.START_X;
		float startY = Gdx.graphics.getHeight() - GameConstants.START_Y;
		
		float offsetX = GameConstants.OFFSET_X;
		float offsetY = GameConstants.OFFSET_Y;
		
		for(int i = 0; i < GameConstants.INVADER_ROWS; ++i){
			for(int j = 0; j < GameConstants.INVADER_COLS; ++j){
				Invader invader = new Invader(startX + (j * offsetX),
						startY - (i * offsetY), i/2);
				
				mInvaders.add(invader);
			}
		}
		// End of adding invaders
		
		mHud = new Hud();
		mBatch = new SpriteBatch();
		
		
		gameState = GameState.getInstance();
		gameState.setLives(GameConstants.PLAYER_LIVES);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		this.mDelta = Gdx.graphics.getDeltaTime();
		this.updatePositions();
	
		if(mInvaders.size() == 0){
			// TODO: Add logic for end game
		}
		
		// Clear the screen to black
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		mBatch.begin();
			mShip.render(mBatch);
			
			// Draw the remaining invaders
			for(Invader invader: mInvaders) invader.render(mBatch);
			
			mHud.render(mBatch);
		mBatch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	/** 
	 * 	Updates position of the ship
	 * 	
	 * 	On Android, uses the accelerometer to detect movement,
	 * 	on PC uses arrow keys.
	 */
	private void updatePositions(){
			
		// TODO: Remove during refactoring
	}
}