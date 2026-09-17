package smartbudget;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DataStore {
    private final Path file;

    public DataStore(String filename) {
        this.file = Paths.get(filename);
    }

    public List<Expense> load() {
        List<Expense> result = new ArrayList<>();
        if (!Files.exists(file)) return result;

        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                try {
                    result.add(Expense.fromCsv(line));
                } catch (RuntimeException ex) {
                    System.err.println("Skipped invalid record.");
                }
            }
        } catch (IOException ex) {
            System.err.println("Could not read data file: " + ex.getMessage());
        }
        return result;
    }

    public boolean save(List<Expense> expenses) {
        try {
            Path parent = file.getParent();
            if (parent != null) Files.createDirectories(parent);
            try (BufferedWriter bw = Files.newBufferedWriter(file)) {
                for (Expense e : expenses) {
                    bw.write(e.toCsv());
                    bw.newLine();
                }
            }
            return true;
        } catch (IOException ex) {
            System.err.println("Could not save data: " + ex.getMessage());
            return false;
        }
    }
}
