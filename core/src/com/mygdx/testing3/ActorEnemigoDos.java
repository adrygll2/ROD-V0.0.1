package com.mygdx.testing3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorEnemigoDos extends Actor {

	private Texture link;
	private TextureRegion miLink;
	private int w;
	private int h;
	private int x = 0;
	private int y = 0;
	private Animation Aup, Adown, Aleft, Aright;
	private float elapsedTime = 0;
	private TextureRegion[] linkAnimationRight;
	private TextureRegion[] linkAnimationLeft;
	private TextureRegion[] linkAnimationUp;
	private TextureRegion[] linkAnimationDown;
	private float actualX;
	private float actualY;
	private float antiguaX;
	private float antiguaY;
	private Rectangle rectagulo;
	private Vector2 position;

	public ActorEnemigoDos() {
		// definimos el alto y el ancho de la imagen
		w = 95;
		h = 128;

		// definimos la textura y el textureRegion con la imagen y el alto y el
		// ancho
		link = new Texture("img/esqueletos.png");
		miLink = new TextureRegion(link, 0, 0, w, h);

		this.setBounds(0, 0, 100, 100);

		// matriz donde introducimos cada una de las imagenes del diablo
		TextureRegion[][] temp = miLink.split(w / 3, h / 4);

		// Introducimos en los arrays lo que nos retorna el metodo de los
		// sprites

		linkAnimationRight = getArrayTextureRegionTempDos(temp);

		linkAnimationLeft = getArrayTextureRegionTempTres(temp);

		linkAnimationUp = getArrayTextureRegionTempCuatro(temp);

		linkAnimationDown = getArrayTextureRegionTempCinco(temp);

		// Iniciamos una posicion en la pantalla
		Aright = new Animation(0.09f, linkAnimationRight);

		Aleft = new Animation(0.09f, linkAnimationLeft);

		Adown = new Animation(0.09f, linkAnimationDown);

		Aup = new Animation(0.09f, linkAnimationUp);

		rectagulo = new Rectangle(this.getX(), this.getY(), this.getWidth(),
				this.getHeight());

	}
	
	// metodo para conseguir la linea de imagenes de link a la derecha
	private TextureRegion[] getArrayTextureRegionTempDos(TextureRegion[][] temp) {
		TextureRegion[] tempDos = new TextureRegion[3];

		for (int i = 0; i < tempDos.length; i++) {
			tempDos[i] = temp[2][i];

		}

		return tempDos;

	}

	// metodo para conseguir la linea de imagenes de link arriba
	private TextureRegion[] getArrayTextureRegionTempCuatro(TextureRegion[][] temp) {
		TextureRegion[] tempTres = new TextureRegion[3];

		for (int i = 0; i < tempTres.length; i++) {
			tempTres[i] = temp[3][i];

		}

		return tempTres;

	}
	
	// metodo para conseguir la linea de imagenes de link hacia la izquierda
	private TextureRegion[] getArrayTextureRegionTempTres(TextureRegion[][] temp) {
		TextureRegion[] tempCuatro = new TextureRegion[3];

		for (int i = 0; i < tempCuatro.length; i++) {
			tempCuatro[i] = temp[1][i];

		}

		return tempCuatro;

	}

	// metodo para conseguir la linea de imagenes de link abajo
	private TextureRegion[] getArrayTextureRegionTempCinco(TextureRegion[][] temp) {
		TextureRegion[] tempCinco = new TextureRegion[3];
		
		
		for (int i = 0; i < tempCinco.length; i++) {
			tempCinco[i] = temp[0][i];

		}

		return tempCinco;

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);


		
		Color color= getColor();
		batch.setColor(color.r,color.g,color.b,color.a * parentAlpha);
		
		
		// Cuarto superior derecho
		if(actualY > 100 && actualX > 100 && actualX > actualY){
			
			batch.draw(Aright.getKeyFrame(elapsedTime, true), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());		

		} 
		if(actualY > 100 && actualX > 100 && actualX < actualY){
			
			batch.draw(Aup.getKeyFrame(elapsedTime, true), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());		

		} 
		
		// Cuarto inferior derecho
		if(actualY < 100 && actualX > 100 && actualX < actualY){
	     	
			batch.draw(Aright.getKeyFrame(elapsedTime, true), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

		}
		if(actualY < 100 && actualX > 100 && actualX > actualY){
	     	
			batch.draw(Adown.getKeyFrame(elapsedTime, true), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

		} 	
	
		//Cuarto superior derecho
		if(actualX < 100 && actualY > 100 && actualX < actualY){
	     	
			batch.draw(Aleft.getKeyFrame(elapsedTime, true), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

		}
		
		if(actualX < 100 && actualY > 100 && actualX > actualY){
	     	
			batch.draw(Aup.getKeyFrame(elapsedTime, true), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

		} 
		
		
		//Cuarto inferior izquierdo
		if(actualX < 100 && actualY < 100 && actualX < actualY){
			
			batch.draw(Aleft.getKeyFrame(elapsedTime, true), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

		}
		
		if(actualX < 100 && actualY < 100 && actualX > actualY){
			
			batch.draw(Adown.getKeyFrame(elapsedTime, true), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

		}
		
		// Estado parado
		if(actualX == 100 && actualY == 100) {
			
			batch.draw(Adown.getKeyFrame(elapsedTime, true), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());		

		}
		antiguaX = actualX;
		antiguaY = actualY;
		elapsedTime += Gdx.graphics.getDeltaTime();

		
	}

	public Texture getLink() {
		return link;
	}

	public void setLink(Texture link) {
		this.link = link;
	}

	public TextureRegion getMiLink() {
		return miLink;
	}

	public void setMiLink(TextureRegion miLink) {
		this.miLink = miLink;
	}

	public TextureRegion[] getLinkAnimationRight() {
		return linkAnimationRight;
	}

	public void setLinkAnimationRight(TextureRegion[] linkAnimationRight) {
		this.linkAnimationRight = linkAnimationRight;
	}

	public TextureRegion[] getLinkAnimationLeft() {
		return linkAnimationLeft;
	}

	public void setLinkAnimationLeft(TextureRegion[] linkAnimationLeft) {
		this.linkAnimationLeft = linkAnimationLeft;
	}

	public TextureRegion[] getLinkAnimationUp() {
		return linkAnimationUp;
	}

	public void setLinkAnimationUp(TextureRegion[] linkAnimationUp) {
		this.linkAnimationUp = linkAnimationUp;
	}

	public TextureRegion[] getLinkAnimationDown() {
		return linkAnimationDown;
	}

	public void setLinkAnimationDown(TextureRegion[] linkAnimationDown) {
		this.linkAnimationDown = linkAnimationDown;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public float getActualX() {
		return actualX;
	}

	public void setActualX(float actualX) {
		this.actualX = actualX;
	}

	public float getActualY() {
		return actualY;
	}

	public void setActualY(float actualY) {
		this.actualY = actualY;
	}

	public Rectangle getRectagulo() {
		return rectagulo;
	}

	public void setRectagulo(Rectangle rectagulo) {
		this.rectagulo = rectagulo;
	}

	public float getAntiguaX() {
		return antiguaX;
	}

	public void setAntiguaX(float antiguaX) {
		this.antiguaX = antiguaX;
	}

	public float getAntiguaY() {
		return antiguaY;
	}

	public void setAntiguaY(float antiguaY) {
		this.antiguaY = antiguaY;
	}



}
