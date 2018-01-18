package pokemonai.data.pokedex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class Pokedex {

	private static HashMap<String, DexEntry> dexTable = null;

	static {
		if (dexTable == null) {
			JSONParser parser = new JSONParser();
			try {
				Object obj = parser.parse(new FileReader("resources/pokedex.json"));
				JSONObject pokedexObject = (JSONObject) obj;
				dexTable = new HashMap<>();
				for (Object key : pokedexObject.keySet()) {
					dexTable.put((String) key, new DexEntry((JSONObject) pokedexObject.get(key)));
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static DexEntry getDexEntry(String species) {
		species = species.toLowerCase().trim().replaceAll(" ", "");
		return dexTable.get(species);
	}
}
