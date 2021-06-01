package Project;

import java.util.List;

public class ArrowType
{
	private String utility;
	private Boolean unique = false;
	private String writtenArrow = "->";
	private int priority = 0;
	private String origin;
	private String objective;
	
	
	
	
	public String getOrigin() {
		return origin;
	}


	public String getObjective() {
		return objective;
	}

	public boolean sameDirrection(ArrowType arrow)
	{
		return this.objective.equals(arrow.objective) && this.origin.equals(arrow.origin);
	}
	public ArrowType(String utility, Boolean unique, String writtenArrow, int priority)
	{
		this.utility = utility;
		this.unique = unique;
		this.writtenArrow = writtenArrow;
		this.priority = priority;
	}

	public ArrowType(ArrowType arrowType)
	{
		this(arrowType.utility,arrowType.unique,arrowType.writtenArrow,arrowType.priority);
	}
	public ArrowType(ArrowType arrowType,String origin,String objective)
	{
		this(arrowType);
		this.origin = origin;
		this.objective = objective;
	}
	
	
	
	public static List<ArrowType> addArrow(ArrowType arrow,List<ArrowType> arrows)
	{
		boolean replaced = false;
		if(arrow.unique)
			for(int i = 0; i < arrows.size(); i++)
			{
				if(arrows.get(i).sameDirrection(arrow) && arrows.get(i).unique && arrows.get(i).priority<arrow.priority)
				{
					arrows.set(i,arrow);
					replaced = true;
				}
			}
		if(!replaced)
			arrows.add(arrow);
		return arrows;
	}
	
	
	public Boolean getUnique() {
		return unique;
	}
	public String getWrittenArrow() {
		return writtenArrow;
	}
	public int getPriority() {
		return priority;
	}
	public String getUtility() {
		return utility;
	}


	@Override
	public String toString() {
		return origin+" "+writtenArrow+" "+objective;
	}

	
	
}
