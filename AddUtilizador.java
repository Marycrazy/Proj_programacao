

import java.util.Random;

import src.Cliente;
import src.Sistema;
import src.Tecnicos;
import src.Utilizador;
import src.Validator;

public class AddUtilizador {
    public static void main(String[] args) {
        try {
            Random random = new Random();
            administrador();
            cliente(random);
            //3 tecnicos
            tecnicos(random);
            Sistema.getInstance().save();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }

    private static final String[] RUAS = {
        "Rua das Flores", "Avenida da Liberdade", "Travessa do Moinho",
        "Praça da República", "Rua do Comércio", "Estrada Nacional 1"
    };

    public static String generateNIF() {
        Random random = new Random();
        
        // Primeiro dígito - pode ser 1, 2, ou 3 (pessoas singulares) ou outros valores dependendo da entidade.
        int d1 = random.nextInt(3) + 1; // Gera 1, 2 ou 3
        int[] nif = new int[9];
        nif[0] = d1;

        // Gerar os próximos 7 dígitos aleatoriamente
        for (int i = 1; i <= 7; i++) {
            nif[i] = random.nextInt(10); // Gera de 0 a 9
        }

        // Calcular o dígito de controlo (D9)
        int soma = 0;
        for (int i = 0; i < 8; i++) {
            soma += nif[i] * (9 - i);
        }
        int resto = soma % 11;
        nif[8] = (resto < 2) ? 0 : (11 - resto);

        // Montar o NIF final
        StringBuilder nifString = new StringBuilder();
        for (int digit : nif) {
            nifString.append(digit);
        }

        return nifString.toString();
    }

    public static String generateTelemovel() {
        Random random = new Random();
        
        // Escolher um prefixo válido (91, 92, 93, 96)
        String[] prefixos = {"91", "92", "93", "96"};
        String prefixo = prefixos[random.nextInt(prefixos.length)];

        // Gerar os 7 dígitos seguintes aleatoriamente
        StringBuilder telefone = new StringBuilder(prefixo);
        for (int i = 0; i < 7; i++) {
            telefone.append(random.nextInt(10)); // Gera de 0 a 9
        }

        return telefone.toString();
    }

    public static void administrador() {
        Utilizador user = new Utilizador("admin", Validator.encryptPassword(Integer.toString(123456789)), "Admin", true, "admin@gmail.com", "administrador");
            Sistema.getInstance().adicionarutilizador(user);
            for (int i = 1; i <= 2; i++) {
                user = new Utilizador("admin" + i, Validator.encryptPassword(Integer.toString(123456789)), "Admin" + i, false, "admin" + i + "@gmail.com", "administrador");
                Sistema.getInstance().adicionarutilizador(user);
            }
    }

    public static void cliente(Random random) {
        //3 clientes
        for (int i = 1; i <= 3; i++) {
            Utilizador user = new Cliente("cliente" + i, Validator.encryptPassword(Integer.toString(123456789)), "Cliente" + i, false, "cliente" + i + "@gmail.com", "cliente" , generateNIF() , RUAS[random.nextInt(RUAS.length)], generateTelemovel());
            Sistema.getInstance().adicionarutilizador(user);
        }
    }

    public static void tecnicos(Random random) {
        for (int i = 1; i <= 3; i++) {
            Utilizador user = new Tecnicos("tecnico" + i, Validator.encryptPassword(Integer.toString(123456789)), "Tecnico" + i, false, "tecnico" + i + "@gmail.com", "tecnico", generateNIF(), RUAS[random.nextInt(RUAS.length)], generateTelemovel());
            Sistema.getInstance().adicionarutilizador(user);
        }
    }

}
