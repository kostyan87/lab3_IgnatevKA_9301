
public class AdjacencyMatrixFromFile {

    private int[][] matrix;

    private int sizeOfMatrix;

    private FlightRepository repo;

    public FlightRepository getRepo() {
        return repo;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getSizeOfMatrix() {
        return sizeOfMatrix;
    }

    public AdjacencyMatrixFromFile(String fileName) {
        this.repo = new FlightRepository(fileName);
        fillMatrix(fileName);
    }

    public void fillMatrix(String fileName) {

        this.sizeOfMatrix = this.repo.getCities().getSize();
        this.matrix = new int[this.sizeOfMatrix][this.sizeOfMatrix];

        for (int i = 0; i < this.sizeOfMatrix; i++) {
            for (int j = 0; j < this.sizeOfMatrix; j++) {
                if (i == j) this.matrix[i][j] = 0;
                else {
                    this.matrix[i][j] = this.repo.getPriceOfWay
                            (this.repo.getCities().get(i),
                                    this.repo.getCities().get(j));
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < this.sizeOfMatrix; i++) {
            for (int j = 0; j < this.sizeOfMatrix; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println('\n');
        }
    }
}