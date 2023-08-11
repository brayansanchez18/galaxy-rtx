package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import math.Vector2D;
import states.GameState;

/**
 * Propiedades del laser como posicion, velocidad, velocidad max, angulo, textura
 */

public class Laser extends MovingObject {

  public Laser(Vector2D position, Vector2D velocity, double maxVel, double angle, BufferedImage texture,
      GameState gameState) {
    super(position, velocity, maxVel, texture, gameState);
    this.angle = angle;
    this.velocity = velocity.scale(maxVel);
  }
/**
 * Actualizar la pantalla en una colision
 */
  @Override
  public void update(float dt) {
    position = position.add(velocity);
    if (position.getX() < 0 || position.getX() > Constants.WIDTH ||
        position.getY() < 0 || position.getY() > Constants.HEIGHT) {
      Destroy();
    }

    collidesWith();

  }

  /**
   * Actualizar cada disparo, independientemente de la nave
   */
  @Override
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    at = AffineTransform.getTranslateInstance(position.getX() - width / 2, position.getY());
    at.rotate(angle, width / 2, 0);
    g2d.drawImage(texture, at, null);

  }

  /**
   * Respawn de cualquier objeto en el centro de la pantalla, en este caso la nave
   */
  @Override
  public Vector2D getCenter() {
    return new Vector2D(position.getX() + width / 2, position.getY() + width / 2);
  }

}