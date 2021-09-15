package de.max.swarps.utils;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileManager {

    protected String f;
    File file;
    YamlConfiguration data;

    public FileManager(File file) {
        this.file = file;
        data = YamlConfiguration.loadConfiguration(file);
        f = file.getPath();
    }

    public FileManager(String f) {
        this.f = f;
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
    }

    public FileManager(String path, String f) {
        this.f = path + "/" + f;
        file = new File(path, f);
        data = YamlConfiguration.loadConfiguration(file);
    }

    public void set(String o, Object v) {
        data.set(o, v);
    }

    public void delete(String o) {
        data.set(o, null);
    }

    public boolean delete() {
        return file.delete();
    }

    public void add(String o, Object v) {
        if (!isSet(o)) {
            data.set(o, v);
        }
        save();
    }

    public boolean exist() {
        return file.exists();
    }

    public boolean isSet(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.isSet(o);
    }

    public List<String> getStringList(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getStringList(o);
    }

    public boolean getBoolean(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getBoolean(o);
    }

    public Location getLocation(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getLocation(o);
    }

    public int getInt(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getInt(o);
    }

    public double getDouble(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getDouble(o);
    }

    public long getLong(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getLong(o);
    }

    public Object get(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.get(o);
    }

    public List<?> getList(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getList(o);
    }

    public ItemStack getItemStack(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getItemStack(o);
    }

    public String getString(String o) {
        file = new File(f);
        data = YamlConfiguration.loadConfiguration(file);
        return data.getString(o);
    }

    public void save() {
        try {
            data.save(file);
        } catch (IOException ignored) {
        }
    }

    public void addDefault(String o, Object v) {
        data.addDefault(o, v);

    }

}


