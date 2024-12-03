package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;

    // Listas para armazenar usuários, serviços e equipamentos
    private List<Utilizador> utilizadores;
    /*private List<Servico> servicos;
    private List<Equipamento> equipamentos;*/
    private static Sistema sistema;

    public static Sistema getInstance() {
        if (sistema == null) {
            sistema = new Sistema();
        }
        return sistema;
    }

    public static void setInstance(Sistema sistemaCarregado) {
        sistema = sistemaCarregado;
    }

    // Construtor
    public Sistema() {
        this.utilizadores = new ArrayList<>();
        /*this.servicos = new ArrayList<>();
        this.equipamentos = new ArrayList<>();*/
    }

    public void save() {
        Ficheiros.insertUserFicheiro(sistema);
        Main.pressEnterKey();
        Ficheiros.insertObjectFicheiro(sistema);
        Main.pressEnterKey();
    }

    public void adicionarUsuario(Utilizador utilizador) {
        if (utilizador == null) {
            System.out.println("Tentativa de adicionar um utilizador nulo!");
            return;
        }
        this.utilizadores.add(utilizador);
        System.out.println("Usuário adicionado: " + utilizador.getLogin());
    }
    /*public void adicionarServico(Servico servico) {
        this.servicos.add(servico); Marycrazy 
    }

    public void adicionarEquipamento(Equipamento equipamento) {
        this.equipamentos.add(equipamento);
    }*/

    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void carregarDados() {
        Sistema sistemaCarregado = Ficheiros.carregarDadosSistema();
        if (sistemaCarregado != null) {
            Sistema.setInstance(sistemaCarregado);
        }
        else if (getInstance().getUtilizadores().isEmpty()) {
            System.out.println("Nenhum utilizador encontrado no sistema.");
        }
    }

    public boolean isLoginUnique(String login) {
        System.out.println("Verificando se o login " + login + " é único...");
        System.out.println("Utilizadores atuais: " + utilizadores.size());
        boolean isUnique = utilizadores.stream().noneMatch(u -> {
            System.out.println("Comparando com: " + u.getLogin());
            return u.getLogin().equalsIgnoreCase(login);
        });
        System.out.println("Login " + login + " é único? " + isUnique);
        return isUnique;
    }

    public boolean isEmailUnique(String email) {
        return utilizadores.stream().noneMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public boolean isNifUnique(String nif) {
        return utilizadores.stream()
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

    public boolean isPhoneNumberUnique(String phoneNumber) {
        return utilizadores.stream()
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


    public static boolean isValueUnique(String type, String value) {
        switch (type) {
            case "login":
                return sistema.isLoginUnique(value);
            case "email":
                return sistema.isEmailUnique(value);
            case "nif":
                return sistema.isNifUnique(value);
            case "telefone":
                return sistema.isPhoneNumberUnique(value);
            default:
                return true;
        }
    }

//eliminar no final esta função
    public void exibirDados() {
        System.out.println("Utilizadores cadastrados:");
        for (Utilizador utilizador : utilizadores) {
            System.out.println("Login: " + utilizador.getLogin() +
                               ", Nome: " + utilizador.getNome() +
                               ", Email: " + utilizador.getEmail() +
                               ", Tipo: " + utilizador.getTipo());
            if (utilizador instanceof Tecnicos) {
                Tecnicos tecnico = (Tecnicos) utilizador;
                System.out.println("Nif: " + tecnico.getNIF());
                System.out.println("Morada: " + tecnico.getMorada());
                System.out.println("Telefone: " + tecnico.getTelefone());
            }
            else if (utilizador instanceof Cliente) {
                Cliente clien = (Cliente) utilizador;
                System.out.println("Nif: " + clien.getNIF());
                System.out.println("Morada: " + clien.getMorada());
                System.out.println("Telefone: " + clien.getTelefone());
            }
        }
    }

}
