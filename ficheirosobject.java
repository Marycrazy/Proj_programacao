

import src.Main;
import src.Sistema;

public class ficheirosobject {
    public static void main(String[] args) {
        try {
            Sistema.getInstance().carregarDados();
            Sistema.getInstance().exibirDados();
            Main.pressEnterKey();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
