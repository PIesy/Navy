package com.mycompany.app;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JsonBuilder {

    public JsonObject getJsonObject(String string)
    {
        JsonObject object;

        JsonReader reader = Json.createReader(new StringReader(string));
        object = reader.readObject();
        return object;
    }
}