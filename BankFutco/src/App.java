import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import model.Account;
import model.Balance;
import model.Cards;
import model.Loans;
import services.AccountService;
import services.BalanceService;
import services.CardsService;
import services.LoansService;

public class App {
    private static AccountService accountService = new AccountService();
    private static BalanceService balanceService = new BalanceService();
    private static LoansService loansService = new LoansService();
    private static CardsService cardsService = new CardsService();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();
                switch (option) {
                    case "1" -> runCrudMenu(sc, "Account");
                    case "2" -> runCrudMenu(sc, "Balance");
                    case "3" -> runCrudMenu(sc, "Loans");
                    case "4" -> runCrudMenu(sc, "Cards");
                    case "0" -> {
                        running = false;
                        System.out.println("Saliendo...");
                    }
                    default -> System.out.println("Opción no válida.");
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

    private static void runCrudMenu(Scanner sc, String entityName) {
        boolean back = false;
        while (!back) {
            printCrudMenu(entityName);
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> createEntity(sc, entityName);
                case "2" -> readEntity(sc, entityName);
                case "3" -> listEntities(entityName);
                case "4" -> updateEntity(sc, entityName);
                case "5" -> deleteEntity(sc, entityName);
                case "0" -> back = true;
                default -> System.out.println("Opción no válida.");
            }
        }
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

    private static void createEntity(Scanner sc, String entityName) {
        try {
            switch (entityName) {
                case "Account" -> {
                    System.out.print("Número de cuenta: ");
                    String accNum = sc.nextLine().trim();
                    System.out.print("Nombre completo: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Correo: ");
                    String email = sc.nextLine().trim();
                    System.out.print("Teléfono: ");
                    String phone = sc.nextLine().trim();
                    System.out.print("Tipo de cuenta: ");
                    String type = sc.nextLine().trim();
                    System.out.print("Dirección: ");
                    String address = sc.nextLine().trim();
                    Account account = new Account(accNum, name, email, phone, type, address);
                    accountService.save(account);
                    System.out.println("Cuenta creada exitosamente.");
                }
                case "Balance" -> {
                    LocalDate date = readDate(sc, "Fecha (YYYY-MM-DD): ");
                    System.out.print("Descripción: ");
                    String desc = sc.nextLine().trim();
                    BigDecimal cashIn = readBigDecimal(sc, "Cash In: ");
                    BigDecimal cashOut = readBigDecimal(sc, "Cash Out: ");
                    BigDecimal closing = readBigDecimal(sc, "Closing Balance: ");
                    Balance balance = new Balance(date, desc, cashIn, cashOut, closing);
                    balanceService.save(balance);
                    System.out.println("Balance creado exitosamente.");
                }
                case "Loans" -> {
                    LocalDate date = readDate(sc, "Fecha (YYYY-MM-DD): ");
                    System.out.print("Tipo de préstamo: ");
                    String type = sc.nextLine().trim();
                    BigDecimal totalLoan = readBigDecimal(sc, "Total Loan: ");
                    BigDecimal paid = readBigDecimal(sc, "Amount Paid: ");
                    BigDecimal outstanding = readBigDecimal(sc, "Outstanding Amount: ");
                    Loans loan = new Loans(date, type, totalLoan, paid, outstanding);
                    loansService.save(loan);
                    System.out.println("Loan creado exitosamente.");
                }
                case "Cards" -> {
                    System.out.print("Número de tarjeta: ");
                    String cardNum = sc.nextLine().trim();
                    System.out.print("Tipo (Credit/Debit): ");
                    String type = sc.nextLine().trim();
                    BigDecimal totalLimit = readBigDecimal(sc, "Total Limit: ");
                    BigDecimal used = readBigDecimal(sc, "Amount Used: ");
                    BigDecimal available = readBigDecimal(sc, "Available: ");
                    Cards card = new Cards(cardNum, type, totalLimit, used, available);
                    cardsService.save(card);
                    System.out.println("Card creada exitosamente.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear la entidad: " + e.getMessage());
        }
    }

    private static LocalDate readDate(Scanner sc, String prompt) {
        LocalDate date = null;
        while (date == null) {
            try {
                System.out.print(prompt);
                date = LocalDate.parse(sc.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Intente de nuevo.");
            }
        }
        return date;
    }

    private static BigDecimal readBigDecimal(Scanner sc, String prompt) {
        BigDecimal value = null;
        while (value == null) {
            try {
                System.out.print(prompt);
                value = new BigDecimal(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Intente de nuevo.");
            }
        }
        return value;
    }

    private static void readEntity(Scanner sc, String entityName) {
        System.out.print("Ingrese id: ");
        String id = sc.nextLine().trim();
        switch (entityName) {
            case "Account" -> accountService.findById(id).ifPresentOrElse(System.out::println, () -> System.out.println("No encontrado."));
            case "Balance" -> balanceService.findById(id).ifPresentOrElse(System.out::println, () -> System.out.println("No encontrado."));
            case "Loans" -> loansService.findById(id).ifPresentOrElse(System.out::println, () -> System.out.println("No encontrado."));
            case "Cards" -> cardsService.findById(id).ifPresentOrElse(System.out::println, () -> System.out.println("No encontrado."));
        }
    }

    private static void listEntities(String entityName) {
        switch (entityName) {
            case "Account" -> accountService.findAll().forEach(System.out::println);
            case "Balance" -> balanceService.findAll().forEach(System.out::println);
            case "Loans" -> loansService.findAll().forEach(System.out::println);
            case "Cards" -> cardsService.findAll().forEach(System.out::println);
        }
    }

    private static void deleteEntity(Scanner sc, String entityName) {
        System.out.print("Ingrese id a eliminar: ");
        String id = sc.nextLine().trim();
        boolean deleted = switch (entityName) {
            case "Account" -> accountService.deleteById(id);
            case "Balance" -> balanceService.deleteById(id);
            case "Loans" -> loansService.deleteById(id);
            case "Cards" -> cardsService.deleteById(id);
            default -> false;
        };
        System.out.println(deleted ? "Eliminado correctamente." : "No se encontró el registro.");
    }

    private static void updateEntity(Scanner sc, String entityName) {
        try {
            switch (entityName) {
                case "Account" -> {
                    System.out.print("Ingrese el número de cuenta a actualizar: ");
                    String accNum = sc.nextLine().trim();
                    accountService.findById(accNum).ifPresentOrElse(account -> {
                        System.out.print("Nuevo nombre (" + account.getName() + "): ");
                        String name = sc.nextLine().trim();
                        if (!name.isEmpty()) account.setName(name);

                        System.out.print("Nuevo correo (" + account.getEmail() + "): ");
                        String email = sc.nextLine().trim();
                        if (!email.isEmpty()) account.setEmail(email);

                        System.out.print("Nuevo teléfono (" + account.getMobileNumber() + "): ");
                        String phone = sc.nextLine().trim();
                        if (!phone.isEmpty()) account.setMobileNumber(phone);

                        System.out.print("Nuevo tipo de cuenta (" + account.getAccountType() + "): ");
                        String type = sc.nextLine().trim();
                        if (!type.isEmpty()) account.setAccountType(type);

                        System.out.print("Nueva dirección (" + account.getAddress() + "): ");
                        String address = sc.nextLine().trim();
                        if (!address.isEmpty()) account.setAddress(address);

                        accountService.save(account);
                        System.out.println("Cuenta actualizada correctamente.");
                    }, () -> System.out.println("Cuenta no encontrada."));
                }

                case "Balance" -> {
                    System.out.print("Ingrese la fecha (YYYY-MM-DD) del balance a actualizar: ");
                    LocalDate date = readDate(sc, "");
                    balanceService.findById(date.toString()).ifPresentOrElse(balance -> {
                        System.out.print("Nueva descripción (" + balance.getDescription() + "): ");
                        String desc = sc.nextLine().trim();
                        if (!desc.isEmpty()) balance.setDescription(desc);

                        BigDecimal cashIn = readOptionalBigDecimal(sc, "Nuevo Cash In (" + balance.getCashIn() + "): ");
                        if (cashIn != null) balance.setCashIn(cashIn);

                        BigDecimal cashOut = readOptionalBigDecimal(sc, "Nuevo Cash Out (" + balance.getCashOut() + "): ");
                        if (cashOut != null) balance.setCashOut(cashOut);

                        BigDecimal closing = readOptionalBigDecimal(sc, "Nuevo Closing Balance (" + balance.getClosingBalance() + "): ");
                        if (closing != null) balance.setClosingBalance(closing);

                        balanceService.save(balance);
                        System.out.println("Balance actualizado correctamente.");
                    }, () -> System.out.println("Balance no encontrado."));
                }

                case "Loans" -> {
                    System.out.print("Ingrese la fecha (YYYY-MM-DD) del préstamo a actualizar: ");
                    LocalDate date = readDate(sc, "");
                    loansService.findById(date.toString()).ifPresentOrElse(loan -> {
                        System.out.print("Nuevo tipo de préstamo (" + loan.getType() + "): ");
                        String type = sc.nextLine().trim();
                        if (!type.isEmpty()) loan.setType(type);

                        BigDecimal totalLoan = readOptionalBigDecimal(sc, "Nuevo Total Loan (" + loan.getTotalLoan() + "): ");
                        if (totalLoan != null) loan.setTotalLoan(totalLoan);

                        BigDecimal paid = readOptionalBigDecimal(sc, "Nuevo Amount Paid (" + loan.getAmountPaid() + "): ");
                        if (paid != null) loan.setAmountPaid(paid);

                        BigDecimal outstanding = readOptionalBigDecimal(sc, "Nuevo Outstanding Amount (" + loan.getOutstandingAmt() + "): ");
                        if (outstanding != null) loan.setOutstandingAmt(outstanding);

                        loansService.save(loan);
                        System.out.println("Préstamo actualizado correctamente.");
                    }, () -> System.out.println("Préstamo no encontrado."));
                }

                case "Cards" -> {
                    System.out.print("Ingrese el número de tarjeta a actualizar: ");
                    String cardNum = sc.nextLine().trim();
                    cardsService.findById(cardNum).ifPresentOrElse(card -> {
                        System.out.print("Nuevo tipo de tarjeta (" + card.getType() + "): ");
                        String type = sc.nextLine().trim();
                        if (!type.isEmpty()) card.setType(type);

                        BigDecimal totalLimit = readOptionalBigDecimal(sc, "Nuevo Total Limit (" + card.getTotalLimit() + "): ");
                        if (totalLimit != null) card.setTotalLimit(totalLimit);

                        BigDecimal used = readOptionalBigDecimal(sc, "Nuevo Amount Used (" + card.getAmountUsed() + "): ");
                        if (used != null) card.setAmountUsed(used);

                        BigDecimal available = readOptionalBigDecimal(sc, "Nuevo Available (" + card.getAvailable() + "): ");
                        if (available != null) card.setAvailable(available);

                        cardsService.save(card);
                        System.out.println("Tarjeta actualizada correctamente.");
                    }, () -> System.out.println("Tarjeta no encontrada."));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    private static BigDecimal readOptionalBigDecimal(Scanner sc, String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return null;
        try {
            return new BigDecimal(input);
        } catch (NumberFormatException e) {
            System.out.println("Número inválido, se mantiene el valor anterior.");
            return null;
        }
    }
}