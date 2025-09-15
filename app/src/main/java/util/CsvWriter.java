package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Utility class for writing data to a CSV file.
 */
public final class CsvWriter {
  private final String filePath;

  public CsvWriter(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Writes a list of string arrays to a CSV file.
   * @param data - list of string arrays, each array represents a row
   * @throws IOException if an I/O error occurs
   */
  public void write(List<String[]> data) throws IOException {
    try (FileWriter writer = new FileWriter(filePath)) {
      for (String[] row : data) {
        writer.append(String.join(",", row)).append("\n");
      }
    }
  }
}
