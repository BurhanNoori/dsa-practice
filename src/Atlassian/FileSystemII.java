package Atlassian;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileSystemII {
    String name;
    int value;
    Map<String, FileSystemII> children;

    public FileSystemII(String name, int value) {
        this.name = name;
        this.value = value;
        children = new HashMap<>();
    }

    public FileSystemII() {
        this.name = "";
        this.value = -1;
        children = new HashMap<>();
    }

    public boolean createPath(String path, int value) {
        return dfs(this, path, value);
    }

    public int get(String path) {
        return getValueHelper(this, path);
    }

    private boolean dfs(FileSystemII fs, String path, int value) {
        String [] pathArr = Arrays.stream(path.split("/"))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
        if (pathArr.length == 1 && fs.children.containsKey(pathArr[0])) {
            return false;
        } else if(pathArr.length == 1 && !fs.children.containsKey(pathArr[0])) {
            FileSystemII node = new FileSystemII(pathArr[0], value);
            fs.children.put(pathArr[0], node);
            return true;
        }

        if (!fs.children.containsKey(pathArr[0])) return false;

        int nextSlash = path.indexOf('/',1) == -1 ? path.length() : path.indexOf('/',1);
        String newPath = path.substring(nextSlash);

        return dfs(fs.children.get(pathArr[0]), newPath, value);
    }

    private int getValueHelper(FileSystemII fs, String path) {

        String [] pathArr = Arrays.stream(path.split("/"))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
        if (pathArr.length == 1) {
            if(fs.children.containsKey(pathArr[0])) return fs.children.get(pathArr[0]).value;
            return -1;
        }

        if (!fs.children.containsKey(pathArr[0])) return -1;

        int nextSlash = path.indexOf('/',1) == -1 ? path.length() : path.indexOf('/',1);
        String newPath = path.substring(nextSlash);
        System.out.println(newPath);
        return getValueHelper(fs.children.get(pathArr[0]), newPath);
    }


}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */