package Atlassian;

import java.util.*;

public class FileSystem {
    private FileSystem parent;
    private Map<String, FileSystem> children;
    private String name;
    private FileSystem current;

    public FileSystem() {
        this.children = new HashMap<>();
        this.parent = null;
        this.name = "";
        this.current = this;
    }

    public FileSystem(String name, FileSystem parent) {
        this.children = new HashMap<>();
        this.parent = parent;
        this.name = name;
    }

    public void ls() {
        for (String childName : current.children.keySet()) {
            System.out.println(childName);
        }
    }

    public void mkdir(String path) {
        String[] pathArr = Arrays.stream(path.split("/")).filter(s -> !s.isEmpty()).toArray(String[]::new);
        FileSystem curr = current;

        if (path.startsWith("/")) {
            while (curr.parent != null) curr = curr.parent; // Go to root
        }

        for (String dir : pathArr) {
            if (dir.equals("..")) {
                if (curr.parent != null) curr = curr.parent;
            } else if (dir.equals(".")) {
                continue;
            } else {
                curr.children.putIfAbsent(dir, new FileSystem(dir, curr));
                curr = curr.children.get(dir);
            }
        }
        current = curr;
    }

    public void cd(String path) {
        String[] pathArr = Arrays.stream(path.split("/")).filter(s -> !s.isEmpty()).toArray(String[]::new);
        FileSystem curr = current;

        if (path.startsWith("/")) {
            while (curr.parent != null) curr = curr.parent; // root
        }

        for (String dir : pathArr) {
            if (dir.equals("..")) {
                if (curr.parent != null) curr = curr.parent;
            } else if (dir.equals(".")) {
                continue;
            } else {
                if (!curr.children.containsKey(dir)) throw new IllegalArgumentException("Path not found");
                curr = curr.children.get(dir);
            }
        }
        current = curr;
    }

    public String pwd() {
        Deque<String> stack = new ArrayDeque<>();
        FileSystem curr = current;
        while (curr.parent != null) {
            stack.push(curr.name);
            curr = curr.parent;
        }

        return "/" + String.join("/", stack);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(pwd() + "> ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split(" ");
            String method = parts[0].toLowerCase();
            String arg = parts.length > 1 ? parts[1] : "";

            try {
                switch (method) {
                    case "pwd":
                        System.out.println(pwd()); break;
                    case "ls": ls(); break;
                    case "cd": cd(arg); break;
                    case "mkdir": mkdir(arg); break;
                    case "exit": return;
                    default: System.out.println("Unknown command");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new FileSystem().run();
    }
}
