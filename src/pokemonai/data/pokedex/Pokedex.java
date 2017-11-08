package pokemonai.data.pokedex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class Pokedex {
	
	public Pokedex() {
		
	}
	
	public void readPokedex() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("resources/pokedex.json"));
			JSONObject jsonObject = (JSONObject) obj;
			System.out.println(jsonObject.get("bulbasaur"));
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
