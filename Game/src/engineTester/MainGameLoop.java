package engineTester;

import entities.Light;
import entities.Player;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.*;
import terrains.Terrain;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGameLoop {

	//TODO :
	//	-Het terrain meer low poly maken
	//	-Automatisch de height map laten genereren

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		MasterRenderer renderer = new MasterRenderer();

		Terrain terrain = new Terrain(0, -1, loader, "HeightMap");

		TexturedModel treeModel = new TexturedModel(ObjLoader.loadObjModel("tree/Tree", loader),
				new ModelTexture(loader.loadTexture("tree/TreeTexture")));

		TexturedModel grassModel = new TexturedModel(ObjLoader.loadObjModel("grass/GrassModel", loader),
				new ModelTexture(loader.loadTexture("grass/GrassTexture")));
		grassModel.getTexture().setHasTransparency(true);
		grassModel.getTexture().setUseFakeLighting(true);

		Random random = new Random();
		List<Entity> trees = new ArrayList<>();
		List<Entity> grassList = new ArrayList<>();
		for (int i = 0; i < 500; i++) {
			float x = random.nextFloat() * 800;
			float z = random.nextFloat() * -600;
			float y = terrain.getHeightOfTerrain(x, z);
			trees.add(new Entity(treeModel, new Vector3f(x, y, z), 0, random.nextInt(360), 0, 0.2f));
		}
		for (int i = 0; i < 500; i++) {
			float x = random.nextFloat() * 800;
			float z = random.nextFloat() * -600;
			float y = terrain.getHeightOfTerrain(x, z);
			grassList.add(new Entity(grassModel, new Vector3f(x, y, z), 0, random.nextInt(360), 0, 0.2f));
		}

		Light light = new Light(new Vector3f(3000, 2000, 2000), new Vector3f(1, 1, 1));

		TexturedModel foxModel = new TexturedModel(ObjLoader.loadObjModel("fox/Fox", loader),
				new ModelTexture(loader.loadTexture("fox/FoxTexture")));
		Player player = new Player(foxModel, new Vector3f(100, 0, -50), 0, 0, 0, 0.4f);

		Camera camera = new Camera(player);


		while(!Display.isCloseRequested()) {
			camera.move();
			player.move(terrain);

			renderer.processEntity(player);

			for (Entity tree : trees) {
				renderer.processEntity(tree);
			}

			for (Entity grass : grassList) {
				renderer.processEntity(grass);
			}

			renderer.processTerrain(terrain);
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
