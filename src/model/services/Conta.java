package model.services;

import model.entities.Cliente;
import model.entities.IConta;

public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected String cpf;
	protected String saldo;
	protected Cliente cliente;
	protected Double saldo2;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	public Conta(int agencia, String cpf, String saldo, Cliente cliente) {
		this.agencia = agencia;
		this.cpf = cpf;
		this.saldo = saldo;
		this.cliente = cliente;
		saldo2 = Double.parseDouble(saldo);
	}


	@Override
	public void sacar(double valor) {
		saldo2 -= valor;
		this.saldo = String.format("%.2f",saldo2).replace(",", ".");
	}

	@Override
	public void depositar(double valor) {
		saldo2 += valor;
		this.saldo = String.format("%.2f",saldo2).replace(",", ".");
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	public int getAgencia() {
		return agencia;
	}

	public String getCPF() {return cpf;}

	public String getSaldo() {
		return saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Double getSaldo2() {
		return saldo2;
	}

	public void setSaldo2(Double saldo2) {
		this.saldo2 = saldo2;
	}

	protected void imprimirInfosComuns() {
		System.out.print("Titular: " + this.cliente.getNome());
		System.out.print("Agencia: " + this.agencia);
		System.out.print("Numero: " + this.numero);
		System.out.print("Saldo: " + this.saldo);
	}

}
