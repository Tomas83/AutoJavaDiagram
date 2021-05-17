
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.Test;

import Prendas.GuardaRopa;
import Prendas.Prenda;
import Prendas.Material.Trama;


public class AutoDiagramTest {


	@Test
	public void fullPackageDiagram() throws ClassNotFoundException
	{
		System.out.println(AutoDiagram.getFullPackageDiagram("Prendas"));
	}
	@Test
	public void getEnumConstants()
	{
		System.out.println(Trama.class.getEnumConstants()[0].toString());
	}
	@Test
	public void fullDiagram()
	{
		System.out.println(AutoDiagram.getClassDiagram(GuardaRopa.class));
	}
	@Test
	public void getAClassFields() throws ClassNotFoundException
	  {
		Field[] fields = Prenda.class.getDeclaredFields();
		System.out.println(fields.length);
		for (int i = 0; i < fields.length; i++)
		{
			System.out.println(AutoDiagram.getFieldData(fields[i]));
		}
	}
	
	@Test
	  public void getAClassMethods() throws ClassNotFoundException
	  {
		System.out.print("\n\n\n");
		Method[] methods = GuardaRopa.class.getDeclaredMethods();
		System.out.println(methods.length);
		for (int i = 0; i < methods.length; i++)
		{
			System.out.println(AutoDiagram.getMethodData(methods[i]));
		}
	  }
	@Test
	  public void getAClass() throws ClassNotFoundException
	  {
		List<String> classes = AutoDiagram.getAllClases("Prendas");
		for (int i = 0; i < classes.size(); i++)
		{
			String[] temp = classes.get(i).split("\\.");
			System.out.println(temp[temp.length-1]);
		}
	  }

}

