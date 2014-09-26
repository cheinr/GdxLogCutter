package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Assets;

public class Target {
	float x;
	float y;
	float width;
	float height;
	
	float gapSize = 0.25f;
	
	boolean targetSplit = false;
	
	Log log;
	
	TextureRegion targetLineRegion = new TextureRegion();
	Sprite targetLineSprite1; // TODO Might have to create an array if multiple cuts are made 
	Sprite targetLineSprite2;
	
	public Target(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		init();
	}
	
	private void init() {
		targetLineRegion.setRegion(Assets.instance.assetTarget.targetLine);
		targetLineSprite1 = new Sprite(targetLineRegion);
		targetLineSprite2 = new Sprite(targetLineRegion);
		
		targetLineSprite1.setBounds(x, y, width, height);
	}
	
	public void update(float fallSpeed, float deltaTime){
		
		y = y - fallSpeed * deltaTime;
		targetLineSprite1.setBounds(x, y, width, height);
		if(targetSplit) {
			targetLineSprite2.setBounds(x, y - gapSize, width, height);
		}
	}
	
	public void draw(SpriteBatch batch, OrthographicCamera camera){
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		targetLineSprite1.draw(batch);
		
		if(targetSplit) {
			targetLineSprite2.draw(batch);
		}
		
		batch.end();
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}

	public float getY() {
		return y;
	}

	public void split() {  //TODO needs work
		targetSplit = true;
		targetLineSprite1.setBounds(x, y, width, height);
		targetLineSprite2.setBounds(x, y - gapSize, width, height);
	}
	}
