package com.ktar5.utilities.libgdx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.ktar5.utilities.common.util.FileScanner;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class AnimationLoader {
    private HashMap<String, Animation<TextureRegion>> animations;
    private HashMap<String, Texture> textures;

    public AnimationLoader() {
        animations = new HashMap<>();
        textures = new HashMap<>();
        try {
            searchAtlases(new File("../assets"));
        } catch (Exception e) {
            try {
                searchAtlases(new File("./assets"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public void searchAtlases(File root) {
        new FileScanner(root, (file) -> {
            if(file.getName().endsWith(".atlas") && !file.getPath().contains("maps")){
                loadAtlas(file.getPath());
                return true;
            }
            return false;
        });
    }

    public Animation<TextureRegion> getAnimation(String animation) {
        if (animation.contains(animation)) {
            return animations.get(animation);
        } else {
            throw new NullPointerException();
        }
    }

    public void loadAtlas(String atlasPath) {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasPath));
        Array<TextureAtlas.AtlasRegion> regions = atlas.getRegions();
        HashMap<String, List<TextureAtlas.AtlasRegion>> atlasAnimations = new HashMap<>();
        for (TextureAtlas.AtlasRegion region : regions) {
            if (atlasAnimations.containsKey(region.name)) {
                atlasAnimations.get(region.name).add(region);
            } else {
                //noinspection ArraysAsListWithZeroOrOneArgument
                List<TextureAtlas.AtlasRegion> newList = new ArrayList<>();
                newList.add(region);
                atlasAnimations.put(region.name, newList);
            }
        }
        atlasAnimations.forEach((key, value) -> {
            int largestIndex = value.size() - 1;
            value.sort(Comparator.comparingInt(o -> o.index));
            for (TextureAtlas.AtlasRegion region : value) {
                if (region.index > largestIndex || region.index < 0) {
                    throw new IllegalArgumentException("Check indexes for animation " + key + " on atlas " + atlasPath);
                }
            }
            //TODO frame duration
            animations.put(key, new Animation<>(1/15f, value.toArray(new TextureAtlas.AtlasRegion[value.size()])));
        });
    }

    public Animation<TextureRegion> getTextureAsAnimation(String filename) {
        if (animations.containsKey(filename)) {
            return animations.get(filename);
        } else {
            Texture texture;
            if (textures.containsKey(filename)) {
                texture = textures.get(filename);
            } else {
                texture = new Texture(Gdx.files.internal(filename));
                textures.put(filename, texture);
            }
            animations.put(filename, new Animation<>(1, new TextureRegion(texture)));
            return animations.get(filename);
        }
    }

    public Texture getTexture(String filename) {
        if (textures.containsKey(filename)) {
            return textures.get(filename);
        } else {
            Texture texture = new Texture(Gdx.files.internal(filename));
            textures.put(filename, texture);
            return texture;
        }
    }

}

