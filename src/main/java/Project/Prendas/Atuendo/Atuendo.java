package Project.Prendas.Atuendo;

import java.util.Arrays;
import java.util.List;

import Project.Exceptions.TipoDeParteIncorrectaException;
import Project.Prendas.Prenda;
import Project.Prendas.TipoDePrenda;

public class Atuendo {
	private List<Prenda> prendas;
	
	public Atuendo(Prenda parteSuperior, Prenda parteInferior, Prenda calzado, Prenda accesorio) 
	{
		List<Prenda> prendasRecividad = Arrays.asList(parteSuperior,parteInferior,calzado,accesorio);
		checkearPrendas(prendasRecividad);
		this.prendas = prendasRecividad;
	}
	public Atuendo(List<Prenda> prendas)
	{
		checkearPrendas(prendas);
		this.prendas = prendas;
	}
	public Atuendo(Prenda[] prendas)
	{
		List<Prenda> prendasList = Arrays.asList(prendas);
		checkearPrendas(prendasList);
		this.prendas = prendasList;
	}
	/*
	private boolean checkearPrendas (List<Prenda> prendas)
	{
		return	chequearCantidadDePrenda(prendas, TipoDePrenda.PARTE_SUPERIOR) &&
				chequearCantidadDePrenda(prendas, TipoDePrenda.PARTE_INFERIOR) &&
				chequearCantidadDePrenda(prendas, TipoDePrenda.CALZADO) &&
				chequearCantidadDePrenda(prendas, TipoDePrenda.ACCESORIO);
	}
	private boolean chequearCantidadDePrenda(List<Prenda> prendas, TipoDePrenda tipoDePrenda)
	{
		return (int) prendas.stream().filter(p -> p.getTipoDePrenda() == tipoDePrenda).count() != 1;
	}*/
	
	private void checkearPrendas (List<Prenda> prendas)
	{
		chequearCantidadDePrenda(prendas,TipoDePrenda.PARTE_SUPERIOR, "parte superior");
		chequearCantidadDePrenda(prendas,TipoDePrenda.PARTE_INFERIOR, "parte inferior");
		chequearCantidadDePrenda(prendas,TipoDePrenda.CALZADO, "calzado");
		chequearCantidadDePrenda(prendas,TipoDePrenda.ACCESORIO, "accesorio");
	}
	
	private void chequearCantidadDePrenda(List<Prenda> prendas, TipoDePrenda tipoDePrenda, String tipoPrenda)
	{
		int prenda = (int) prendas.stream().filter(p -> p.getTipoDePrenda() == tipoDePrenda).count();
		if (prenda > 1)
			throw new TipoDeParteIncorrectaException("Hay demasiadas "+tipoPrenda);
		if (prenda < 1)
			throw new TipoDeParteIncorrectaException("Hay muy pocas "+tipoPrenda);
	}
	public List<Prenda> getPrendas() {
		return prendas;
	}
}
