package musicgo.models.persistence;

import com.google.gson.*;
import musicgo.models.entities.Audio;
import musicgo.models.entities.Cancion;
import musicgo.models.entities.Podcast;

import java.lang.reflect.Type;

/**
 * Deserializador personalizado para la clase abstracta Audio.
 * Gson no puede instanciar clases abstractas por sí solo, así que
 * esta clase decide, según los campos presentes en el JSON, si debe
 * reconstruir el objeto como Cancion o como Podcast.
 */
public class AudioDeserializer implements JsonDeserializer<Audio> {

    @Override
    public Audio deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.has("artista") && jsonObject.has("album")) {
            return context.deserialize(jsonObject, Cancion.class);
        } else if (jsonObject.has("anfitrion") && jsonObject.has("descripcion")) {
            return context.deserialize(jsonObject, Podcast.class);
        }

        throw new JsonParseException("No se pudo determinar el tipo de Audio: " + jsonObject);
    }
}