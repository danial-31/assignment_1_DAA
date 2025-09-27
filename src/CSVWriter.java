import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private final FileWriter writer;

    public CSVWriter(String filename) throws IOException {
        writer = new FileWriter(filename);
        writer.write("algorithm,n,time,comparisons,allocations,depth\n");
    }

    public void write(String algo, int n, Mertics metrics) throws IOException {
        writer.write(algo + "," + n + "," + metrics.getTime() + "," +
                metrics.getComparisons() + "," +
                metrics.getAllocations() + "," +
                metrics.getMaxDepth() + "\n");
    }

    public void close() throws IOException {
        writer.close();
    }
}
