import java.util.Scanner;

interface Pharmacy {
    int stockManagement();
}

class Medicine implements Pharmacy {
    private String medicineId;
    private String medicineName;
    protected int stockCount;
    private double price;
    private String type;

    Medicine(String medicineId, String medicineName, int stockCount, double price, String type) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.stockCount = stockCount;
        this.price = price;
        this.type = type;
    }

    public String getId() { return medicineId; }
    public String getName() { return medicineName; }
    public double getPrice() { return price; }
    public int getCount() { return stockCount; }
    public String getType() { return type; }

    public void showDetail() {
        System.out.println("Medicine ID    : " + getId());
        System.out.println("Medicine Name  : " + getName());
        System.out.println("Price          : Rs. " + getPrice());
        System.out.println("Stock Count    : " + getCount());
        System.out.println("Type           : " + getType());
    }

    public int stockManagement() {
        return stockCount;
    }
}

class Info extends Medicine {
    Info(String medicineId, String medicineName, int stockCount, double price, String type) {
        super(medicineId, medicineName, stockCount, price, type);
    }

    public void showDetail() {
        System.out.println("====== MEDICINE INFO ======");
        super.showDetail();
        System.out.println("---------------------------");
    }
}

class Buy extends Medicine {
    protected int buyCount;

    Buy(String medicineId, String medicineName, int stockCount, double price, String type, int buyCount) {
        super(medicineId, medicineName, stockCount, price, type);
        this.buyCount = buyCount;
    }

    public int getBuyCount() { return buyCount; }

    public int stockManagement() {
        return stockCount;
    }
}

class Prescription extends Buy {
    private boolean isPrescription;
    private String prescriptionBy;

    Prescription(String medicineId, String medicineName, int stockCount, double price, String type,
                 int buyCount, boolean isPrescription, String prescriptionBy) {
        super(medicineId, medicineName, stockCount, price, type, buyCount);
        this.isPrescription = isPrescription;
        this.prescriptionBy = prescriptionBy;
    }

    public void showDetail() {
        if (isPrescription) {
            super.showDetail();
            System.out.println("Prescribed By  : " + prescriptionBy);
        } else {
            System.out.println("Medicine cannot be sold without a prescription.");
        }
    }

    public int stockManagement() {
        if (isPrescription) {
            if (buyCount <= stockCount) {
                stockCount -= buyCount;
                System.out.println("Medicine purchased successfully.");
            } else {
                System.out.println("Out of stock.");
            }
        } else {
            System.out.println("Medicine cannot be sold without a prescription.");
        }
        return stockCount;
    }
}

class WithoutPrescription extends Buy {
    WithoutPrescription(String medicineId, String medicineName, int stockCount, double price, String type, int buyCount) {
        super(medicineId, medicineName, stockCount, price, type, buyCount);
    }

    public void showDetail() {
        super.showDetail();
    }

    public int stockManagement() {
        if (buyCount <= stockCount) {
            stockCount -= buyCount;
            System.out.println("Medicine purchased successfully.");
        } else {
            System.out.println("Out of stock.");
        }
        return stockCount;
    }
}

class BillMake extends Buy {
    BillMake(String medicineId, String medicineName, int stockCount, double price, String type, int buyCount) {
        super(medicineId, medicineName, stockCount, price, type, buyCount);
    }

    public double bill() {
        double total = buyCount * getPrice();
        if (buyCount > 100) {
            total -= total * 0.25;
        } else if (buyCount > 10) {
            total -= total * 0.1;
        }
        return total;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Info[] medicines = {
            new Info("M0034", "Sertraline",    150, 60,   "Tablet"),
            new Info("M0035", "Metformin",     100, 1000, "Tablet"),
            new Info("M0036", "Zolpidem",       10, 500,  "Injection"),
            new Info("M0134", "Insulin",        200, 600,  "Tablet"),
            new Info("M0074", "Phenytoin",      157, 50,   "Tablet"),
            new Info("M0044", "Morphine",       180, 100,  "Liquid"),
            new Info("M0050", "Isotretinoin",    30, 680,  "Tablet"),
            new Info("M0654", "Prednisolone",   800, 840,  "Liquid"),
            new Info("M0844", "Diazepam",       600, 640,  "Tablet"),
            new Info("M0010", "Fluoxetine",     300, 560,  "Liquid")
        };

        System.out.println("================ WELCOME TO PHARMACY SYSTEM ================");
        System.out.println("What do you want?");
        System.out.println("1. Show available medicine details");
        System.out.println("2. Buy medicine");
        System.out.print("Choose an option: ");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                for (Info med : medicines) {
                    med.showDetail();
                }
                break;

            case 2:
                System.out.println("Available Medicines:");
                System.out.println("[Sertraline / Metformin / Zolpidem / Insulin / Phenytoin / Morphine / Isotretinoin / Prednisolone / Diazepam / Fluoxetine]");
                System.out.print("Enter medicine name: ");
                String medName = input.next();
                System.out.print("How many units? ");
                int buyCount = input.nextInt();
                System.out.print("Is prescription required? (true/false): ");
                boolean isPrescription = input.nextBoolean();
                String prescribedBy = "";
                if (isPrescription) {
                    System.out.print("Prescribed by: ");
                    prescribedBy = input.next();
                }

                boolean found = false;
                for (Info med : medicines) {
                    if (med.getName().equalsIgnoreCase(medName)) {
                        handlePurchase(med, buyCount, isPrescription, prescribedBy);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Medicine not found.");
                }
                break;

            default:
                System.out.println("Invalid option.");
        }

        input.close();
    }

    public static void handlePurchase(Info med, int buyCount, boolean isPrescription, String prescribedBy) {
        if (isPrescription) {
            Prescription p = new Prescription(med.getId(), med.getName(), med.getCount(),
                    med.getPrice(), med.getType(), buyCount, isPrescription, prescribedBy);
            p.stockManagement();
            p.showDetail();
            BillMake b = new BillMake(med.getId(), med.getName(), med.getCount(),
                    med.getPrice(), med.getType(), buyCount);
            System.out.println("Total Bill : Rs. " + b.bill());
        } else {
            WithoutPrescription w = new WithoutPrescription(med.getId(), med.getName(), med.getCount(),
                    med.getPrice(), med.getType(), buyCount);
            w.stockManagement();
            w.showDetail();
            BillMake b = new BillMake(med.getId(), med.getName(), med.getCount(),
                    med.getPrice(), med.getType(), buyCount);
            System.out.println("Total Bill : Rs. " + b.bill());
        }
    }
}
