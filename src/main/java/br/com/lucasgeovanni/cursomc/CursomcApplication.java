package br.com.lucasgeovanni.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.lucasgeovanni.cursomc.domain.Categoria;
import br.com.lucasgeovanni.cursomc.domain.Cidade;
import br.com.lucasgeovanni.cursomc.domain.Cliente;
import br.com.lucasgeovanni.cursomc.domain.Endereco;
import br.com.lucasgeovanni.cursomc.domain.Estado;
import br.com.lucasgeovanni.cursomc.domain.Pagamento;
import br.com.lucasgeovanni.cursomc.domain.PagamentoComBoleto;
import br.com.lucasgeovanni.cursomc.domain.PagamentoComCartao;
import br.com.lucasgeovanni.cursomc.domain.Pedido;
import br.com.lucasgeovanni.cursomc.domain.Produto;
import br.com.lucasgeovanni.cursomc.domain.enums.EstadoPagamentoEnum;
import br.com.lucasgeovanni.cursomc.domain.enums.TipoClienteEnum;
import br.com.lucasgeovanni.cursomc.repositories.CategoriaRepository;
import br.com.lucasgeovanni.cursomc.repositories.CidadeRepository;
import br.com.lucasgeovanni.cursomc.repositories.ClienteRepository;
import br.com.lucasgeovanni.cursomc.repositories.EnderecoRepository;
import br.com.lucasgeovanni.cursomc.repositories.EstadoRepository;
import br.com.lucasgeovanni.cursomc.repositories.PagamentoRepository;
import br.com.lucasgeovanni.cursomc.repositories.PedidoRepository;
import br.com.lucasgeovanni.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository _categoriaRepository;
	@Autowired
	private ProdutoRepository _produtoRepository;
	@Autowired
	private EstadoRepository _estadoRepository;
	@Autowired
	private CidadeRepository _cidadeRepository;
	@Autowired
	private ClienteRepository _clienteRepository;
	@Autowired
	private EnderecoRepository _enderecoRepository;
	@Autowired
	private PedidoRepository _pedidoRepository;
	@Autowired
	private PagamentoRepository _pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		_categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		_produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		_estadoRepository.saveAll(Arrays.asList(est1, est2));
		_cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "37277468821", TipoClienteEnum.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("11 25151652", "11 949569944"));

		Endereco e1 = new Endereco(null, "Travessa Maria Antonieta Berny", "29", "CASA", "Vila", "08031565", cli1, c2);
		Endereco e2 = new Endereco(null, "Rua Diogo Oliver", "61", "CASA", "Vila", "08030510", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		_clienteRepository.saveAll(Arrays.asList(cli1));
		_enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("31/12/2017 10:10"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamentoEnum.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamentoEnum.PENDENTE, ped2,
				sdf.parse("31/12/2017 10:10"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		_pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		_pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

	}
}
