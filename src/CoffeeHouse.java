import java.util.Scanner;

// Product interface
interface Coffee {
    void brew();
    double getPrice();
}

// Concrete products
class Espresso implements Coffee {
    private final double basePrice = 2.0;
    @Override
    public void brew() {
        System.out.println("Brewing Espresso");
    }
    @Override
    public double getPrice() {
        return basePrice;
    }
}

class Latte implements Coffee {
    private final double basePrice = 3.0;
    @Override
    public void brew() {
        System.out.println("Brewing Latte");
    }
    @Override
    public double getPrice() {
        return basePrice;
    }
}

class Cappuccino implements Coffee {
    private final double basePrice = 3.5;
    @Override
    public void brew() {
        System.out.println("Brewing Cappuccino");
    }
    @Override
    public double getPrice() {
        return basePrice;
    }
}

// Product interface for snacks
interface Snack {
    void prepare();
    double getPrice();
}

// Concrete products for snacks
class Croissant implements Snack {
    private final double basePrice = 1.5;
    @Override
    public void prepare() {
        System.out.println("Preparing Croissant");
    }
    @Override
    public double getPrice() {
        return basePrice;
    }
}

class Cookie implements Snack {
    private final double basePrice = 1.0;
    @Override
    public void prepare() {
        System.out.println("Preparing Cookie");
    }
    @Override
    public double getPrice() {
        return basePrice;
    }
}

// Factory interface
interface CoffeeFactory {
    Coffee createCoffee();
}

// Concrete factories
class EspressoFactory implements CoffeeFactory {
    @Override
    public Coffee createCoffee() {
        return new Espresso();
    }
}

class LatteFactory implements CoffeeFactory {
    @Override
    public Coffee createCoffee() {
        return new Latte();
    }
}

class CappuccinoFactory implements CoffeeFactory {
    @Override
    public Coffee createCoffee() {
        return new Cappuccino();
    }
}

// Factory interface for snacks
interface SnackFactory {
    Snack createSnack();
}

// Concrete factories for snacks
class CroissantFactory implements SnackFactory {
    @Override
    public Snack createSnack() {
        return new Croissant();
    }
}

class CookieFactory implements SnackFactory {
    @Override
    public Snack createSnack() {
        return new Cookie();
    }
}

// Client
public class CoffeeHouse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Coffee House!");

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Espresso - $2.00");
            System.out.println("2. Latte - $3.00");
            System.out.println("3. Cappuccino - $3.50");
            System.out.println("Please choose a coffee:");

            int coffeeChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            CoffeeFactory coffeeFactory = null;
            switch (coffeeChoice) {
                case 1:
                    coffeeFactory = new EspressoFactory();
                    break;
                case 2:
                    coffeeFactory = new LatteFactory();
                    break;
                case 3:
                    coffeeFactory = new CappuccinoFactory();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    continue;
            }

            Coffee coffee = coffeeFactory.createCoffee();
            coffee.brew();
            double totalCost = coffee.getPrice();

            boolean addMoreCustomization = true;
            while (addMoreCustomization) {
                System.out.println("Would you like to add more customizations? (yes/no)");
                String customizeChoice = scanner.nextLine().toLowerCase();

                if (customizeChoice.equals("yes")) {
                    System.out.println("Customization options:");
                    System.out.println("1. Add milk (+$0.50)");
                    System.out.println("2. Add sugar (+$0.25)");
                    System.out.println("3. Add flavorings (+$0.75)");
                    System.out.println("4. No more customizations");
                    System.out.println("Please select an option:");

                    int customizationChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    switch (customizationChoice) {
                        case 1:
                            System.out.println("Adding milk to your coffee.");
                            totalCost += 0.5;
                            break;
                        case 2:
                            System.out.println("Adding sugar to your coffee.");
                            totalCost += 0.25;
                            break;
                        case 3:
                            System.out.println("Adding flavorings to your coffee.");
                            totalCost += 0.75;
                            break;
                        case 4:
                            System.out.println("No more customizations selected.");
                            addMoreCustomization = false;
                            break;
                        default:
                            System.out.println("Invalid choice! No more customizations selected.");
                            addMoreCustomization = false;
                            break;
                    }
                } else if (customizeChoice.equals("no")) {
                    addMoreCustomization = false;
                } else {
                    System.out.println("Invalid choice! Please try again.");
                }
            }

            System.out.println("Would you like to order a snack? (yes/no)");
            String snackChoice = scanner.nextLine().toLowerCase();
            if (snackChoice.equals("yes")) {
                System.out.println("Snack Menu:");
                System.out.println("1. Croissant - $1.50");
                System.out.println("2. Cookie - $1.00");
                System.out.println("Please choose a snack:");

                int selectedSnack = scanner.nextInt();
                scanner.nextLine(); // consume newline

                SnackFactory snackFactory = null;
                switch (selectedSnack) {
                    case 1:
                        snackFactory = new CroissantFactory();
                        break;
                    case 2:
                        snackFactory = new CookieFactory();
                        break;
                    default:
                        System.out.println("Invalid snack choice! Skipping snack order.");
                        break;
                }

                if (snackFactory != null) {
                    Snack snack = snackFactory.createSnack();
                    snack.prepare();
                    totalCost += snack.getPrice();
                }
            }

            System.out.println("Total cost: $" + totalCost);

            System.out.println("Please select payment method: (1. Cash / 2. Card)");
            int paymentChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (paymentChoice == 1) {
                System.out.println("Payment received in cash. Thank you!");
            } else if (paymentChoice == 2) {
                System.out.println("Payment received via card. Thank you!");
            } else {
                System.out.println("Invalid payment choice!");
            }

            System.out.println("Enjoy your coffee!");

            System.out.println("Would you like to place another order? (yes/no)");
            String anotherOrder = scanner.nextLine().toLowerCase();
            if (!anotherOrder.equals("yes")) {
                break;
            }
        }

        scanner.close();
    }
}
