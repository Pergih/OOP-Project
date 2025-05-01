import java.util.Scanner;

public class UserFactory {
    public static User create (Scanner scanner) {
        System.out.print("Handle: ");
        String handle = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.println("Choose a plan:");
        System.out.println("1. Free");
        System.out.println("2. Premium Base");
        System.out.println("3. Premium Top");
        System.out.print("Option: ");
        int option = Integer.parseInt(scanner.nextLine());

        Plan plan;
        switch (option) {
            case 1: plan = new PlanFree(); break;
            case 2: plan = new PlanBasePremium(); break;
            case 3: plan = new PlanTopPremium(); break;
            default:
                System.out.println("Invalid option. Defaulting to Free.");
                plan = new PlanFree();
        }

        User user = new User(handle, name, email, address, plan);
        user.setPlan(plan); // apply join bonus
        return user;

    }


}
