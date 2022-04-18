package utils;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileService {

    private static final String path = "/Users/Maxim_Sherstoboyev/Education/Senior/hometask/multithreading/m/five/src/main/resources";

    @SneakyThrows
    public synchronized <T> Map<String, T> loadFilesData(String directoryName) {
        File directory = getOrCreateDirectory(directoryName);
        HashMap<String, T> fileData = new HashMap<>();
        if (directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                FileInputStream fileInputStream = FileUtils.openInputStream(file);
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream) {
                        @Override
                        protected void readStreamHeader() {
                        }
                    };
                    fileData.put(file.getName(), (T) objectInputStream.readObject());
                } catch (WriteAbortedException | EOFException exception) {

                }
            }
        }
        return fileData;
    }

    @SneakyThrows
    public synchronized <T> void saveObjectToFile(T object, String directoryName, String fileName) {
        File file = getOrCreateDirectory(directoryName);
        System.out.println(file);
        FileOutputStream fileOutputStream = new FileOutputStream(path + "/" + directoryName + "/" + fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream) {
            @Override
            protected void writeStreamHeader() {
            }
        };
        oos.writeObject(object);
        oos.flush();
        oos.reset();
        oos.close();
    }

    private File getOrCreateDirectory(String directoryName) throws IOException {
        Path directory = Paths.get(FileService.path + "/" + directoryName);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
            System.out.println("Directory created: " + directory.getFileName());
        } else {
            System.out.println("Directory already exists");
        }
        return directory.toFile();
    }
}
