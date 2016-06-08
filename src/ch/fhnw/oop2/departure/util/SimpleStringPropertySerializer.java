package ch.fhnw.oop2.departure.util;

import java.lang.reflect.Type;
import com.google.gson.*;
import javafx.beans.property.SimpleStringProperty;

public class SimpleStringPropertySerializer implements JsonSerializer<SimpleStringProperty> {
	 public JsonElement serialize(SimpleStringProperty src, Type typeOfSrc, JsonSerializationContext context) {
	    return new JsonPrimitive(src.get());
	  }
}
