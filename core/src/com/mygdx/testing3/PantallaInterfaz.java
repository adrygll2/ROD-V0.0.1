package com.mygdx.testing3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PantallaInterfaz extends PantallaBase{

	private Skin skin;
	private Stage escenario;
	private TextButton botonEmpezar, botonSeleccion;
	private Table contenedor;
	private List lista;
	private Texture backgroundTextureTitle;
	private Sprite backgroundSpriteTitle;
	private Texture backgroundTexture;
	private Sprite backgroundSprite;
	private Music menuSong;
	private	float currentBgX;
	private	long lastTimeBg;
	private	OrthographicCamera camera;
	private Viewport viewport;
	private Button twitterboton;
	private Button facebookboton;
	private Button musicaboton;
	private Table contendorShare;
	private Table contendorMusica;
	private int processStatus = 0;


	
	public PantallaInterfaz(JuegoPrincipal juego){
		super(juego);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(width, height);
	    viewport = new FitViewport(800,480,camera);
	    viewport.apply();
		camera.setToOrtho(false,800,480);
	    camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	    
		currentBgX = 800;
		lastTimeBg = TimeUtils.nanoTime();

		menuSong = Gdx.audio.newMusic(Gdx.files.internal("music/menumusic.mp3"));
		menuSong.setLooping(true);
		
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		escenario = new Stage();
		botonEmpezar = new TextButton("Jugar", skin);
		twitterboton = new Button(skin, "twitter");
		facebookboton = new Button(skin, "facebook");
		musicaboton = new Button(skin, "musica");
		
		
		contenedor = new Table();
		contenedor.row();
		contenedor.add(botonEmpezar).width(550).height(150).row();
		contenedor.setFillParent(true);
	
		contendorShare = new Table();
		contendorShare.add(twitterboton);
		contendorShare.add(facebookboton);
		contendorShare.right().bottom();
		contendorShare.setFillParent(true);
		
		contendorMusica = new Table();
		contendorMusica.add(musicaboton);
		contendorMusica.left().bottom();
		contendorMusica.setFillParent(true);
		
		escenario.addActor(contendorMusica);
		escenario.addActor(contenedor);
		escenario.addActor(contendorShare);

		
		
		
		twitterboton.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
			     Gdx.net.openURI("https://twitter.com/runordie2015");

				return true;
			}
			
		});
		
		facebookboton.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
			     Gdx.net.openURI("https://www.facebook.com/pages/Run-or-die/839610536092389?ref=aymt_homepage_panel");

				return true;
			}
			
		});
		
		musicaboton.addListener(new InputListener(){
			@Override
			
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
					menuSong.setVolume(0);	
				
			
				return true;
			}
			
		});

		botonEmpezar.addListener(new InputListener(){
			
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button){
						
				getJuego().setScreen(new MundoUnoTest(getJuego()));
				menuSong.stop();
				
				
				return true;
				
			}			
			
		});
		
		
		backgroundTexture = new Texture(Gdx.files.internal("img/back.png"));
		backgroundSprite = new Sprite(backgroundTexture,0,0,1280,768);
		backgroundTextureTitle = new Texture(Gdx.files.internal("img/title.png"));
		
		//Resolucion de pantalla mayor a 1280x768
		if(width > 768 && height > 1280){
			backgroundSpriteTitle = new Sprite(backgroundTextureTitle,180,50,1280,768);
			backgroundSpriteTitle.setBounds(100, 0, 880, 468);
			
		} else { //Resolucion de pantalla menor o igual a 1280x768
			backgroundSpriteTitle = new Sprite(backgroundTextureTitle,170,20,1280,768);
			backgroundSpriteTitle.setBounds(100, 0, 880, 468);
			
		}
		
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


		
		juego.getBatch().draw(backgroundSprite, currentBgX - 800, 0);
		juego.getBatch().draw(backgroundSprite,currentBgX, 0);
		backgroundSpriteTitle.draw(juego.getBatch());

		//Accion de conseguir bucle de fondo
		if(TimeUtils.nanoTime() - lastTimeBg > 150000000){

			currentBgX -= 50;
			lastTimeBg = TimeUtils.nanoTime();
		}

		if(currentBgX == 0){
			currentBgX = 800;
		}
		

		juego.getBatch().end();

		
		menuSong.play();

		escenario.draw();
		escenario.act();

		

		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		escenario.getViewport().update(width, height);
	}
	
	

}

