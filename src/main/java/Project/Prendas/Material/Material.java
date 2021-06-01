package Project.Prendas.Material;

public class Material {
	private TipoDeMaterial tipoDeMaterial;
	private Trama trama;

	public Material(TipoDeMaterial tipoDeMaterial)
	{
		this.tipoDeMaterial = tipoDeMaterial;
		this.trama = Trama.LISO;
	}
	public Material(TipoDeMaterial tipoDeMaterial, Trama trama)
	{
		this.tipoDeMaterial = tipoDeMaterial;
		this.trama = trama;
	}
	public Trama getTrama() {
		return trama;
	}
	public TipoDeMaterial getTipoDeMaterial() {
		return tipoDeMaterial;
	}
	
}
