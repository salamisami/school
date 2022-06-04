/**
 * Diese Datei ist Teil der Vorgabe zur Lehrveranstaltung Algorithmen und Datenstrukturen der Hochschule
 * für Angewandte Wissenschaften Hamburg von Prof. Philipp Jenke (Informatik)
 */

package praktikum.aufgabe3.vis;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import praktikum.aufgabe3.Constants;
import praktikum.aufgabe3.map.Character;

/**
 * Animated sprite rendering
 */
public class AnimatedSprite {

  /**
   * Sprite input image width
   */
  private int width;

  /**
   * Sprite input image height
   */
  private int height;

  /**
   * Current frame in the animation.
   */
  private int currentFrame;

  /**
   * Counts the number of draw calls, used to select the next frame.
   */
  private int renderCallCounter;

  /**
   * Inner structure to hold the information for an animation state.
   */
  private class StateImage {

    public int numFrames;
    public Image img;

    public StateImage(int numFrames, Image img) {
      this.numFrames = numFrames;
      this.img = img;
    }
  }

  /**
   * Contains the supported states and the image for each state.
   */
  private Map<Character.State, StateImage> stateImages;

  public AnimatedSprite(int width, int height) {
    this.width = width;
    this.height = height;
    this.renderCallCounter = 0;
    stateImages = new HashMap<>();
  }

  /**
   * Add an additional animation state and its image.
   */
  public void addAnimationState(Character.State state, String spriteFilename) {
    FileInputStream inputstream = null;
    try {
      inputstream = new FileInputStream(spriteFilename);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Image img = new Image(inputstream);
    int numFrames = (int) (img.getWidth()) / width;
    stateImages.put(state, new StateImage(numFrames, img));
    System.out.println(
      "Created sprite animation " + state + " with " + numFrames + " frames."
    );
  }

  /**
   * Draw the current frame of the animation.
   */
  public void draw(GraphicsContext gc, Point p, Character.State state) {
    if (!stateImages.containsKey(state)) {
      return;
    }
    StateImage stateImage = stateImages.get(state);
    renderCallCounter++;
    if (renderCallCounter % Constants.SPRITE_ANIMATION_SPEED == 0) {
      currentFrame = (currentFrame + 1) % stateImage.numFrames;
    }
    currentFrame = currentFrame % stateImage.numFrames;
    int renderWidth = (int) (Constants.CELL_RENDER_SIDELENGTH * 1.5);
    int rendeHeight = (int) (Constants.CELL_RENDER_SIDELENGTH * 1.5);
    gc.drawImage(
      stateImage.img,
      currentFrame * width,
      0,
      width,
      height,
      p.x - renderWidth / 2,
      p.y - rendeHeight / 2,
      renderWidth,
      rendeHeight
    );
  }
}
