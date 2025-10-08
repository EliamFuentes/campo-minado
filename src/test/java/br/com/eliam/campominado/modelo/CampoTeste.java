package br.com.eliam.campominado.modelo;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CampoTeste {

    private Campo campo = new Campo(3, 3);

    @Test
    public void testeVizinhoRealDistancia1 () {
        Campo vizinho = new Campo(3, 2);

        boolean resultado = campo.adicionarVizinho(vizinho);

        assertTrue(resultado);
    }
}
