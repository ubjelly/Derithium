package org.hyperion.rs2.model.object;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.hyperion.rs2.model.World;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Loads objects from a JSON file and adds/removes them in the game world.
 * @author Stephen Andrews
 */
public class ObjectManager {

	/**
	 * A list of objects in the world.
	 */
	private GameObject[] objects;
	
	/**
	 * The location of the spawns file.
	 */
	private final static String OBJECT_FILE = "./data/world/objects/objects.json";
	
	/**
	 * Loads the custom objects.
	 */
	public void loadObjects() {
		try (BufferedReader reader = new BufferedReader(new FileReader(OBJECT_FILE))) {
            Gson gson = new GsonBuilder().create();
            objects = gson.fromJson(reader, GameObject[].class);
            for (GameObject object : objects) {
            	World.getWorld().getRegionManager().getRegionByLocation(object.getLocation()).addGameObject(object);
            }
            System.out.println("Loaded " + objects.length + " game objects...");
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the game objects.
	 * @return The game objects.
	 */
	public GameObject[] getObjects() {
		return objects;
	}
}