public class FordBellmanAlgo {

    public static void main(String[] args) {
        AdjacencyMatrixFromFile matrix =
                new AdjacencyMatrixFromFile("src/main/java/test.txt");
        FordBellmanAlgo(matrix,
                "Владивосток","Хабаровск");
        FordBellmanAlgo(matrix,
                "Владивосток","Санкт-Петербург");
        FordBellmanAlgo(matrix,
                "Владивосток","Москва");
        FordBellmanAlgo(matrix,
                "Москва","Владивосток");
        FordBellmanAlgo(matrix,
                "Москва","Санкт-Петербург");
        FordBellmanAlgo(matrix,
                "Москва","Хабаровск");
        FordBellmanAlgo(matrix,
                "Хабаровск","Владивосток");
        FordBellmanAlgo(matrix,
                "Хабаровск","Санкт-Петербург");
        FordBellmanAlgo(matrix,
                "Хабаровск","Москва");
        FordBellmanAlgo(matrix,
                "Санкт-Петербург","Хабаровск");
        FordBellmanAlgo(matrix,
                "Санкт-Петербург","Владивосток");
        FordBellmanAlgo(matrix,
                "Санкт-Петербург","Москва");
    }

    public static int FordBellmanAlgo(AdjacencyMatrixFromFile matrix,
                                         String from, String to) {
        int[] efficientFlights = new int[matrix.getSizeOfMatrix()];
        initEfficientFlights(efficientFlights,
                matrix.getMatrix(), getNumberOfInitString(matrix, from));
        String resultStr = from;

        for (int m = 0; m < matrix.getSizeOfMatrix() - 1; m++) {
            for (int i = 0; i < matrix.getSizeOfMatrix(); i++) {
                for (int k = 0; k < matrix.getSizeOfMatrix(); k++) {
                    if (efficientFlights[i] > efficientFlights[k] + matrix.getMatrix()[k][i]) {
                        efficientFlights[i] = efficientFlights[k] + matrix.getMatrix()[k][i];
                    }
                }
            }
        }

        /*for (int i = 0; i < efficientFlights.length; i++) {
            System.out.print(efficientFlights[i] + " ");
        }*/

        System.out.println("The optimal way from " + from +
                " to " + to + " is: " +
                efficientFlights[getNumberOfInitString(matrix,to)]);

        return efficientFlights[getNumberOfInitString(matrix,to)];
    }

    private static int getNumberOfInitString(AdjacencyMatrixFromFile matrix,
                                              String s) {
        for (int i = 0; i < matrix.getSizeOfMatrix(); i++) {
            if (matrix.getRepo().getCities().get(i).equals(s))
                return i;
        }
        return -1;
    }

    public static void initEfficientFlights(int[] flights,
                                            int[][] matrix,
                                            int numberOfString) {
        for (int j = 0; j < matrix.length; j++) {
            flights[j] = matrix[numberOfString][j];
        }
    }
}
