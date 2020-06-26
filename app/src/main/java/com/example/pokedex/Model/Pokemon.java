package com.example.pokedex.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("image")
    String image;
    @SerializedName("description")
    String description;
    @SerializedName("height")
    @Expose
    float height;
    @SerializedName("weight")
    @Expose
    float weight;
    @SerializedName("species")
    @Expose
    String species;
    @SerializedName("types")
    List<String> typespokemon = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTypespokemon() {
        return typespokemon;
    }

    public void setTypespokemon(List<String> typespokemon) {
        this.typespokemon = typespokemon;
    }

    public String types() {
        String tiposPokeom = new String();
        for (String pokemontypes : typespokemon) {
            String virgula = " ";
            tiposPokeom += pokemontypes.concat(virgula);
        }
        return tiposPokeom;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", species='" + species + '\'' +
                ", typespokemon=" + types() +
                '}';
    }
}
