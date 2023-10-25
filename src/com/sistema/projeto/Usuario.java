package com.sistema.projeto;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
public class Usuario {

    //SCANNER
    Scanner scanner = new Scanner(System.in);


    //VARIAVEL DE PASTA USUARIO
    private String nomePastaUsuario;

    //VARIAVEIS PRINCIPAIS
    private String nome;
    private String apelido;
    private String email;
    private String senha;

    //VARIAVEIS SOBRE SAUDE

    private String sexo;
    private float altura;
    private float peso;
    private int idade;
    private float gordura;
    private int taxaMetabolicaBasal;

    // VARIAVEIS SOBRE SAUDE COM MAIS DE UMA RESPOSTA
    private int atividade;

    //METODO GET

    public String getNomePastaUsuario() {
        return nomePastaUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getSexo() {
        return sexo;
    }

    public float getAltura() {
        return altura;
    }

    public float getPeso() {
        return peso;
    }

    public int getIdade() {
        return idade;
    }

    public float getGordura() {
        return gordura;
    }

    public int getTaxaMetabolicaBasal() {
        return taxaMetabolicaBasal;
    }

    public int getAtividade() {
        return atividade;
    }

    //METODO SETT

    public void setNomePastaUsuario(String nomePastaUsuario) {
        this.nomePastaUsuario = nomePastaUsuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setGordura(float gordura) {
        this.gordura = gordura;
    }

    public void setTaxaMetabolicaBasal(int taxaMetabolicaBasal) {
        this.taxaMetabolicaBasal = taxaMetabolicaBasal;
    }

    public void setAtividade(int atividade) {
        this.atividade = atividade;
    }

    //CADASTRO
    public void fazerCadastro() {

        System.out.println("Bem-vindo ao cadastro de usuário");

        while (true) {
            System.out.println("Qual é o seu nome?");
            String nomeUsuario = scanner.nextLine().trim(); // Remove espaços em branco no início e no fim

            // Verifica se o nome não está vazio
            if (!nomeUsuario.isEmpty()) {
                setNome(nomeUsuario);

                // Verifica se o nome de usuário já existe
                File pastaUsuario = Diretorios.obterPastaUsuarios();
                File arquivoLogin = new File(pastaUsuario, nomeUsuario + "/login.txt");

                if (arquivoLogin.exists()) {
                    System.out.println("Nome de usuário já existe. Por favor, escolha outro nome.");
                    continue; // Volte ao início do loop para fornecer um novo nome
                } else {
                    //CRIA UMA PASTA COM O NOME DO USUARIO
                    Diretorios.criarPastaUsuario(nomeUsuario);
                    this.nomePastaUsuario = nomeUsuario;
                    break; // Nome de usuário válido e único, saia do loop
                }
            } else {
                System.out.println("Nome inválido. O nome não pode estar vazio.");
            }
        }

        // Solicitar a senha após verificar que o nome de usuário é válido e único

        do {
            System.out.println("Escolha uma senha (Entre 6 a 8 caracteres):");
            String senhaUsuario = scanner.nextLine();

            // Verifica se a senha possui entre 6 e 8 dígitos numéricos
            if (senhaUsuario.matches("\\d{6,8}")) {
                setSenha(senhaUsuario);
                break;
            } else {
                System.out.println("Senha inválida. A senha deve conter de 6 a 8 dígitos numéricos.");
            }
        } while (true);

        do {
            System.out.println("Qual é o seu endereço de e-mail?");
            String emailUsuario = scanner.nextLine().trim();

            // Verifica se o e-mail é válido usando uma expressão regular simples
            if (emailUsuario.matches(".+@.+\\..+")) {
                setEmail(emailUsuario);
                break;
            } else {
                System.out.println("E-mail inválido. O e-mail deve ter um formato válido.");
            }
        } while (true);

        while (true) {
            System.out.println("Deseja ter um apelido? (Sim ou Não)");
            String escolhaApelido = scanner.nextLine().trim().toLowerCase();

            if (escolhaApelido.equals("sim") || escolhaApelido.equals("s")) {
                System.out.println("Digite seu apelido:");
                String apelidoUsuario = scanner.nextLine().trim();
                setApelido(apelidoUsuario);
                break; // Sai do loop se o apelido for fornecido
            } else if (escolhaApelido.equals("não") || escolhaApelido.equals("n") || escolhaApelido.equals("nao")) {
                // Use o nome como apelido padrão
                setApelido(getNome());
                break; // Sai do loop se o usuário não quiser fornecer um apelido
            } else {
                System.out.println("Resposta inválida. Por favor, digite 'sim' para Sim, ou 'não' para Não.");
            }
        }

        //FUNÇÃO PARA CRIAR O ARQUIVO: "LOGIN.TXT"
        Diretorios.CriarArquivoLogin(getNome(), getSenha(), getEmail(), getApelido());


        // LOGIN APOS CADASTRO
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Sair");

            if (scanner.hasNextInt()) {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 1) {
                    fazerLogin();
                    break; // Saia do loop se o usuário escolher fazer login
                } else if (opcao == 2) {
                    System.out.println("Uma abraço do seu amigo NutriNinja, até a próxima vez!");
                    break; // Saia do loop se o usuário escolher sair
                } else {
                    System.out.println("Opção inválida. Por favor, escolha 1 para fazer login ou 2 para sair.");
                }
            } else {
                scanner.nextLine(); // Limpe o buffer do scanner em caso de entrada inválida
            }

        }
    }

    //FUNÇÃO PARA VERIFICAÇÃO/VALIDAÇÃO DE LOGIN

    //VALIDAÇÃO DO NOME DO USUARIO
    private static boolean verificarNomeDeUsuario(File arquivoLogin, String nomeUsuario) {
        try (Scanner scanner = new Scanner(arquivoLogin)) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (linha.startsWith("Nome: ")) {
                    String nomeArmazenado = linha.substring(6); // Pule "Nome: "

                    // Verifique se o nome de usuário corresponde
                    if (nomeUsuario.equals(nomeArmazenado)) {
                        return true; // Nome de usuário válido
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false; // Nome de usuário não encontrado no arquivo
    }

    private static boolean VerificarSenhaDoUsuario(File arquivoLogin, String senhaUsuario) {

        try (Scanner scanner = new Scanner(arquivoLogin)) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (linha.startsWith("Senha: ")) {
                    String senhaArmazenada = linha.substring(7); // Pule "Senha: "

                    // Verifique se a senha corresponde
                    if (senhaUsuario.equals(senhaArmazenada)) {
                        return true; // Senha válida
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false; // Senha não encontrada no arquivo
    }

    //REALIZAR CALCULO

    public void CalculadoraBasalCaculo(){
        System.out.println("Ola, seja bem-vindo a calculadora basal:");
        System.out.println("Vamos coletar algumas informações para seguir");


        do {
            System.out.println("Qual é o seu sexo?");


            String sexoUsuario = scanner.nextLine();

            if (sexoUsuario.equals("feminino") || sexoUsuario.equals("masculino")) {
                setSexo(sexoUsuario);
                break;
            } else {
                System.out.println("Digite um sexo válido");
            }

        } while (true);

        do {
            System.out.println("Qual é a sua altura?");
            float alturaUsuario;

            try {
                String alturaUsuarioStr = scanner.nextLine();
                alturaUsuario = Float.parseFloat(alturaUsuarioStr);

                if (alturaUsuario > 0) {
                    setAltura(alturaUsuario);
                    break;  // A altura é válida, saia do loop
                } else {
                    System.out.println("A altura deve ser maior que 0. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Certifique-se de digitar um número válido.");
            }
        } while (true);

        do {
            System.out.println("Qual é o seu peso?");
            float pesoUsuario;

            try {
                String pesoUsuarioString = scanner.nextLine();
                pesoUsuario = Float.parseFloat(pesoUsuarioString);

                if (pesoUsuario > 0) {
                    setPeso(pesoUsuario);
                    break;
                } else {
                    System.out.println("Peso inválido");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Certifique-se de digitar um número válido.");
            }
        } while (true);

        do {
            System.out.println("Qual é o seu idade?");
            int idadeUsuario;

            try {
                String pesoUsuarioString = scanner.nextLine();
                idadeUsuario = Integer.parseInt(pesoUsuarioString);

                if (idadeUsuario > 0) {
                    setIdade(idadeUsuario);
                    break;
                } else {
                    System.out.println("Peso inválido");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Certifique-se de digitar um número inteiro válido.");
            }
        } while (true);

        //VARIAVEL PARA SABER SE O USUARIO SABE SEU PERCENTUAL ED GORUDA
        boolean sabeLMB; // Declare a variável fora do loop

        // Calcula a TMB com base no sexo do usuário
        double tmb;

        if (getSexo().equals("feminino")) {
            tmb = (10 * getPeso() + 6.25 * getAltura() - 5 * getIdade()) - 151;
        } else {
            tmb = (10 * getPeso() + 6.25 * getAltura() - 5 * getIdade()) + 5;
        }

        // Define a taxa metabólica basal usando o método set
        setTaxaMetabolicaBasal((int) tmb);

        int taxaBasal = getTaxaMetabolicaBasal();


        double fatorAtividade;

        do {
            System.out.println("Escolha o seu nível de atividade física:");
            System.out.println("1 - Sedentário (pouca ou nenhuma atividade física)");
            System.out.println("2 - Levemente ativo (exercícios leves ou esporádicos)");
            System.out.println("3 - Moderadamente ativo (exercícios moderados ou regularmente)");
            System.out.println("4 - Muito ativo (exercícios intensos ou fisicamente exigentes)");

            int nivelAtividadeUsuario;

            try {
                nivelAtividadeUsuario = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                if (nivelAtividadeUsuario >= 1 && nivelAtividadeUsuario <= 4) {
                    setAtividade(nivelAtividadeUsuario); // Define o nível de atividade usando o método set

                    switch (nivelAtividadeUsuario) {
                        case 1:
                            fatorAtividade = 1.2;
                            break;
                        case 2:
                            fatorAtividade = 1.375;
                            break;
                        case 3:
                            fatorAtividade = 1.55;
                            break;
                        case 4:
                            fatorAtividade = 1.725;
                            break;
                        default:
                            fatorAtividade = 1.2; // Valor padrão para sedentário
                            break;
                    }
                    break;
                } else {
                    System.out.println("Opção inválida. Escolha um número de 1 a 4 para o nível de atividade.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Certifique-se de escolher um número válido.");
                scanner.nextLine(); // Limpe o buffer do scanner em caso de entrada inválida
            }
        } while (true);

        // Calcule a necessidade calórica diária total
        double necessidadeCaloricaDiaria = getTaxaMetabolicaBasal() * fatorAtividade;
        int necessidadeCaloricaDiariaInt = (int) necessidadeCaloricaDiaria; //CONVERTE O RESULTADO DE DOUBLE PARA INTEIRO

        System.out.println("Sua necessidade calórica diária é: " + necessidadeCaloricaDiariaInt);//INFORMAÇÃO SOBRE O BASAL DO USUARIO

        Diretorios.criarArquivoTaxaMetabolicaBasal(nomePastaUsuario, necessidadeCaloricaDiariaInt); // FUNÇÃO QUE CRIA O ARQUIVO TXT E ARMAZENA A TAXA METABOLICA BASAL

    }


    //FUNÇÃO PARA A CALCULADORA BASAL
    public void CalculadoraBasal() {
        File pastaUsuario = Diretorios.obterPastaUsuario(nomePastaUsuario);
        File arquivoTaxaMetabolicaBasal = new File(pastaUsuario, "Taxa Metabolica Basal.txt");

        if (arquivoTaxaMetabolicaBasal.exists()) {
            int escolha = 0;

            while (escolha != 3) { // Permite sair quando escolha for 3
                System.out.println("1 - Refazer o cálculo");
                System.out.println("2 - Ver a Taxa Metabolica Basal existente");
                System.out.println("3 - Voltar para o menu");
                System.out.println("4 - Sair");

                if (scanner.hasNextInt()) {
                    escolha = scanner.nextInt();
                    scanner.nextLine();  // Limpa o buffer

                    switch (escolha) {
                        case 1:
                            CalculadoraBasalCaculo();
                            break;
                        case 2:
                            // Leitura do arquivo e exibição da Taxa Metabolica Basal
                            try (Scanner fileScanner = new Scanner(arquivoTaxaMetabolicaBasal)) {
                                while (fileScanner.hasNextLine()) {
                                    String linha = fileScanner.nextLine();
                                    if (linha.startsWith("Taxa Metabólica Basal: ")) {
                                        String valor = linha.substring(22); // Obtém o valor da linha
                                        int taxaMetabolicaBasal = Integer.parseInt(valor.trim());
                                        System.out.println("Sua Taxa Metabólica Basal é: " + taxaMetabolicaBasal);
                                    }
                                }
                            } catch (FileNotFoundException e) {
                                System.out.println("Erro ao ler o arquivo de Taxa Metabolica Basal.");
                            }

                            voltarOuSairParaMenu();
                            break;
                        case 3:
                            // Chame a função para voltar ao menu
                            menu();
                            break;
                        case 4:
                            System.out.println("Saindo do NutriNinja.");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Opção inválida. Por favor, escolha 1, 2, 3 ou 4.");
                    }
                } else {
                    System.out.println("Opção inválida. Por favor, escolha 1, 2, 3 ou 4.");
                    scanner.next(); // Limpa a entrada inválida
                }
            }
        } else {
            // Se o arquivo não existe, prossiga com o cálculo normalmente
            scanner.nextLine();  // Limpa o buffer
            CalculadoraBasalCaculo();

            // Após concluir o cálculo, retorne ao menu
            menu();
        }
    }


    // FUTURAS FUNÇÕES:

    //FUNÇÃO PARA DIVISÃO DO MACRO NUTRIENTE

    //FUNÇÃO PARA CONVERSAR COM O ASSISTENTE PESSOAL : NUTRI-NINJA

    //FUNÇÃO DO MENU
    public void menu() {
        System.out.println("Menu do Nutri-Ninja");

        while (true) {
            //OPÇÕES
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Calculadora Basal");
            System.out.println("2 - Divisor de Macro Nutrientes");
            System.out.println("3 - Conversar com o seu assistente: Nutri-Ninja");
            System.out.println("4 - Sair");

            if (scanner.hasNextInt()) {
                int opcaoMenuPrincipal = scanner.nextInt();

                if (opcaoMenuPrincipal == 1) {
                    CalculadoraBasal(); //FUNÇÃO PARA FAZER O CALCULO
                    break; // Saia do loop se escolher essa opção
                } else if (opcaoMenuPrincipal == 2) {
                    // Faça a divisão
                    break; // Saia do loop se escolher essa opção
                } else if (opcaoMenuPrincipal == 3) {
                    // Conversar com o nutri ninja
                } else if (opcaoMenuPrincipal == 4) {
                    break;
                } else {
                    System.out.println("Opção inválida. Por favor, escolha entre 1 a 4.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                scanner.next(); // Limpe a entrada inválida
            }
        }
    }

    //FUNÇAÕ PARA FAZER O LOGIN
    public void fazerLogin() {
        System.out.println("Seja bem-vindo");

        while (true) {
            // NOME
            System.out.println("Digite o seu nome:");
            String nomeUsuario = scanner.nextLine().trim();
            this.nomePastaUsuario = nomeUsuario; // Atribui o nome da pasta do usuário à variável

            // Validação do nome
            File pastaUsuario = Diretorios.obterPastaUsuarios();
            File arquivoLogin = new File(pastaUsuario, nomeUsuario + "/login.txt");

            if (arquivoLogin.exists()) {
                // SE O ARQUIVO EXISTIR, CONTINUE COM A VALIDAÇÃO DA SENHA
                System.out.println("Digite a sua senha:");
                String senha = scanner.nextLine().trim();

                // Validação da senha
                if (VerificarSenhaDoUsuario(arquivoLogin, senha)) {
                    System.out.println("Login bem-sucedido! Bem-vindo, " + nomeUsuario);
                    menu(); //FUNÇÃO PARA EXIBIR O MENU
                    break; // Login bem-sucedido, saia do loop
                } else {
                    System.out.println("Senha incorreta. Tente novamente.");
                }
            } else {
                System.out.println("Nome de usuário não encontrado. Tente novamente.");
            }
        }

    }

    //FUNÇÕES PARA VOLTAR E SAIR

    //public void voltarOuSairParaMenu // Da calculadora basal para menu

    public void voltarOuSairParaMenu() {
        System.out.println("1 - Voltar");
        System.out.println("2 - Sair");

        int escolhaSairOuVoltarParaMenu = scanner.nextInt();

        if (escolhaSairOuVoltarParaMenu == 3) {
            System.out.println("Voltando...");
            menu();

        } else if (escolhaSairOuVoltarParaMenu == 4) {
            System.out.println("Saindo do NutriNinja");
            System.exit(0); // Encerra o programa
        }

    }

    public void voltarOuSairParaMenuDaCalculadoraBasal() {
        int escolhaSairOuVoltarParaMenuDaCalculadoraBasal = 0;

        do {
            System.out.println("1 - Voltar");
            System.out.println("2 - Sair");

            if (scanner.hasNextInt()) {
                escolhaSairOuVoltarParaMenuDaCalculadoraBasal = scanner.nextInt();

                if (escolhaSairOuVoltarParaMenuDaCalculadoraBasal == 1) {
                    System.out.println("Voltando...");
                    CalculadoraBasal();
                } else if (escolhaSairOuVoltarParaMenuDaCalculadoraBasal == 2) {
                    System.out.println("Saindo do NutriNinja");
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Por favor, escolha 1 para Voltar ou 2 para Sair.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, digite 1 para Voltar ou 2 para Sair.");
                scanner.next(); // Limpa a entrada inválida
            }
        } while (escolhaSairOuVoltarParaMenuDaCalculadoraBasal != 1 && escolhaSairOuVoltarParaMenuDaCalculadoraBasal != 2);
    }
}