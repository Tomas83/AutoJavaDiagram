package Project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AutoDiagram
{
	private List<Class<?>> classes;
	private String packageName;
	private List<ArrowType> arrows = Arrays.asList(
				new ArrowType("Herencia",false,"--|>",1),
				new ArrowType("typeReference", true, "->", 3),
				new ArrowType("ListtypeReference", true, "-->\"*\"", 4)
			);
	public AutoDiagram(String packageName) throws ClassNotFoundException
	{
		
		this.packageName= packageName;
		
		this.classes = new ArrayList<>();
		List<String> classesNames = getAllClases(packageName);
		for(String className:classesNames)
		{
			this.classes.add(Class.forName(className));
		}
	}
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
	
	public String getFullPackageDiagram() throws ClassNotFoundException
	{
		List<ArrowType> declaredArrows = new ArrayList<>();
		
		for (Class<?>tempClass:this.classes)
		{

			for (Class<?>parentClass:this.classes)
			{
				if (tempClass.isAssignableFrom(parentClass) && tempClass!=parentClass)
				{
					declaredArrows.add(new ArrowType(arrows.get(0),tempClass.getSimpleName(),parentClass.getSimpleName()));
				}
			}
			if(!tempClass.isEnum() && !tempClass.getSimpleName().equals(""))
			{
				List<ArrowType> foundConections = getClassRelationships(tempClass);
				System.out.println(tempClass.getSimpleName()+"\t"+foundConections.size());
				declaredArrows.addAll(foundConections);
			}
		}
		String relationships = "";
		for(ArrowType arrow:declaredArrows)
		{
			relationships+=arrow.toString()+"\n";
		}
		return getFullPackageDiagram(packageName)+"\n"+relationships;
		
	}
	private static boolean checkCollection(String name)
	{
		String[] collectionNames = {"List","ArrayList"};
		for(String check: collectionNames)
		{
			if(check.equals(name))
				return true;
		}
		return false;
	}
	private static boolean checkCollection(List<String> names)
	{
		return names.stream().anyMatch(c->checkCollection(c));
	}
	private List<ArrowType> getClassRelationships(Class<?> tempClass)
	{
		
		Field[] fields = tempClass.getDeclaredFields();
		List<ArrowType> declaredArrows = new ArrayList<ArrowType>();
		
		for(Field field: fields)
		{
			String[] allClasses = getGenericTypeName(field.getGenericType()).split("[^a-zA-Z0-9']+");
			for(String type:allClasses)
			{
				if(classes.stream().anyMatch(c -> c.getSimpleName().equals(type)))
				{
					Class<?> objective = classes.stream().filter(c -> c.getSimpleName().equals(type)).findFirst().get();
					if(checkCollection(Arrays.asList(allClasses)))
					{
						ArrowType.addArrow(new ArrowType(arrows.get(2),field.getDeclaringClass().getSimpleName(),objective.getSimpleName()), declaredArrows);
						//declaredArrows.add(new ArrowType(arrows.get(2),field.getDeclaringClass().getSimpleName(),objective.getSimpleName()));
					}
					else
					{
						ArrowType.addArrow(new ArrowType(arrows.get(1),field.getDeclaringClass().getSimpleName(),objective.getSimpleName()), declaredArrows);
						//declaredArrows.add(new ArrowType(arrows.get(1),field.getDeclaringClass().getSimpleName(),objective.getSimpleName()));
					}
				}
			}
		}
		return declaredArrows;
	}
	public static void getFullPackageDiagramInFile(String filename, String packageName) throws IOException, ClassNotFoundException
	{
		
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),"utf-8"));
		writer.write(getFullPackageDiagram(packageName));
		writer.close();
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
		return field.getName()+":"+getGenericTypeName(field.getGenericType());//field.getType().getSimpleName();
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
	/*
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
	}*/

}
