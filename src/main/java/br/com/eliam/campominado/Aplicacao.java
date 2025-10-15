package br.com.eliam.campominado;

import br.com.eliam.campominado.modelo.Tabuleiro;
import br.com.eliam.campominado.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);

        new TabuleiroConsole(tabuleiro);
    }
}
