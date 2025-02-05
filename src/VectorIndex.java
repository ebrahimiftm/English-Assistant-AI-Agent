import java.util.ArrayList;
import java.util.List;

public class VectorIndex {
    private List<VectorRecord> records;

    public VectorIndex() {
        records = new ArrayList<>();
    }

    public void addRecord(VectorRecord record) {
        records.add(record);
    }

    public List<VectorRecord> search(double[] queryVector, int topK) {
        List<ScoredRecord> scoredRecords = new ArrayList<>();
        for (VectorRecord rec : records) {
            double sim = cosineSimilarity(queryVector, rec.getVector());
            scoredRecords.add(new ScoredRecord(rec, sim));
        }
        scoredRecords.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
        List<VectorRecord> result = new ArrayList<>();
        for (int i = 0; i < Math.min(topK, scoredRecords.size()); i++) {
            result.add(scoredRecords.get(i).getRecord());
        }
        return result;
    }

    private double cosineSimilarity(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Vectors must have the same length.");
        }
        double dot = 0.0, normA = 0.0, normB = 0.0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    private static class ScoredRecord {
        private VectorRecord record;
        private double score;

        public ScoredRecord(VectorRecord record, double score) {
            this.record = record;
            this.score = score;
        }

        public VectorRecord getRecord() {
            return record;
        }

        public double getScore() {
            return score;
        }
    }
}
