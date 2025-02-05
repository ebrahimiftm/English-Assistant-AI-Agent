public class VectorRecord {
    private String id;
    private double[] vector;
    private String metadata;

    public VectorRecord(String id, double[] vector, String metadata) {
        this.id = id;
        this.vector = vector;
        this.metadata = metadata;
    }

    public String getId() {
        return id;
    }

    public double[] getVector() {
        return vector;
    }

    public String getMetadata() {
        return metadata;
    }
}
