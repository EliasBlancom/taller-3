import java.math.BigDecimal;
import java.util.Scanner;
import model.*;
import services.*;

public class App {

    private static final AccountService accountService = new AccountService();
    private static final CardsService cardsService = new CardsService();
    private static final BalanceService balanceService = new BalanceService();
    private static final LoansService loansService = new LoansService();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();
                switch (option) {
                    case "1" -> runAccountMenu(sc);
                    case "2" -> runBalanceMenu(sc);
                    case "3" -> runLoansMenu(sc);
                    case "4" -> runCardsMenu(sc);
                    case "0" -> {
                        running = false;
                        System.out.println("Saliendo del sistema...");
                    }
                    default -> System.out.println("Opción no válida. Intente nuevamente.");
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Account");
        System.out.println("2. Balance");
        System.out.println("3. Loans");
        System.out.println("4. Cards");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // ==============================
    // CRUD: ACCOUNT
    // ==============================
    private static void runAccountMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Account");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> { // CREATE
                    System.out.print("Número de cuenta: ");
                    String accNum = sc.nextLine();
                    System.out.print("Nombre: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Celular: ");
                    String mobile = sc.nextLine();
                    System.out.print("Tipo de cuenta (Savings/Checking): ");
                    String type = sc.nextLine();
                    System.out.print("Dirección: ");
                    String addr = sc.nextLine();

                    Account acc = new Account(accNum, name, email, mobile, type, addr);
                    accountService.save(acc);
                    System.out.println("✅ Cuenta creada correctamente.");
                }
                case "2" -> { // READ
                    System.out.print("Ingrese número de cuenta: ");
                    String id = sc.nextLine();
                    accountService.findById(id).ifPresentOrElse(
                        a -> System.out.println("📋 " + a),
                        () -> System.out.println("⚠️ No se encontró la cuenta.")
                    );
                }
                case "3" -> { // LIST ALL
                    accountService.findAll().forEach(System.out::println);
                }
                case "4" -> { // UPDATE
                    System.out.print("Número de cuenta a actualizar: ");
                    String idUp = sc.nextLine();
                    accountService.findById(idUp).ifPresentOrElse(acc -> {
                        System.out.print("Nuevo nombre: ");
                        acc.setName(sc.nextLine());
                        System.out.print("Nuevo email: ");
                        acc.setEmail(sc.nextLine());
                        System.out.print("Nuevo celular: ");
                        acc.setMobileNumber(sc.nextLine());
                        System.out.print("Nuevo tipo de cuenta: ");
                        acc.setAccountType(sc.nextLine());
                        System.out.print("Nueva dirección: ");
                        acc.setAddress(sc.nextLine());
                        accountService.save(acc);
                        System.out.println("✅ Cuenta actualizada correctamente.");
                    }, () -> System.out.println("⚠️ Cuenta no encontrada."));
                }
                case "5" -> {
                    System.out.print("Número de cuenta a eliminar: ");
                    String idDel = sc.nextLine();
                    if (accountService.deleteById(idDel)) {
                        System.out.println("✅ Cuenta eliminada.");
                    } else {
                        System.out.println("⚠️ No se encontró esa cuenta.");
                    }
                }
                case "0" -> back = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // ==============================
    // CRUD: CARDS
    // ==============================
    private static void runCardsMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Cards");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> {
                    System.out.print("Número de tarjeta: ");
                    String num = sc.nextLine();
                    System.out.print("Tipo (Credit/Debit): ");
                    String type = sc.nextLine();
                    System.out.print("Límite total: ");
                    BigDecimal limit = new BigDecimal(sc.nextLine());
                    System.out.print("Monto usado: ");
                    BigDecimal used = new BigDecimal(sc.nextLine());
                    BigDecimal available = limit.subtract(used);

                    Cards c = new Cards(num, type, limit, used, available);
                    cardsService.save(c);
                    System.out.println("✅ Tarjeta creada.");
                }
                case "2" -> {
                    System.out.print("Número de tarjeta: ");
                    String id = sc.nextLine();
                    cardsService.findById(id).ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("⚠️ No encontrada.")
                    );
                }
                case "3" -> cardsService.findAll().forEach(System.out::println);
                case "4" -> {
                    System.out.print("Número de tarjeta a actualizar: ");
                    String idUp = sc.nextLine();
                    cardsService.findById(idUp).ifPresentOrElse(card -> {
                        System.out.print("Nuevo tipo: ");
                        card.setType(sc.nextLine());
                        System.out.print("Nuevo límite total: ");
                        card.setTotalLimit(new BigDecimal(sc.nextLine()));
                        System.out.print("Nuevo monto usado: ");
                        card.setAmountUsed(new BigDecimal(sc.nextLine()));
                        card.setAvailable(card.getTotalLimit().subtract(card.getAmountUsed()));
                        cardsService.save(card);
                        System.out.println("✅ Tarjeta actualizada.");
                    }, () -> System.out.println("⚠️ Tarjeta no encontrada."));
                }
                case "5" -> {
                    System.out.print("Número de tarjeta a eliminar: ");
                    String idDel = sc.nextLine();
                    cardsService.deleteById(idDel);
                }
                case "0" -> back = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // ==============================
    // CRUD: BALANCE
    // ==============================
    private static void runBalanceMenu(Scanner sc) {
        System.out.println("⚠️ BalanceService aún no implementado.");
    }

    // ==============================
    // CRUD: LOANS
    // ==============================
    private static void runLoansMenu(Scanner sc) {
        System.out.println("⚠️ LoansService aún no implementado.");
    }

    private static void printCrudMenu(String entityName) {
        System.out.println("\n--- " + entityName + " CRUD ---");
        System.out.println("1. Create");
        System.out.println("2. Read by id");
        System.out.println("3. List all");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Seleccione una opción: ");
    }
}
