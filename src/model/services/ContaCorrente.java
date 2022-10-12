package model.services;

import model.entities.Cliente;
import model.services.Conta;

public class ContaCorrente extends Conta {

	public ContaCorrente(int agencia, String cpf, String saldo, Cliente cliente) {
		super(agencia, cpf, saldo, cliente);
	}

	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}


	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato model.services.Conta Corrente ===");
		super.imprimirInfosComuns();
	}
	
}
