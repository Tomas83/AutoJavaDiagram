import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import Project.AutoDiagram;

public class GeneralTests {

	List<List<String>> lista;
	List<String> lista2;
	HashMap<String, List<String>> hashMap;
	String test;


	@Test
	public void split()
	{
		String Str = "Palabra";
		String[] words = Str.split("[^a-zA-Z0-9']+");
		System.out.print(words.length);
	}
	@Test
	public void tryCollectionClass() throws NoSuchFieldException, SecurityException
	{
		Class<GeneralTests> gen = GeneralTests.class;
		Field field = gen.getDeclaredField("lista");
		Field field2 = gen.getDeclaredField("hashMap");
		Field field3 = gen.getDeclaredField("test");
		System.out.println(field.getDeclaringClass());
		/*

		ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
		ParameterizedType parameterizedType2 = (ParameterizedType) parameterizedType.getActualTypeArguments()[0];
		
		ParameterizedType parameterizedType3 = (ParameterizedType) field2.getGenericType();
		ParameterizedType parameterizedType4 = (ParameterizedType) parameterizedType3.getActualTypeArguments()[1];
		//ParameterizedType parameterizedType3 = (ParameterizedType) parameterizedType2.getActualTypeArguments()[0];
		//Collection<?> clas = (Collection<?>)parameterizedType3.getActualTypeArguments()[1];
		System.out.println(getGenericTypeName(field2.getGenericType()));
		//System.out.println(parameterizedType3.getRawType().toString().split("\\."));*/
	}
	
	private static String getGenericTypeName(Type type)
	{
		String parameterTypeName = "";
		try
		{
			ParameterizedType tempType = (ParameterizedType) type;
			Type[] parametersType = tempType.getActualTypeArguments();
			parameterTypeName+=getRawTypeName(tempType.getRawType())+"<";
			for(Type parameterType: parametersType)
			{
				parameterTypeName+=getGenericTypeName(parameterType)+",";
			}
			parameterTypeName = parameterTypeName.substring(0,parameterTypeName.length()-1);
			parameterTypeName+=">";
		}
		catch (ClassCastException e)
		{
			parameterTypeName+=getRawTypeName(type);
		}
		return parameterTypeName;
	}
	private static String getRawTypeName(Type type)
	{
		String[] temp = type.toString().split("\\.");
		return temp[temp.length-1];
	}
	
	
}
