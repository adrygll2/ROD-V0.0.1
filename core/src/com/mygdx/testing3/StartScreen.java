package com.mygdx.testing3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StartScreen extends PantallaBase{
	
	private Texture backgroundTexture;
	private Sprite backgroundSprite;
	private Stage escenario;
	private SpriteBatch spriteBatch;
	private Thread Th;
	private	OrthographicCamera camera;
	private Viewport viewport;


	public StartScreen(JuegoPrincipal juego) {
		super(juego);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(800,480);
	    viewport = new FitViewport(800,480,camera);
	    viewport.apply();
		camera.setToOrtho(false,800,480);
	    camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		escenario = new Stage();

		
		backgroundTexture = new Texture(Gdx.files.internal("img/starticon.gif"));
		backgroundSprite = new Sprite(backgroundTexture,0,0,1280,768);
		
		Gdx.input.setInputProcessor(escenario);

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		
		camera.update();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		juego.getBatch().setProjectionMatrix(camera.combined);

		juego.getBatch().begin();
		//juego.getBatch().draw(backgroundSprite,800, 0);
		//backgroundSprite.draw(juego.getBatch());
		juego.getBatch().draw(backgroundSprite, 800 - 1050, 0);
		juego.getBatch().draw(backgroundSprite,800, 0);

		
		Gdx.app.postRunnable(new Runnable() {
	        @SuppressWarnings("static-access")
			@Override
	        public void run() {
	        	try {
					Th.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				getJuego().setScreen(new PantallaInterfaz(getJuego()));
	        }
	     });
	

		
		juego.getBatch().end();
		escenario.draw();
		escenario.act();
		
		
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		escenario.getViewport().update(width, height);
	}
	
	

}
