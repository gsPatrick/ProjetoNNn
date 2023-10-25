package com.sistema.projeto;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.sistema.projeto.Usuario;
public class Diretorios {

    //CAMINHO DINAMICA DA PASTA: CENTRAL
    private static final String PASTA_CENTRAL = "../PastaCentral"; // Aqui definimos uma constante com o nome de Pasta Central

    // CAMINHO DINAMICO DA PASTA: USUARIOS
    private static final String PASTA_USUARIOS = "Usuarios";

    public static File obterPastaCentral() {
        return new File(PASTA_CENTRAL);
    }

    public static File obterPastaUsuarios() {
        File pastaCentralDiretorio = obterPastaCentral();
        return new File(pastaCentralDiretorio, PASTA_USUARIOS);
    }

    public static boolean pastaCentralExiste() {
        File pastaCentralDiretorio = obterPastaCentral();
        return pastaCentralDiretorio.exists();
    }

    //FUNÇÃO PARA CRIAR A PASTA: CENTRAL | & | PASTA: USUARIOS
    public static boolean criarPastaCentralEUsuarios() {
        File pastaCentralDiretorio = obterPastaCentral();
        File pastaUsuariosDiretorio = obterPastaUsuarios();

        if (!pastaCentralExiste()) {
            try {
                if (pastaCentralDiretorio.mkdir()) {
                    return pastaUsuariosDiretorio.mkdir();
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    //FUNÇÃO PARA CRIAR A PASTA COM O NOME FORNECIDO PELO USUARIO
    public static boolean criarPastaUsuario(String nomePastaUsuario) {
        File pastaUsuariosDiretorio = obterPastaUsuarios();
        File pastaUsuario = new File(pastaUsuariosDiretorio, nomePastaUsuario);

        if (!pastaUsuario.exists()) {
            if (pastaUsuario.mkdir()) {
                // Remova esta linha para que o usuário não seja informado sobre a criação da pasta
                // System.out.println("Pasta do usuário criada com sucesso: " + nomePastaUsuario);
                return true;
            } else {
                System.out.println("Falha ao criar a pasta do usuário: " + nomePastaUsuario);
            }
        } else {
            System.out.println("O nome de usuário com o nome '" + nomePastaUsuario + "' já existe. Escolha um nome diferente.");
        }

        return false;
    }

    public static File obterPastaUsuario(String nomePastaUsuario) {
        File pastaUsuariosDiretorio = obterPastaUsuarios();
        return new File(pastaUsuariosDiretorio, nomePastaUsuario);
    }

    //FUNÇÃO PARA CRIAR O ARQUIVO "LOGIN.TXT" PARA ARMAZENAR : NOME,APELIDO,EMAIL E SENHA

    public static boolean CriarArquivoLogin(String nomePastaUsuario, String senha, String email, String apelido) {
        File pastaUsuariosDiretorio = obterPastaUsuarios();
        File pastaUsuario = new File(pastaUsuariosDiretorio, nomePastaUsuario);

        // Verifica se a pasta do usuário já existe
        if (pastaUsuario.exists()) {
            // Cria o arquivo "login.txt" dentro da pasta do usuário
            File arquivoLogin = new File(pastaUsuario, "login.txt");

            // Escreve as informações de nome, senha, email e apelido no arquivo "login.txt"
            try {
                FileWriter writer = new FileWriter(arquivoLogin);
                writer.write("Nome: " + nomePastaUsuario + "\n");
                writer.write("Senha: " + senha + "\n");
                writer.write("Email: " + email + "\n");
                writer.write("Apelido: " + apelido + "\n");
                writer.close();
                return true;
            } catch (IOException e) {
                System.out.println("Falha ao criar o arquivo 'login.txt'.");
            }
        } else {
            System.out.println("A pasta do usuário com o nome '" + nomePastaUsuario + "' não existe. Crie a pasta do usuário antes de criar o arquivo de login.");
        }
        return false;
    }

    //FUNÇÃO PARA CRIAR O ARQUIVO TXT E ARMAZENAR

    public static void criarArquivoTaxaMetabolicaBasal(String nomePastaUsuario, int necessidadeCaloricaDiariaInt) {
        File pastaUsuario = Diretorios.obterPastaUsuario(nomePastaUsuario);

        if (!pastaUsuario.exists()) {
            System.out.println("A pasta do usuário não existe. Certifique-se de criar a pasta do usuário primeiro.");
            return;
        }

        // Agora você pode criar o arquivo "Taxa Metabolica Basal" na pasta do usuário
        File arquivoTaxaMetabolicaBasal = new File(pastaUsuario,"Taxa Metabolica Basal.txt");

        try {
            FileWriter writer = new FileWriter(arquivoTaxaMetabolicaBasal);
            writer.write("Taxa Metabólica Basal: " + necessidadeCaloricaDiariaInt + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Falha ao criar o arquivo 'Taxa Metabolica Basal'.");
        }
    }

}