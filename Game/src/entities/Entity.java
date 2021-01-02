package entities;

import collisions.AABB;
import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

import java.util.Arrays;
import java.util.List;

public class Entity {

	protected TexturedModel model;
	protected Vector3f position;
	protected float rotX, rotY, rotZ;
	protected float scale;

	private List<AABB> collisionBoxes;

	private int textureIndex = 0;

	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ,
			float scale, AABB... collisionBoxes) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.collisionBoxes = Arrays.asList(collisionBoxes);
	}

	public Entity(TexturedModel model, int textureIndex, Vector3f position, float rotX, float rotY, float rotZ,
				  float scale, AABB... collisionBoxes) {
		this.textureIndex = textureIndex;
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.collisionBoxes = Arrays.asList(collisionBoxes);
	}

	public float getTextureXOffset() {
		int column = this.textureIndex % this.model.getTexture().getNumberOfRows();
		return (float) column / (float) this.model.getTexture().getNumberOfRows();
	}

	public float getTextureYOffset() {
		int row = this.textureIndex / this.model.getTexture().getNumberOfRows();
		return (float) row / (float) this.model.getTexture().getNumberOfRows();
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public List<AABB> getCollisionBoxes() {
		return collisionBoxes;
	}

	public boolean hasCollisions() {
		return !this.collisionBoxes.isEmpty();
	}

}
