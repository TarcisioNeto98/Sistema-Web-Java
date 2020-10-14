package model;

public class Funcionario {
	
	private int id;
	private String nome;
	private String cargo;
	private String dataAniversario;
	private String dataEntrada;
	
	public Funcionario(int id, String nome, String cargo, String dataAniversario, String dataEntrada) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
		this.dataAniversario = dataAniversario;
		this.dataEntrada = dataEntrada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(String dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cargo=" + cargo + ", dataAniversario=" + dataAniversario
				+ ", dataEntrada=" + dataEntrada;
	}
	
}
