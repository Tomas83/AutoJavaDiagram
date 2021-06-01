
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.Test;

import Project.*;
import Project.Prendas.GuardaRopa;
import Project.Prendas.Prenda;
import Project.Prendas.Material.Trama;

public class AutoDiagramTest {
	
	@Test
	public void fullPackageDiagram() throws ClassNotFoundException
	{
		AutoDiagram autoDiagram = new AutoDiagram("Project");
		System.out.println(autoDiagram.getFullPackageDiagram());
	}
	/*

	@Test
	public void fullPackageDiagram() throws ClassNotFoundException
	{
		System.out.println(AutoDiagram.getFullPackageDiagram("Project"));
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
		List<String> classes = AutoDiagram.getAllClases("Project.Prendas");
		for (int i = 0; i < classes.size(); i++)
		{
			String[] temp = classes.get(i).split("\\.");
			System.out.println(temp[temp.length-1]);
		}
	  }

	@Test
	public void WritePackageDiagram() throws ClassNotFoundException, IOException
	{
		AutoDiagram.getFullPackageDiagramInFile("AutoDiagram.txt", "Project");
		//System.out.println(AutoDiagram.getFullPackageDiagram("Project"));
	}*/
}

