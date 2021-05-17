import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AutoDiagram
{
	public static List<String> getAllClases(String packageName)
	{

		URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
		File[]files = new File(root.getFile()).listFiles();

		List<String> classes = new ArrayList<>();
		
		for (int i = 0; i < files.length; i++)
			if (files[i].getName().contains(".class"))
			{
				String className = packageName+"."+files[i].getName();
				className = className.substring(0,className.length()-6);
				classes.add(className);
			}
			else
				classes.addAll(getAllClases(packageName+"."+files[i].getName()));

		return classes;
	}
	
	
	public static String getFullPackageDiagram(String packageName) throws ClassNotFoundException
	{
		List<String> classes = AutoDiagram.getAllClases(packageName);
		String Diagram = "";
		for (int i = 0; i < classes.size(); i++)
		{
			Class<?> LoadedClass = Class.forName(classes.get(i));
			if (LoadedClass.isEnum())
				Diagram += AutoDiagram.getEnumDiagram(LoadedClass)+"\n";
			else
				Diagram += AutoDiagram.getClassDiagram(LoadedClass)+"\n";
		}
		return Diagram;
	}
	
	public static String getEnumDiagram(Class<?> PassedClass)
	{
		String enumDiagram = "enum ";
		
		 enumDiagram += PassedClass.getSimpleName()+" \n{\n";
		Object[] enumConstances = PassedClass.getEnumConstants();
		for (int i = 0; i < enumConstances.length; i++)
			enumDiagram += "\t"+enumConstances[i].toString()+"\n";
		enumDiagram+="}";
		return enumDiagram;
	}
	
	public static String getClassDiagram(Class<?> PassedClass)
	{
		String classDiagram = "class ";
		if (PassedClass.isInterface())
			classDiagram =  "interface ";
		classDiagram += PassedClass.getSimpleName()+" \n{\n";
		Field[] fields = PassedClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++)
			classDiagram += "\t"+AutoDiagram.getFieldData(fields[i])+"\n";
		classDiagram += "\n";
		Method[] methods = PassedClass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++)
			classDiagram += "\t"+AutoDiagram.getMethodData(methods[i])+"\n";
		classDiagram+="}";
		return classDiagram;
	}
	
	
	public static String getFieldData(Field field)
	{
		return field.getName()+":"+field.getType().getSimpleName();
	}
	public static String getMethodData(Method method)
	{	
		Type[] methodParameters = method.getGenericParameterTypes();
		String methodParametersType = "";
		for(int i = 0; i < methodParameters.length; i++)
			methodParametersType += AutoDiagram.getGenericTypeName(methodParameters[i])+", ";
		if(methodParametersType.length()>0)
			methodParametersType = methodParametersType.substring(0,methodParametersType.length()-2);
		return method.getName()+"("+methodParametersType+")";
	}
	
	
	
	public static String getGenericTypeName(Type type)
	{
		//System.out.println(type.getTypeName());
		if (type.getTypeName().contains("."))
		{
			String[] typeName =  type.getTypeName().split("\\.");
			String typeFinalName = typeName[typeName.length-1];
			if(typeFinalName.substring(typeFinalName.length()-1).equals(">"))
			{
				typeFinalName = "List<"+typeFinalName;
			}
			return typeFinalName;
		}
		return type.getTypeName();
	}

}
