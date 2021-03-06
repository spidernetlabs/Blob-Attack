package com.cnia.angelworks.ba.entity;

import it.randomtower.engine.ResourceManager;
import it.randomtower.engine.entity.Entity;
import it.randomtower.engine.entity.PhysicsEntity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * @author CNIAngel This is a simple example for a player entity class. It will
 *         show movement, calling on the sprite, setting the hitbox, and
 *         collision
 */
public class Player extends PhysicsEntity {

	private int moveSpeed = 4, jumpSpeed = 11; // SUPER JUMP!!!!
	private boolean isJumping, onGround, isFalling;
	private Image img, imgLeft; // an Image instance for the sprite
	private Sound jmpSound = ResourceManager.getSound("jmpSound");

	/**
	 * @param x
	 * @param y
	 */
	public Player(float x, float y) {
		super(x, y);

		img = ResourceManager.getImage("doc"); // Set the image to the desired
												// sprite
		imgLeft = ResourceManager.getImage("docLeft");
		setGraphic(img); // Then set the sprite for the Player entity

		// Define preset controls for the Player entity
		define("UP", Input.KEY_UP);
		// define("DOWN", Input.KEY_DOWN);
		define("LEFT", Input.KEY_LEFT);
		define("RIGHT", Input.KEY_RIGHT);

		gravity = 0.4f;

		/*
		 * Set the hitbox for the Player entity using the entity's x variable, y
		 * variable, the width of the entity's sprite, and the height of the
		 * entity's sprite
		 */
		setHitBox(0, 0, img.getWidth(), img.getHeight());

		// Set the collsion type for the entity
		addType(PLAYER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.randomtower.engine.entity.Entity#update(org.newdawn.slick.GameContainer
	 * , int)
	 */
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);

		Input input = gc.getInput();

		onGround = false;
		if (collide(SOLID, x, y + 1) != null) {
			onGround = true;
			isJumping = false;
		}

		if (check("LEFT")) {

			if (collide(SOLID, x - 2, y) != null) {
				setGraphic(imgLeft);
				x -= 0;
			} else {
				setGraphic(imgLeft);
				x -= moveSpeed;
			}

		}

		if (check("RIGHT")) {

			if (collide(SOLID, x + 3, y) != null) {
				setGraphic(img);
				x += 0;
			} else {
				setGraphic(img);
				x += moveSpeed;
			}

		}

		if (pressed("UP")) {
			boolean jumped = false;
			
			
			
			// normal jump
			if (onGround) {
				speed.y = -jumpSpeed;
				jmpSound.play();
				jumped = true;
				isJumping = true;
			}
			
			/**if (isJumping) {
				speed.y = -jumpSpeed;
				jmpSound.play();
				isFalling = true;
			} */
				
		}

		gravity(delta);

		if (speed.y < 0 && !check("UP")) {
			gravity(delta);
			gravity(delta);
		}
	}
}
