import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

class Produto {
  private int codigo;
  private String descricao;
  private String fornecedor;
  private double valor;
  private int quantidade;
  private String unidade;
  private double frete;

  public Produto(int codigo, String descricao, String fornecedor, double valor, int quantidade, String unidade, double frete) {
    this.codigo = codigo;
    this.descricao = descricao;
    this.fornecedor = fornecedor;
    this.valor = valor;
    this.quantidade = quantidade;
    this.unidade = unidade;
    this.frete = frete;
  }

  public int getCodigo() {
    return this.codigo;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public String getFornecedor() {
    return this.fornecedor;
  }

  public double getValor() {
    return this.valor;
  }

  public int getQuantidade() {
    return this.quantidade;
  }

  public String getUnidade() {
    return this.unidade;
  }

  public double getFrete() {
    return this.frete;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public void setFornecedor(String fornecedor) {
    this.fornecedor = fornecedor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public void setUnidade(String unidade) {
    this.unidade = unidade;
  }

  public void setFrete(double frete) {
    this.frete = frete;
  }

  public String toString() {
    return this.codigo + "\t" + this.descricao + "\t" + this.fornecedor + "\t" + this.valor + "\t" + this.quantidade + "\t" + this.unidade + "\t" + this.frete + "\n";
  }
}

class AgendaMain {
  // variáveis globais
  static StringBuffer memoria = new StringBuffer();
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    char opcao, resp = 'N';
    do {
      opcao = menu();
      switch (opcao) {
        case '1':
          inserirDados();
          break;
        case '2':
          alterarDados();
          break;
        case '3':
          excluirDados();
          break;
        case '4':
          consultarDados();
          break;
        case '5':
          System.out.println("Deseja realmente sair do programa? S/N");
          resp = Character.toUpperCase(scan.next().charAt(0));
          break;
        default:
          System.out.println("Opção inválida, tente novamente.");
      }
    } while (resp != 'S');
    System.exit(0);
  }

  static char menu() {
    System.out.println("\n\nEscolha uma Opção:\n" + "1. Inserir novo produto\n" + "2. Alterar produto\n"
        + "3. Excluir um produto\n" + "4. Pesquisar produto\n" + "5. Sair");
    return scan.next().charAt(0);
  }

  static void iniciarArquivo() {
    try {
      BufferedReader arquivoEntrada;
      arquivoEntrada = new BufferedReader(new FileReader("ProjetoFinal.txt"));
      String linha = "";
      memoria.delete(0, memoria.length());// apaga tudo que está na variável memoria
      do {
        linha = arquivoEntrada.readLine();
        if (linha != null) {
          memoria.append(linha + "\n");
        }
      } while (linha != null);
      arquivoEntrada.close();
    } catch (FileNotFoundException erro) {
      System.out.println("\nArquivo não encontrado");
    } catch (IOException e) {
      System.out.println("\nErro de Leitura!");
    }
  }

  public static void gravarDados() {
    try {
      BufferedWriter arquivoSaida;
      arquivoSaida = new BufferedWriter(new FileWriter("ProjetoFinal.txt"));
      arquivoSaida.write(memoria.toString());
      arquivoSaida.flush();
      arquivoSaida.close();
    } catch (IOException e) {
      System.out.println("\nErro de gravação!");
    }
  }

  static void inserirDados() {
    int codigo, quantidade;
    double valor, frete;
    String descricao, fornecedor, unidade;

    try {
      System.out.println("Digite o código do produto:");
      codigo = scan.nextInt();
      System.out.println("Digite a descrição do produto:");
      descricao = scan.next();
      System.out.println("Digite o fornecedor:");
      fornecedor = scan.next();
      System.out.println("Digite o valor do produto:");
      valor = scan.nextDouble();
      System.out.println("Digite a quantidade:");
      quantidade = scan.nextInt();
      System.out.println("Digite a unidade:");
      unidade = scan.next();
      System.out.println("Digite o valor do frete:");
      frete = scan.nextDouble();

      Produto reg = new Produto(codigo, descricao, fornecedor, valor, quantidade, unidade, frete);
      memoria.append(reg.toString()); // inserir uma nova linha no final
      gravarDados(); // grava alteração no HD
    } catch (Exception e) {
      System.out.println("\nErro de gravação");
    }
  }

  public static void alterarDados() {
    String codigo, descricao, fornecedor, unidade;
    int inicio, fim, ultimo, primeiro, quantidade;
    double valor, frete;
    boolean achou = false;
    int procura;
    iniciarArquivo(); // atualizar a variavel memoria para iniciar a pesquisa
    if (memoria.length() != 0) { // não está vazia
      System.out.println("\nDigite o código para alteração:");
      procura = scan.nextInt();
      inicio = 0; // inicio começa na posição 0
      while ((inicio != memoria.length()) && (!achou)) {
        ultimo = memoria.indexOf("\t", inicio);
        codigo = memoria.substring(inicio, ultimo);
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        descricao = memoria.substring(primeiro, ultimo);
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        fornecedor = memoria.substring(primeiro, ultimo);
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        valor = Double.parseDouble(memoria.substring(primeiro, ultimo));
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        quantidade = Integer.parseInt(memoria.substring(primeiro, ultimo));
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        unidade = memoria.substring(primeiro, ultimo);
        primeiro = ultimo + 1;
        fim = memoria.indexOf("\n", primeiro);
        frete = Double.parseDouble(memoria.substring(primeiro, fim));

        Produto reg = new Produto(Integer.parseInt(codigo), descricao, fornecedor, valor, quantidade, unidade, frete);
        if (procura == reg.getCodigo()) {
          System.out.println("\nCódigo: " + reg.getCodigo() + "  descrição: " + reg.getDescricao() + "  fornecedor: "
              + reg.getFornecedor() + " valor: " + reg.getValor() + " quantidade: " + reg.getQuantidade() + " unidade: "
              + reg.getUnidade() + " frete: " + reg.getFrete());
          System.out.println("Entre com os novos dados:");
          System.out.println("Digite a descrição:");
          reg.setDescricao(scan.next());
          System.out.println("Digite o fornecedor:");
          reg.setFornecedor(scan.next());
          System.out.println("Digite o valor do produto:");
          reg.setValor(scan.nextDouble());
          System.out.println("Digite a quantidade:");
          reg.setQuantidade(scan.nextInt());
          System.out.println("Digite a unidade:");
          reg.setUnidade(scan.next());
          System.out.println("Digite o valor do frete:");
          reg.setFrete(scan.nextDouble());

          memoria.replace(inicio, fim + 1, reg.toString()); // alterar dados na memoria
          gravarDados(); // grava alteração no HD
          achou = true;
        }
        inicio = fim + 1; // continua procurando o código do produto
      }
      if (achou) {
        System.out.println("\nAlteração realizada com sucesso");
      } else {
        System.out.println("\nCódigo não encontrado");
      }
    } else {
      System.out.println("\nArquivo vazio");
    }
  }

  public static void excluirDados() {
    String codigo, descricao, fornecedor, unidade;
    int inicio, fim, ultimo, primeiro, quantidade;
    double valor, frete;
    boolean achou = false;
    char resp;
    iniciarArquivo(); // atualizar a variavel memoria para iniciar a pesquisa
    if (memoria.length() != 0) { // não está vazia
      System.out.println("\nDigite o código para exclusão:");
      int procura = scan.nextInt();
      inicio = 0; // inicio começa na posição 0 da variável memoria
      while ((inicio != memoria.length()) && (!achou)) {
        ultimo = memoria.indexOf("\t", inicio);
        codigo = memoria.substring(inicio, ultimo);
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        descricao = memoria.substring(primeiro, ultimo);
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        fornecedor = memoria.substring(primeiro, ultimo);
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        valor = Double.parseDouble(memoria.substring(primeiro, ultimo));
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        quantidade = Integer.parseInt(memoria.substring(primeiro, ultimo));
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        unidade = memoria.substring(primeiro, ultimo);
        primeiro = ultimo + 1;
        fim = memoria.indexOf("\n", primeiro);
        frete = Double.parseDouble(memoria.substring(primeiro, fim));

        Produto reg = new Produto(Integer.parseInt(codigo), descricao, fornecedor, valor, quantidade, unidade, frete);
        if (procura == reg.getCodigo()) {
          System.out.println("Deseja excluir?" + "\n" + "Digite S ou N" + "\n\n" + "Código: " + reg.getCodigo()
              + " descrição: " + reg.getDescricao() + " fornecedor: " + reg.getFornecedor() + " valor: " + reg.getValor()
              + " quantidade: " + reg.getQuantidade() + " unidade: " + reg.getUnidade() + " frete: " + reg.getFrete());
          resp = Character.toUpperCase(scan.next().charAt(0));
          if (resp == 'S') {
            memoria.delete(inicio, fim + 1);
            System.out.println("Registro excluído.");
            gravarDados();
          } else {
            System.out.println("Exclusão cancelada.");
          }
          achou = true;
        }
        inicio = fim + 1; // continua procurando o código do produto
      }
      if (!achou) {
        System.out.println("\nCódigo não encontrado");
      }
    } else {
      System.out.println("\nArquivo vazio");
    }
  }

  public static void consultarDados() {
    String codigo, descricao, fornecedor, unidade;
    int inicio, fim, ultimo, primeiro, quantidade;
    double valor, frete;
    boolean achou = false;
    int procura;
    iniciarArquivo(); // atualizar a variavel memoria para iniciar a pesquisa
    if (memoria.length() != 0) { // não está vazia
      System.out.println("\nDigite o código para pesquisar:");
      procura = scan.nextInt();
      inicio = 0;
      while ((inicio != memoria.length()) && (!achou)) {
        ultimo = memoria.indexOf("\t", inicio);
        codigo = memoria.substring(inicio, ultimo);
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        descricao = memoria.substring(primeiro, ultimo);
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        fornecedor = memoria.substring(primeiro, ultimo);
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        valor = Double.parseDouble(memoria.substring(primeiro, ultimo));
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        quantidade = Integer.parseInt(memoria.substring(primeiro, ultimo));
        primeiro = ultimo + 1;
        ultimo = memoria.indexOf("\t", primeiro);
        unidade = memoria.substring(primeiro, ultimo);
        primeiro = ultimo + 1;
        fim = memoria.indexOf("\n", primeiro);
        frete = Double.parseDouble(memoria.substring(primeiro, fim));

        Produto reg = new Produto(Integer.parseInt(codigo), descricao, fornecedor, valor, quantidade, unidade, frete);
        if (procura == reg.getCodigo()) {
          System.out.println("\nCódigo: " + reg.getCodigo() + "  descrição: " + reg.getDescricao() + "  fornecedor: "
              + reg.getFornecedor() + " valor: " + reg.getValor() + " quantidade: " + reg.getQuantidade() + " unidade: "
              + reg.getUnidade() + " frete: " + reg.getFrete());
          achou = true;
        }
        inicio = fim + 1; // continua procurando o código do produto
      }
      if (!achou) {
        System.out.println("\nCódigo não encontrado");
      }
    } else {
      System.out.println("\nArquivo vazio");
    }
  }
}