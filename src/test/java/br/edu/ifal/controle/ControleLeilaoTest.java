package br.edu.ifal.controle;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifal.modelo.Cliente;
import br.edu.ifal.modelo.Lance;
import br.edu.ifal.modelo.Leilao;
import br.edu.ifal.modelo.Produto;

public class ControleLeilaoTest {

    Produto produto;
    double valorMinimo;
    Leilao leilao;
    Cliente cliente;
    Cliente cliente2;
    ControleLeilao controle;

    @Before
    public void inicializacao(){
        produto = new Produto("PS4", "video-game");
        valorMinimo = 2500;
        leilao = new Leilao(produto, valorMinimo);
        cliente = new Cliente(1, "Ana");
        cliente2 = new Cliente(2, "Zé");
        controle = new ControleLeilao();
    }


    @Test
    public void deveRetornarVerdadeiroParaUmLanceMaiorQueOValorMinimoDoProduto() {
        Lance novoLance = new Lance(cliente, 2501);
        boolean validadeRetornada = controle.validarLance(novoLance, leilao);
        boolean validadeEsperada = true;
        assertEquals(validadeEsperada, validadeRetornada);
    }
    @Test
    public void deveRetornarFalsoParaUmLanceMenorQueOValorMinimoDoProduto() {
        Lance novoLance = new Lance(cliente, 2000);
        boolean validadeRetornada = controle.validarLance(novoLance, leilao);
        boolean validadeEsperada = false;
        assertEquals(validadeEsperada, validadeRetornada);
    }
    @Test
    public void deveRetornarVerdadeiroParaUmLanceIgualAoValorMinimoDoProduto() {
        Lance novoLance = new Lance(cliente, 2500);
        boolean validadeRetornada = controle.validarLance(novoLance, leilao);
        boolean validadeEsperada = true;
        assertEquals(validadeEsperada, validadeRetornada);
    }

    @Test
    public void deveRetornarFalsoParaUmLanceDadoPeloMesmoClienteDoUltimoLance(){
        leilao.adicionarLance(new Lance(cliente, 2600));
        Lance novoLance = new Lance(cliente, 2700);
        boolean validadeRetornada = controle.validarLance(novoLance, leilao);
        boolean validadeEsperada = false;
        assertEquals(validadeEsperada, validadeRetornada);
    }

    @Test
    public void deveRetornarVerdadeiroParaUmLanceDadoPorUmClienteDiferenteDoClienteDoUltimoLance(){
        leilao.adicionarLance(new Lance(cliente, 2600));
        Lance novoLance = new Lance(cliente2, 2700);
        boolean validadeRetornada = controle.validarLance(novoLance, leilao);
        boolean validadeEsperada = true;
        assertEquals(validadeEsperada, validadeRetornada);
    }
    @Test
    public void deveRetornarVerdadeiroParaOPrimeiroLanceDoLeilaoMaiorQueOValorMinimo() {
        Lance novoLance = new Lance(cliente, 2700);
        boolean validadeRetornada = controle.validarLance(novoLance, leilao);
        boolean validadeEsperada = true;
        assertEquals(validadeEsperada, validadeRetornada);
    }
    @Test
    public void deveRetornarFalsoParaUmNovoLanceMenorQueOLanceAneterior() {
        leilao.adicionarLance(new Lance(cliente, 2600));
        Lance novoLance = new Lance(cliente, 2500);
        boolean validadeRetornada = controle.validarLance(novoLance, leilao);
        boolean validadeEsperada = false;
        assertEquals(validadeEsperada, validadeRetornada);
    }
    @Test
    public void deveRetornarverdadeiroParaUmNovoLanceMaiorQueOLanceAneterior() {
        leilao.adicionarLance(new Lance(cliente, 4600));
        Lance novoLance = new Lance(cliente2, 6700);
        boolean validadeRetornada = controle.validarLance(novoLance, leilao);
        boolean validadeEsperada = true;
        assertEquals(validadeEsperada, validadeRetornada);
    }
    @Test
    public void deveRetornarFalsoSeOValorForIgualAoAnterior() {
        leilao.adicionarLance(new Lance(cliente, 2600));
        Lance novoLance = new Lance(cliente, 2600);
        boolean validadeRetornada = controle.validarLance(novoLance, leilao);
        boolean validadeEsperada = false;
        assertEquals(validadeEsperada, validadeRetornada);
    }
    

}