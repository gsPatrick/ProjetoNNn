package com.sistema.projeto;
import com.sistema.projeto.Diretorios;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //INSTANCIAS
        Usuario usuario = new Usuario(); //INSTANCIA DA CLASSE USUARIO
        Diretorios diretorios = new Diretorios(); // INSTANCIA DA CLASSE DIRETORIOS
        Scanner scanner = new Scanner (System.in);//SCANNER

        //VERIFICANDO A EXISTENCIA/CRIANDO A PASTA: CENTRAL & PASTA: USUARIOS
        Diretorios.criarPastaCentralEUsuarios(); // FUNÇÃO PARA CRIAR A PASTA: CENTRAL |&| PASTA : USUARIOS

        //VARIAVEIS DA CLASSE MAIN
        int escolhaMenuCadstroOuLogin = 0; //VARIVEL DA ESCOLHA DE CADASTRO OU LOGIN

        while (escolhaMenuCadstroOuLogin != 1 && escolhaMenuCadstroOuLogin != 2) {
            System.out.println("Deseja fazer o cadastro ou login?");
            System.out.println("1 - Cadastro");
            System.out.println("2 - Login");

            // VERFICA SE A ENTRADA É UM NUMERO
            if (scanner.hasNextInt()) {
                escolhaMenuCadstroOuLogin = scanner.nextInt();
            } else {
                scanner.nextLine();   // Limpar o buffer do Scanner em caso de entrada inválida
            }
        }

        if (escolhaMenuCadstroOuLogin == 1) {
            usuario.fazerCadastro();//FUNÇÃO PARA FAZER O CADASTRO
        } else if (escolhaMenuCadstroOuLogin == 2) {
            usuario.fazerLogin(); // FUNÇÃO PARA FAZER LOGIN
        }

        scanner.close();
    }

}