package com.voronin.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alexey Voronin.
 * @since 11.08.2023.
 */
public class FileUtils<P extends Path, T> {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final P path;
    private final Type type;

    private FileUtils(P path, Type type) {
        this.path = path;
        this.type = type;
    }

    public FileUtils(P path, TypeToken<?> typeToken) {
        this(path, typeToken.getType());
    }

    public List<T> getFromFile() {
        T[] arr;
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            arr = gson.fromJson(br, type);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        }
        List<T> list = new ArrayList<>();
        if (arr != null) {
            list = new ArrayList<>(Arrays.asList(arr));
        }
        return list;
    }

    public void writeToFile(List<T> list) {
        try {
            Files.write(path, gson.toJson(list).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
