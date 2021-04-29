import data_structures.LinkedList;

import java.io.FileReader;
import java.io.IOException;

public class FlightRepository {

    private LinkedList<String> cities = new LinkedList<>();

    private LinkedList<String> departureСities = new LinkedList<>();

    private LinkedList<String> arrivalСities = new LinkedList<>();

    private LinkedList<Integer> flightPrices = new LinkedList<>();

    private LinkedList<Integer> returnFlightPrices = new LinkedList<>();

    public FlightRepository(String fileName) {
        try {
            FileReader file = new FileReader(fileName);
            String stringFromFile = FileUtils.readTextFromFile(file);
            setAll(stringFromFile);
        }
        catch (IOException e) {
            e.getMessage();
        }
    }

    public LinkedList<String> getCities() {
        return cities;
    }

    public LinkedList<String> getDepartureСities() {
        return departureСities;
    }

    public LinkedList<String> getArrivalСities() {
        return arrivalСities;
    }

    public LinkedList<Integer> getFlightPrices() {
        return flightPrices;
    }

    public LinkedList<Integer> getReturnFlightPrice() {
        return returnFlightPrices;
    }

    private void setAll(String s) {
        for (int i = 0; i < s.length(); i++) {
            String departureCity = "";
            String arrivalCity = "";
            String flightPrice = "";
            String returnFlightPrice = "";

            while (s.charAt(i) != ';') {
                departureCity += s.charAt(i);
                i++;
            }

            i++;

            while (s.charAt(i) != ';') {
                arrivalCity += s.charAt(i);
                i++;
            }

            i++;

            while (s.charAt(i) != ';') {
                flightPrice += s.charAt(i);
                i++;
            }

            i++;

            while (s.charAt(i) != '\n') {
                returnFlightPrice += s.charAt(i);
                i++;

                if (i == s.length()) break;
            }

            this.departureСities.pushBack(departureCity);
            this.arrivalСities.pushBack(arrivalCity);

            if (flightPrice.equals("N/A"))
                this.flightPrices.pushBack(50000);
            else
                this.flightPrices.pushBack(Integer.parseInt(flightPrice));

            if (returnFlightPrice.equals("N/A"))
                this.returnFlightPrices.pushBack(50000);
            else
                this.returnFlightPrices.pushBack(Integer.parseInt(returnFlightPrice));
        }

        setCities();
    }

    private void setCities() {
        for (int i = 0; i < this.departureСities.getSize(); i++) {

            String departureCity = this.departureСities.get(i);
            String arrivalCity = this.arrivalСities.get(i);

            if (!this.cities.has(departureCity))
                this.cities.pushBack(departureCity);

            if (!this.cities.has(arrivalCity))
                this.cities.pushBack(arrivalCity);
        }
    }

    public int getPriceOfWay(String from, String to) {

        for (int i = 0; i < this.departureСities.getSize(); i++) {

            if (this.departureСities.get(i).equals(from)
                    && this.arrivalСities.get(i).equals(to)) {
                return this.flightPrices.get(i);
            }

            if (this.departureСities.get(i).equals(to)
                    && this.arrivalСities.get(i).equals(from)) {
                return this.returnFlightPrices.get(i);
            }
        }

        return 50000;
    }

    public void printRepository() {

        System.out.print("cities: ");
        cities.printList();

        System.out.print("departureСities: ");
        departureСities.printList();

        System.out.print("arrivalCities: ");
        arrivalСities.printList();

        System.out.print("flightPrice: ");
        flightPrices.printList();

        System.out.print("returnFlightPrice: ");
        returnFlightPrices.printList();

    }
}
