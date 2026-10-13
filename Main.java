
import java.util.*;

public class Main {

    static String[] areas = {
        "Nijeshnagar",
        "Karthanagar",
        "Budaprolu",
        "Burmingar Street",
        "Temple Street",
        "Main Road"
    };

    static String[] reports = {
        "R101|Nijeshnagar|Earthquake reported and people are trapped.",
        "R102|Karthanagar|Strong tremor felt near residential buildings.",
        "R103|Budaprolu|Aftershock reported after an earthquake.",
        "R104|Burmingar Street|Flood water entered houses after heavy rain.",
        "R105|Temple Street|Fire and smoke reported near a building.",
        "R106|Main Road|Landslide blocked the road.",
        "R107|Nijeshnagar|Cyclone warning issued due to strong winds.",
        "R108|Karthanagar|Tsunami warning issued due to high waves.",
        "R109|Budaprolu|Injured people need medical assistance.",
        "R110|Main Road|People need food, drinking water and medicine."
    };

    static String[] keywords = {
        "earthquake", "tremor", "aftershock", "trapped",
        "flood", "heavy rain", "fire", "smoke",
        "landslide", "cyclone", "strong winds", "tsunami",
        "high waves", "injured", "medical", "food",
        "drinking water", "medicine", "rescue", "missing"
    };

    // KMP string-matching algorithm
    static int kmpSearch(String text, String pattern) {
        text = text.toLowerCase();
        pattern = pattern.toLowerCase();

        int n = text.length();
        int m = pattern.length();

        if (m == 0) return 0;

        int[] lps = new int[m];
        int j = 0;

        for (int i = 1; i < m; ) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[i++] = ++j;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                lps[i++] = 0;
            }
        }

        int i = 0;
        j = 0;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == m) {
                    return i - j;
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        return -1;
    }

    static void searchReports(Scanner sc) {
        System.out.print("\nEnter keyword to search: ");
        String query = sc.nextLine().trim();

        if (query.isEmpty()) {
            System.out.println("Please enter a keyword.");
            return;
        }

        int count = 0;

        System.out.println("\n========== SEARCH RESULTS ==========");

        for (String report : reports) {
            String[] parts = report.split("\\|", 3);

            if (kmpSearch(parts[2], query) != -1) {
                System.out.println("------------------------------------");
                System.out.println("REPORT ID : " + parts[0]);
                System.out.println("AREA      : " + parts[1]);
                System.out.println("REPORT    : " + parts[2]);
                System.out.println("MATCH     : " + query);
                count++;
            }
        }

        System.out.println("------------------------------------");
        System.out.println("Total matching reports: " + count);
    }

    static void showReports() {
        System.out.println("\n========== ALL INCIDENT REPORTS ==========");

        for (String report : reports) {
            String[] parts = report.split("\\|", 3);
            System.out.println("------------------------------------");
            System.out.println("ID     : " + parts[0]);
            System.out.println("AREA   : " + parts[1]);
            System.out.println("REPORT : " + parts[2]);
        }
    }

    static void showAreas() {
        System.out.println("\n========== REGISTERED AREAS ==========");

        for (int i = 0; i < areas.length; i++) {
            System.out.println((i + 1) + ". " + areas[i]);
        }
    }

    static void showDisasters() {
        System.out.println("\n========== SUPPORTED DISASTER TYPES ==========");
        System.out.println("1. Earthquake");
        System.out.println("2. Flood");
        System.out.println("3. Fire");
        System.out.println("4. Landslide");
        System.out.println("5. Cyclone");
        System.out.println("6. Tsunami");
        System.out.println("Other keywords: rescue, trapped, injured,");
        System.out.println("missing, food, drinking water, medicine");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n================================================");
            System.out.println("                  DISASTERNET");
            System.out.println("      EMERGENCY RESPONSE COORDINATION");
            System.out.println("================================================");
            System.out.println("1. Search Emergency Reports (KMP)");
            System.out.println("2. View All Incident Reports");
            System.out.println("3. View Registered Areas");
            System.out.println("4. View Supported Disaster Types");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Enter a valid number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    searchReports(sc);
                    break;
                case 2:
                    showReports();
                    break;
                case 3:
                    showAreas();
                    break;
                case 4:
                    showDisasters();
                    break;
                case 5:
                    System.out.println("DisasterNet closed. Stay safe!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
