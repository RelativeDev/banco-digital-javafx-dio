package model.services;

import model.entities.Cliente;
import model.services.Conta;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(Cliente cliente) {
		super(cliente);
	}

	public ContaPoupanca(int agencia, String cpf, String saldo, Cliente cliente) {
		super(agencia, cpf, saldo, cliente);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato model.services.Conta Poupança ===");
		super.imprimirInfosComuns();
	}
}
