package com.example.yamamotoai.recipelist;

/**
 * Created by yamamotoai on 2017-07-22.
 */

public class Recipe {

    String recipeImage, recipeName, recipeDescription, url;
    Boolean isSelected;

    public Recipe(String recipeImage, String recipeName, String recipeDescription, String url) {
        this.recipeImage = recipeImage;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.url = url;
        isSelected = false;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Boolean getSelected() {
        return isSelected;
    }
}
