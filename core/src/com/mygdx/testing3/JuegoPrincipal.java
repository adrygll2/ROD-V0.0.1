package com.mygdx.testing3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JuegoPrincipal extends Game{

	private SpriteBatch batch;
	//private PantallaUI pantalla;
	private StartScreen startS;
	private MundoUnoTest tTest;

	
	@Override
	public void create() {
		
		//inicializar el spritebatch
		batch = new SpriteBatch();

		startS = new StartScreen(this);
		
		this.setScreen(startS);
		
	}


	@Override
	public void dispose() {
		super.dispose();
	}


	public SpriteBatch getBatch() {
		return batch;
	}


	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
	
	
	

}
