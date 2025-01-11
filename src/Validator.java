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

    private static boolean isValidIntegerNumeric(String num) {
        return num.matches("^-?\\d+$");
    }

    private static boolean isValidFloatNumeric(String num) {
        return num.matches("^-?\\d+(\\.\\d+)?$");
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

    private static boolean isValidMarca(String marca) {
        return marca.matches("^[A-Za-zÀ-ÿ0-9 .-]{2,50}$");
    }

    private static boolean isValidModelo(String modelo){
        return modelo.matches("^[A-Za-zÀ-ÿ0-9 .-]{2,100}$");
    }

    private static boolean isValidObs(String observacoes) {
        return observacoes.length() <= 500;
    }

    private static final Map<String, Function<String, Boolean>> validators = Map.ofEntries(
        Map.entry("email", Validator::isValidEmail),
        Map.entry("nif", Validator::isValidNIF),
        Map.entry("telefone", Validator::isValidPhoneNumber),
        Map.entry("name", Validator::isValidName),
        Map.entry("login", Validator::isValidLogin),
        Map.entry("morada", Validator::isValidAddress),
        Map.entry("marca", Validator::isValidMarca),
        Map.entry("modelo", Validator::isValidModelo),
        Map.entry("código interno", Validator::isValidLogin),
        Map.entry("geração", Validator::isValidIntegerNumeric),
        Map.entry("sequência", Validator::isValidIntegerNumeric),
        Map.entry("unidade", Validator::isValidIntegerNumeric),
        Map.entry("valor alfa", Validator::isValidIntegerNumeric),
        Map.entry("valor beta", Validator::isValidIntegerNumeric),
        Map.entry("voltagem", Validator::isValidFloatNumeric),
        Map.entry("quantidade de stock", Validator::isValidIntegerNumeric),
        Map.entry("preço de venda", Validator::isValidFloatNumeric),
        Map.entry("observações", Validator::isValidObs),
        Map.entry("quantidade", Validator::isValidIntegerNumeric),
        Map.entry("descrição", Validator::isValidObs),
        Map.entry("designação", Validator::isValidObs),
        Map.entry("semanas", Validator::isValidIntegerNumeric),
        Map.entry("dias", Validator::isValidIntegerNumeric),
        Map.entry("horas", Validator::isValidIntegerNumeric),
        Map.entry("minutos", Validator::isValidIntegerNumeric)
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
            else if(isValueUnique(type, input)) {
                check = true;
                System.out.println("Valid " + type + ".");
            }
            else {
                System.out.println(type + " já existe. Por favor intruduza outro.");
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

    private static boolean isLoginUnique(String login) {
        return Sistema.getInstance().getUtilizadores().stream().noneMatch(u -> u.getLogin().equalsIgnoreCase(login));
    }

    private static boolean isEmailUnique(String email) {
        return Sistema.getInstance().getUtilizadores().stream().noneMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    private static boolean isNifUnique(String nif) {
        return Sistema.getInstance().getUtilizadores().stream()
            .filter(u -> u instanceof Cliente || u instanceof Tecnicos)
            .noneMatch(u -> {
                if (u instanceof Cliente) {
                    return ((Cliente) u).getNIF().equals(nif);
                } else if (u instanceof Tecnicos) {
                    return ((Tecnicos) u).getNIF().equals(nif);
                }
                return false;
            });
    }

    private static boolean isPhoneNumberUnique(String phoneNumber) {
        return Sistema.getInstance().getUtilizadores().stream()
            .filter(u -> u instanceof Cliente || u instanceof Tecnicos)
            .noneMatch(u -> {
                if (u instanceof Cliente) {
                    return ((Cliente) u).getTelefone().equals(phoneNumber);
                } else if (u instanceof Tecnicos) {
                    return ((Tecnicos) u).getTelefone().equals(phoneNumber);
                }
                return false;
            });
        }

    public static boolean isCodigoEquipamentoUnico(String codigo) {
        return Sistema.getInstance().getEquipamentos().stream().noneMatch(e -> e.getCodigoInterno().equalsIgnoreCase(codigo));
    }

    public static boolean isValueUnique(String type, String value) {
        switch (type) {
            case "login":
                return isLoginUnique(value);
            case "email":
                return isEmailUnique(value);
            case "nif":
                return isNifUnique(value);
            case "telefone":
                return isPhoneNumberUnique(value);
            case "código interno":
                return isCodigoEquipamentoUnico(value);
            default:
                return true;
        }
    }

}