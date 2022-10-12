package model.entities;

import model.services.Conta;

import java.util.List;

public class Banco {

	private String nome;
	private List<Conta> contas;

	public Banco(String nome, List<Conta> contas) {
		this.nome = nome;
		this.contas = contas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public String toString() {
		return "Banco = " + getNome() + " Cliente = " + getContas().get(0).getCliente().getNome() + " Agencia = "
				+ getContas().get(0).getAgencia() + " CPF = " + getContas().get(0).getCPF() + " Saldo = " +
				getContas().get(0).getSaldo();
	}
}
