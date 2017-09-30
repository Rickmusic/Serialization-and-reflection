package Sender;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Inspector
{
	Object obj;
	
	public void inspect(Object obj) throws IllegalArgumentException, IllegalAccessException
	{
		this.obj = obj;
		printClassName(obj);
		printFields(obj);
	}
	
	public void printClassName(Object object)
	{
		String name = object.getClass().getName().toString();
		System.out.println(name);
	}

	public void printFields(Object object) throws IllegalArgumentException, IllegalAccessException
	{
		Field[] fields = object.getClass().getDeclaredFields();
		for(Field x : fields)
		{
			x.setAccessible(true);
			System.out.println("With Field: " + x + " With Type: " + x.getType());
			System.out.println("This field has value of: ");
			if(x.getType().isPrimitive())
			{
				System.out.println(x.get(object));
			}
			else if(x.getType().isArray())
			{
				try
				{
					Object[] array = new Object[Array.getLength(x.get(object))];
					Object fun = x.get(obj);
					for(int i=0;i<array.length;i++)
					{
						array[i] = Array.get(fun, i);
						System.out.println("Array Value " + i );
						arrayHelper(array[i]);
					}
				}
				catch(Exception e)
				{
					
				}
			} 
			else
			{
				printClassName(x.get(object));
				printFields(x.get(object));
			}
		}
	}
	
	public void arrayHelper(Object in) throws IllegalArgumentException, IllegalAccessException
	{
		System.out.println(in.getClass());
		if(in.getClass().isPrimitive())
		{
			System.out.println("Primitive? ");
			System.out.println(in);
		}
		else
		{
			printFields(in);
		}
	}

}

