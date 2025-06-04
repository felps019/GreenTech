package entities;

public class Logradouro {

	private String cep;
	private String logradouro;

	public Logradouro(String cep, String logradouro) {
		this.cep = cep;
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Override
	public String toString() {
		return logradouro + " - CEP: " + cep;
	}
}