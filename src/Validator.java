package src;
import java.util.Map;
import java.util.function.Function;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Validator {
    private static boolean isValidEmail(String email) {
        return email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    }

    private static boolean isValidNIF(String nif) {
        return nif.matches("^[0-9]{9}$");
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^(9|2|3)\\d{8}$");
    }

    private static boolean isValidName(String name) {
        return name.matches("^[A-Z](?=.{2,100}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$");
    }

    private static boolean isValidLogin(String login) {
        return login.matches("^[A-Za-z0-9_-]{3,20}$");
    }


    private static boolean isValidAddress(String address) {
        return address.matches("^[\\w\\s,\\.]{2,100}$");
    }

    private static final Map<String, Function<String, Boolean>> validators = Map.ofEntries(
        Map.entry("email", Validator::isValidEmail),
        Map.entry("nif", Validator::isValidNIF),
        Map.entry("telefone", Validator::isValidPhoneNumber),
        Map.entry("name", Validator::isValidName),
        Map.entry("login", Validator::isValidLogin),
        Map.entry("morada", Validator::isValidAddress)
    );

    public static String validatePassword(String type) {
        String password;
        System.out.print("Password: ");
        password = Input.readLine();
        return encryptPassword(password);
    }

    public static String validateInput(String typeString) {
        String input;
        boolean check = false;
        do {
            System.out.print(typeString + ": ");
            String type = typeString.replaceAll("\\s*\\(optional\\)|(?i)updated\\s+", "").toLowerCase();
            input = Input.readLine();
            boolean isValid = validators.get(type).apply(input);
            if (!isValid) {
                System.out.println("Invalid " + type + ". Please try again.");
                continue;
            }
            else if(Sistema.isValueUnique(type, input)) {
                check = true;
                System.out.println("Valid " + type + ".");
            }
            else {
                System.out.println(type + " already exists. Please try again.");
            }
        } while (!check);
        return input;
    }

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}