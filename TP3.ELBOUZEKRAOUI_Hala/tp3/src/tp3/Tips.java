package tp3;

public class Tips {
	int bill;
	int tips;
	int Nombre_Personne;

	Tips (int bill, int tips, int Nombre_Personne) {
		this.bill= bill;
		this.tips = tips;
		this.Nombre_Personne = Nombre_Personne;
	}
	public Integer getBill() {
		return bill;
	}
	public void setBill ( Integer bill) {
		this.bill =bill ;
	}
	
	public Integer getTips() {
		return tips;
	}

	public void setTips ( Integer tips) {
		this.tips =tips;
	}
	
	public Integer getNbPrs() {
		return Nombre_Personne;
	}

	public void setNbPrs ( Integer Nombre_Personne) {
		this.Nombre_Personne =Nombre_Personne;
	}
}
