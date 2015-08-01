package com.mygdx.testing3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class MundoUnoTest extends PantallaBase implements InputProcessor {

	// Variables de actores
	private ActorLink link;
	private ActorEnemigo enemigo;
	private ActorEnemigoDos enemigoDos;
	private ActorEnemigoTres enemigoTres;
	private ActorEnemigoCuatro enemigoCuatro;
	
	//Variables de escenario, camara, touchpad, velocidad, alto, ancho
	private OrthographicCamera camera;
	private Stage escenario;
	private SpriteBatch batch;
	private Touchpad touchpad;
	private TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Drawable touchBackground;
	private Drawable touchKnob;
	private TiledMap tiledMap = new TmxMapLoader().load("img/tiledPrueba.tmx");
	private MapProperties prop = tiledMap.getProperties();
	private TiledMapRenderer tiledMapRenderer;
	private float actorSpeed;
	private ShapeRenderer sp;
	private int mapWidth = prop.get("width", Integer.class);
	private int mapHeight = prop.get("height", Integer.class);
	private int tilePixelWidth = prop.get("tilewidth", Integer.class);
	private int tilePixelHeight = prop.get("tileheight", Integer.class);
	private int mapPixelWidth = mapWidth * tilePixelWidth;
	private int mapPixelHeight = mapHeight * tilePixelHeight;
	private	int w = tilePixelWidth;
	private	int h = tilePixelHeight;
	private float width = Gdx.graphics.getWidth();
	private float height = Gdx.graphics.getHeight();
	private Music menuSong;
	private Sound loseSound;

	
	public MundoUnoTest(JuegoPrincipal juego) {
		super(juego);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		//Iniciamos los actores
		link = new ActorLink();
		enemigo = new ActorEnemigo();
		enemigoDos = new ActorEnemigoDos();
		enemigoTres = new ActorEnemigoTres();
		enemigoCuatro = new ActorEnemigoCuatro();
		
		//Iniciamos la musica
		menuSong = Gdx.audio.newMusic(Gdx.files.internal("music/gamemusic.mp3"));
		loseSound = Gdx.audio.newSound(Gdx.files.internal("music/youlose.wav"));
		//menuSong.setLooping(true);
		
		//Iniciamos el spriteBatch
		batch = new SpriteBatch();
		
		// Iniciamos la camara
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.update();

		// Se crea la skin del touchpad
		touchpadSkin = new Skin();
		// Le aplicamos un fondo al touchpad
		touchpadSkin.add("touchBackground", new Texture(
				"img/touchBackground.png"));
		// Le aplicamos la imagen que vamos a mover en el touchpad
		touchpadSkin.add("touchKnob", new Texture("img/touchKnob.png"));
		// Creamos el TouchPad Style
		touchpadStyle = new TouchpadStyle();
		// Cogemos de la skin del touchpad las imagenes
		touchBackground = touchpadSkin.getDrawable("touchBackground");
		touchKnob = touchpadSkin.getDrawable("touchKnob");
		// Le aplicamos al touchpad fondo y boton principal
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		// inicializamos el touchpad y le aplicamos un estilo
		touchpad = new Touchpad(10, touchpadStyle);
		// le indicamos el tamaño 
		touchpad.setBounds(15, 15, 200, 200);
		
		// creamos el escenario y aÃ±adimos el touchpad y los actores
		escenario = new Stage();
		escenario.addActor(enemigo);
		escenario.addActor(enemigoDos);
		escenario.addActor(enemigoTres);
		escenario.addActor(enemigoCuatro);
		escenario.addActor(link);
		escenario.addActor(touchpad);
		
		//Posicion del actor en la pantalla
		link.setPosition(Gdx.graphics.getWidth() / 2 - link.getWidth() /2 ,  Gdx.graphics.getHeight() / 2 - link.getHeight() / 2);
		
		enemigo.setPosition(Gdx.graphics.getWidth() / 4 - enemigo.getWidth() / 4 ,  Gdx.graphics.getHeight() / 4 - enemigo.getHeight() / 4);
		
		enemigoDos.setPosition(Gdx.graphics.getWidth() / 2 - enemigoDos.getWidth() / 4 ,  Gdx.graphics.getHeight() - enemigoDos.getHeight() / 4);

		enemigoTres.setPosition(Gdx.graphics.getWidth() / 5 - enemigoTres.getWidth() / 2 ,  Gdx.graphics.getHeight() - enemigoTres.getHeight() / 4);
		
		enemigoCuatro.setPosition(Gdx.graphics.getWidth() / 4 - enemigoCuatro.getWidth() / 6 ,  Gdx.graphics.getHeight() / 2 - enemigoCuatro.getHeight() / 4);

		//Velocidad del actor
		actorSpeed = 3;

		menuSong.play();

		
		//Cargamos el mapa
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		this.sp = new ShapeRenderer();
		this.sp.setProjectionMatrix(camera.combined);
		
		// aÃ±adimos la funcionalidad al touchpad
		Gdx.input.setInputProcessor(this);

		Gdx.input.setInputProcessor(escenario);
		
	}


	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		
		// comienzo del batch
		juego.getBatch().begin();
		
		//llamada al metodo de colisiones e IA
		colisionMapa();
		enemigosIAUno();
		enemigosIADos();
		enemigosIATres();
		enemigosIACuatro();

		Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Posiciono la camara en el centro junto al actor
		camera.position.set(link.getX() + mapPixelWidth / 2 ,link.getY()+ mapPixelHeight / 2 , 0);

		camera.update();
		
		
		// Movimiento personaje principal
		link.setX(link.getX() + touchpad.getKnobPercentX() * actorSpeed);		
		link.setY(link.getY() + touchpad.getKnobPercentY() * actorSpeed);
		
		// Movimiento enemigo
		if (link.getX() == link.getAntiguaX() && link.getY() == link.getAntiguaY()){
			enemigo.setX(enemigo.getX() + touchpad.getKnobPercentX() * actorSpeed +5 );
			enemigo.setY(enemigo.getY() + touchpad.getKnobPercentY() * actorSpeed +5 );
			enemigoDos.setX(enemigoDos.getX() + touchpad.getKnobPercentX() * actorSpeed);
			enemigoDos.setY(enemigoDos.getY() + touchpad.getKnobPercentY() * actorSpeed);
			enemigoTres.setX(enemigoDos.getX() + touchpad.getKnobPercentX() * actorSpeed);
			enemigoTres.setY(enemigoDos.getY() + touchpad.getKnobPercentY() * actorSpeed);
			enemigoCuatro.setX(enemigoCuatro.getX() + touchpad.getKnobPercentX() * actorSpeed);
			enemigoCuatro.setY(enemigoCuatro.getY() + touchpad.getKnobPercentY() * actorSpeed);
		}
		
		//Posicion del rectangulo de link
		link.getRectagulo().setPosition(link.getX(), link.getY());
		
		enemigo.getRectagulo().setPosition(enemigo.getX(), enemigo.getY());
		enemigoDos.getRectagulo().setPosition(enemigoDos.getX(), enemigoDos.getY());
		enemigoTres.getRectagulo().setPosition(enemigoTres.getX(), enemigoTres.getY());
		enemigoCuatro.getRectagulo().setPosition(enemigoCuatro.getX(), enemigoCuatro.getY());
		
		//seteo los campos de la posicion del touchpad	
		link.setActualX(touchpad.getKnobX());
		link.setActualY(touchpad.getKnobY());
		
		//seteo la posicion de los actores enemigos con la del touchpad
		enemigo.setActualX(touchpad.getKnobX());
		enemigo.setActualY(touchpad.getKnobY());
		enemigoDos.setActualX(touchpad.getKnobX());
		enemigoDos.setActualY(touchpad.getKnobY());
		enemigoTres.setActualX(touchpad.getKnobX());
		enemigoTres.setActualY(touchpad.getKnobY());
		enemigoCuatro.setActualX(touchpad.getKnobX());
		enemigoCuatro.setActualY(touchpad.getKnobY());
		
		//se renderiza el mapa 
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		
		// Pintamos los objetos
		escenario.act(delta);
		escenario.draw();
		
		// Llamada a los metodos de ver mini Mapa y el de muerte
		verMiniMapa();
		die();
		// Fin del batch
		juego.getBatch().end();
	}
	
	
	//Inteligencia artificial para el primer actor
	private void enemigosIAUno(){
		
		//Tipo de inteligencia artificial muy basica que siempre
		//se dirige al centro del personaje enemigo
		float enemigoX = enemigo.getX();
		float enemigoY = enemigo.getY();

		if(link.getX()>enemigo.getX()){
			enemigoX++;
			enemigo.setX(enemigoX);
			
		} else {
			enemigoX--;
			enemigo.setX(enemigoX);
		}
		if(link.getY()>enemigo.getY()){
			enemigoY++;
			enemigo.setY(enemigoY);
		} else {
			enemigoY--;
			enemigo.setY(enemigoY);
		}
	}

	//Inteligencia artificial para el segundo actor
	private void enemigosIADos() {

		float enemigoX = enemigoDos.getX();
		float enemigoY = enemigoDos.getY();

		if (link.getX() > enemigoDos.getX()) {
			enemigoX++;
			enemigoDos.setX(enemigoX);

		} else {
			enemigoX--;
			enemigoDos.setX(enemigoX);
		}
		if (link.getY() > enemigoDos.getY()) {
			enemigoY++;
			enemigoDos.setY(enemigoY);
		} else {
			enemigoY--;
			enemigoDos.setY(enemigoY);
		}
	}
	
	//Inteligencia artificial para el tercer actor
	private void enemigosIATres() {

		float enemigoX = enemigoTres.getX();
		float enemigoY = enemigoTres.getY();

		if (link.getX() > enemigoTres.getX()) {
			enemigoX++;
			enemigoTres.setX(enemigoX);

		} else {
			enemigoX--;
			enemigoTres.setX(enemigoX);
		}
		if (link.getY() > enemigoTres.getY()) {
			enemigoY++;
			enemigoTres.setY(enemigoY);
		} else {
			enemigoY--;
			enemigoTres.setY(enemigoY);
		}
	}
	
	//Inteligencia artificial para el cuarto actor
	private void enemigosIACuatro() {

		float enemigoX = enemigoCuatro.getX();
		float enemigoY = enemigoCuatro.getY();

		if (link.getX() > enemigoCuatro.getX()) {
			enemigoX++;
			enemigoCuatro.setX(enemigoX);

		} else {
			enemigoX--;
			enemigoCuatro.setX(enemigoX);
		}
		if (link.getY() > enemigoCuatro.getY()) {
			enemigoY++;
			enemigoCuatro.setY(enemigoY);
		} else {
			enemigoY--;
			enemigoCuatro.setY(enemigoY);
		}
	}

	private void colisionMapa(){
		
		//En primer lugar mira si la posición x y la posicion y del personaje
		//es menor al ancho y el alto de la pantalla de ser asi se le setea la posicion anterior
		//luego la segunda condición recorta la pantalla y setea al eje x y al eje y del personaje
		//la posicion que se quiere
		if(link.getX()< mapHeight){ 	
			link.setX(link.getAntiguaX());
			
		}else if(link.getX()>mapHeight*mapWidth-1870){
			link.setX(mapWidth*mapHeight-1870);
		}
		if(link.getY()< mapWidth){ 	
			link.setY(link.getAntiguaY());
			
		}else if(link.getY()>mapHeight*mapWidth-1970){
			link.setY(mapWidth*mapHeight-1970);
		}
			
	}
	
	//Metodo que dibuja en el mapa los rectangulos de los actores
	//Su uso principal es como debugger pero aqui lo utilizo commo mini mapa
	private void verMiniMapa(){
		sp.begin(ShapeRenderer.ShapeType.Filled);
		sp.setColor(Color.BLACK);
		
		sp.rect(link.getRectagulo().x / 10, link.getRectagulo().y / 10, link.getRectagulo().width / 10, link.getRectagulo().height / 10);
		sp.rect(enemigo.getRectagulo().x / 10, enemigo.getRectagulo().y / 10, enemigo.getRectagulo().width / 10, enemigo.getRectagulo().height / 10);
		sp.rect(enemigoDos.getRectagulo().x / 10, enemigoDos.getRectagulo().y / 10, enemigoDos.getRectagulo().width / 10, enemigoDos.getRectagulo().height / 10);
		sp.rect(enemigoTres.getRectagulo().x / 10, enemigoTres.getRectagulo().y / 10, enemigoTres.getRectagulo().width / 10, enemigoTres.getRectagulo().height / 10);
		sp.rect(enemigoCuatro.getRectagulo().x / 10, enemigoCuatro.getRectagulo().y / 10, enemigoCuatro.getRectagulo().width / 10, enemigoCuatro.getRectagulo().height / 10);
		
		sp.end();
		
	}

	// Metodo de muerte en caso de que alguno de los rectangulos enemigos
	// toque el rectangulo del actor principal nos devuelve a la pantalla de inicio
	private void die(){
		
		if (link.getRectagulo().overlaps(enemigo.getRectagulo())) {
			// Choca con los rectangulos de la pantalla

			loseSound.play();
			getJuego().setScreen(new PantallaInterfaz(getJuego()));
			menuSong.stop();
			
		}
		
		if(link.getRectagulo().overlaps(enemigoDos.getRectagulo())){

			loseSound.play();
			getJuego().setScreen(new PantallaInterfaz(getJuego()));
			menuSong.stop();
		}

		if(link.getRectagulo().overlaps(enemigoTres.getRectagulo())){

			loseSound.play();
			getJuego().setScreen(new PantallaInterfaz(getJuego()));
			menuSong.stop();
		}
		
		if(link.getRectagulo().overlaps(enemigoCuatro.getRectagulo())){

			loseSound.play();
			getJuego().setScreen(new PantallaInterfaz(getJuego()));
			menuSong.stop();
		}
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public Music getMenuSong() {
		return menuSong;
	}

	public void setMenuSong(Music menuSong) {
		this.menuSong = menuSong;
	}

}
