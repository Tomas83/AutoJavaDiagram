package Project.Prendas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Project.Api.WeatherApiStrategy;
import Project.Exceptions.insuficientesPrendasARangoDeTemperaturaExceptionextends;
import Project.Prendas.Atuendo.Atuendo;

public class GuardaRopa
{
	public List<Project.Prendas.Atuendo.Atuendo> sugerencias(List<Prenda> prendas,int temperatura, int rango)
	{
		return this.sugerencias(this.getPrendasATemperatura(prendas,temperatura,rango));
	}
	public List<Atuendo> sugerencias(List<Prenda> prendas, WeatherApiStrategy weatherApiStrategy,int rango)
	{
		return this.sugerencias(this.getPrendasATemperatura(prendas,(int)weatherApiStrategy.getTemp("Buenos Aires"),rango));
	}

	public List<Atuendo> sugerencias(List<Prenda> prendas)
	{
		if (prendas.size() <= 4)
			throw new insuficientesPrendasARangoDeTemperaturaExceptionextends("Faltan prendas");
		List<int[]> posiblesCombinaciones = this.generate((int) prendas.size(), 4);
		
		List<Prenda[]> combinacionesDePrenda = posiblesCombinaciones.stream().
												map(combinacion -> getMultiplesPrendasDeLista(prendas, combinacion)).
												collect(Collectors.toList());
		
		List<Atuendo> atuendos = new ArrayList<>();
		for (int i = 0; i < combinacionesDePrenda.size(); i++)
		{
			Atuendo atuendo;
			try
			{
				atuendo = new Atuendo(combinacionesDePrenda.get(i));
				atuendos.add(atuendo);
			}
			catch (Exception e)	{}
		}
		return atuendos;
	}
	
	public List<Prenda> getPrendasATemperatura(List<Prenda> prendas,int temperatura, int rango)
	{
		return  prendas.stream().filter(p -> p.getTemperaturaIdeal() > (temperatura -rango) && p.getTemperaturaIdeal() < (temperatura + rango)).collect(Collectors.toList());

	}
	
	
	private void helper(List<int[]> combinations, int data[], int start, int end, int index) {
	    if (index == data.length) {
	        int[] combination = data.clone();
	        combinations.add(combination);
	    } else if (start <= end) {
	        data[index] = start;
	        helper(combinations, data, start + 1, end, index + 1);
	        helper(combinations, data, start + 1, end, index);
	    }
	}
	public List<int[]> generate(int n, int r) {
	    List<int[]> combinations = new ArrayList<>();
	    helper(combinations, new int[r], 0, n-1, 0);
	    return combinations;
	}
	
	public  Prenda[] getMultiplesPrendasDeLista(List<Prenda> items,int[] itemsIndex)
	{
		Prenda[] rescuedItems = new Prenda[itemsIndex.length];
		
		for (int i = 0; i < itemsIndex.length; i++)
			rescuedItems[i] = items.get(itemsIndex[i]);
		
		return  rescuedItems;
	}
}
