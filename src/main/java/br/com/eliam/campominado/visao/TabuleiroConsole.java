package br.com.eliam.campominado.visao;

import br.com.eliam.campominado.excesao.ExplosaoException;
import br.com.eliam.campominado.excesao.SairException;
import br.com.eliam.campominado.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;

            while (continuar) {
                cicloJogo();

                System.out.println("Outra partida? (S/n) ");
                String resposta = entrada.nextLine();

                if ("n".equalsIgnoreCase(resposta)){
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }
            }
        } catch (SairException e) {
            System.out.println("Até mais!");
        } finally {
            entrada.close();
        }
    }

    private void cicloJogo() {
        try {
            while (!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro);

                String digitado;
                Iterator<Integer> xy = null;

                // === Captura e valida as coordenadas ===
                while (true) {
                    digitado = capturarValorDigitado("Digite (x,y): ").trim();

                    // Verifica se há vírgula e dois valores
                    if (!digitado.contains(",")) {
                        System.out.println("Formato inválido! Use o formato x,y (ex: 2,3).");
                        continue;
                    }

                    String[] partes = digitado.split(",");

                    if (partes.length != 2) {
                        System.out.println("Você deve digitar dois valores separados por vírgula (ex: 2,3).");
                        continue;
                    }

                    try {
                        int x = Integer.parseInt(partes[0].trim());
                        int y = Integer.parseInt(partes[1].trim());

                        // Se chegou aqui, é válido
                        xy = Arrays.asList(x, y).iterator();
                        break;

                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite apenas números separados por vírgula (ex: 2,3).");
                    }
                }

                // === Captura e valida a ação ===
                String acao;
                while (true) {
                    acao = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ").trim();

                    if ("1".equals(acao) || "2".equals(acao)) {
                        break;
                    } else {
                        System.out.println("Opção inválida! Digite apenas 1 para Abrir ou 2 para (Des)Marcar.");
                    }
                }

                int x = xy.next();
                int y = xy.next();

                if ("1".equals(acao)) {
                    tabuleiro.abrir(x, y);
                } else {
                    tabuleiro.alternarMarcacao(x, y);
                }
            }

            System.out.println(tabuleiro);
            System.out.println("Você ganhou! Parabéns!");
        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Você perdeu! Tente novamente.");
        }
    }

    private String capturarValorDigitado(String texto) {
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if("sair".equalsIgnoreCase(digitado)) {
            throw new SairException();
        }
        return digitado;
    }

}
