package model;

public class Oznaka {

	private Integer id;
	
	private String opis;

	@Override
	public String toString() {
		return "Oznaka [id=" + id + ", opis=" + opis + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
}
