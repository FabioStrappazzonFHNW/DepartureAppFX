package ch.fhnw.oop2.departure.util;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import javafx.beans.property.SimpleStringProperty;

public class SimpleStringPropertyDeserializer implements JsonDeserializer<SimpleStringProperty> {
  
	@Override
	public SimpleStringProperty deserialize(JsonElement json, Type srcType, JsonDeserializationContext context)
			throws JsonParseException {
		return new SimpleStringProperty(json.getAsJsonPrimitive().getAsString());
	}
	
}
