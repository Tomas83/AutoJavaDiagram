package Project.Prendas;

import java.util.Objects;

import Project.Prendas.Material.Material;

public class PrendaBuilder {

	private TipoDePrenda tipoDePrenda;
	private Material material;
	private Color colorPrincipal;
	private Color colorSecundario = null;
	private int temperaturaIdeal;
	
	public TipoDePrenda getTipoDePrenda() {
		return tipoDePrenda;
	}
	public Material getMaterial() {
		return material;
	}
	public Color getColorPrincipal() {
		return colorPrincipal;
	}
	public Color getColorSecundario() {
		return colorSecundario;
	}
	
	
	public PrendaBuilder setTipoDePrenda(TipoDePrenda tipoDePrenda) {
		this.tipoDePrenda = tipoDePrenda;
		return this;
	}
	public PrendaBuilder setMaterial(Material material) {
		this.material = material;
		return this;
	}
	public PrendaBuilder setColorPrincipal(Color colorPrincipal) {
		this.colorPrincipal = colorPrincipal;
		return this;
	}
	public PrendaBuilder setColorSecundario(Color colorSecundario) {
		this.colorSecundario = colorSecundario;
		return this;
	}
	public Prenda buildPrenda()
	{
		return new Prenda(
				Objects.requireNonNull(tipoDePrenda,"Falta tipo de prenda"),
				Objects.requireNonNull(material, "Falta material"),
				Objects.requireNonNull(colorPrincipal, "Falta color principal"),
				colorSecundario,
				Objects.requireNonNull(temperaturaIdeal,"Falta la temperatura de uso")
				);
	}
	public PrendaBuilder setTemperaturaIdeal(int temperaturaIdeal) {
		this.temperaturaIdeal = temperaturaIdeal;
		return this;
	}
	
}
