package com.mygdx.testing3;
import com.badlogic.gdx.Screen;

public abstract class PantallaBase implements Screen{

	protected JuegoPrincipal juego;
	
	//metodo constructor
	public PantallaBase(JuegoPrincipal juego){
	this.juego=juego;	
	}
	
	//getjuego
	public JuegoPrincipal getJuego() {
		return juego;
	}


	public void setJuego(JuegoPrincipal juego) {
		this.juego = juego;
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
