package Project.Prendas;

import java.util.Objects;

import Project.Prendas.Material.Material;


public class Prenda {
	private TipoDePrenda tipoDePrenda;
	private Material material;
	private Color colorPrincipal;
	private Color colorSecundario;
	private int temperaturaIdeal;

	public Prenda (TipoDePrenda tipoDePrenda, Material material, Color colorPrincipal, int temperaturaIdeal)
	{
		this.tipoDePrenda = Objects.requireNonNull(tipoDePrenda,"Falta tipo de prenda");
		this.material = Objects.requireNonNull(material,"Falta material");
		this.colorPrincipal = Objects.requireNonNull(colorPrincipal,"Falta color Principal");
		this.colorSecundario = null;
		this.temperaturaIdeal = temperaturaIdeal;
	}
	public Prenda (TipoDePrenda tipoDePrenda, Material material, Color colorPrincipal, Color colorSecundario, int temperaturaIdeal)
	{
		this.tipoDePrenda = Objects.requireNonNull(tipoDePrenda,"Falta tipo de prenda");
		this.material = Objects.requireNonNull(material,"Falta material");
		this.colorPrincipal = Objects.requireNonNull(colorPrincipal,"Falta color Principal");
		this.colorSecundario = colorSecundario;
		this.temperaturaIdeal = temperaturaIdeal;
	}
	
	public boolean hayColorSecundario() {	return this.colorSecundario != null;	}
	
	public TipoDePrenda getTipoDePrenda() {
		return tipoDePrenda;
	}
	public Color getColorPrincipal() {
		return colorPrincipal;
	}
	public Material getMaterial() {
		return material;
	}
	public int getTemperaturaIdeal() {
		return temperaturaIdeal;
	}
	
	
}
