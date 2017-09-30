package Sender;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Serializer
{
	Object item;
	
	int IDNumber;
	Element root;
	Document document; 
	
	public void serialize(Object item) throws IllegalArgumentException, IllegalAccessException 
	{
		this.item = item; 
		IDNumber = System.identityHashCode(item);
		root = new Element("serialized");
		document = new Document(root);
		createObject(item, root);
		output();
	}
	
	
	private void createObject(Object cur, Element curRoot) throws IllegalArgumentException, IllegalAccessException
	{
		Element object = new Element("object");
		String make = (cur.getClass().toString());
		make = make.replace("class ", "");
		System.out.println(make);
		object.setAttribute(new Attribute("class", make));
		object.setAttribute(new Attribute("id", String.valueOf(IDNumber)));
		serializeFields(object, cur);
		curRoot.addContent(object);
	}


	private void serializeFields(Element object, Object cur) throws IllegalArgumentException, IllegalAccessException 
	{
		Field[] fields = cur.getClass().getDeclaredFields();
		for (Field x : fields)
		{
			Element field = new Element("field");
			field.setAttribute("name", x.getName());
			field.setAttribute("declaringclass", cur.getClass().getName());
			serializeValue(field, x, cur);
			object.addContent(field);
		}
	} 
	
	private void serializeValue(Element Pfield, Field x, Object cur) throws IllegalArgumentException, IllegalAccessException
	{
		if(x.getType().isPrimitive())
		{
			Element value = new Element("Value");
			try 
			{
				x.setAccessible(true);
				value.setText(x.get(cur).toString());
				Pfield.addContent(value);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
			}
		}
		else if(x.getType().isArray())
		{
			Object[] array = new Object[Array.getLength(x.get(cur))];
		    for(int i=0;i<array.length;i++)
		    {
		        array[i] = Array.get(x.get(cur), i);
		    }
			Element object = new Element("object");
			object.setAttribute("class", x.getType().toString());
			object.setAttribute("id",String.valueOf(System.identityHashCode(x)));
			object.setAttribute("length",String.valueOf(array.length));
			addArrayValue(object, array, cur);
			Pfield.addContent(object);
		}
		else
		{
			Element reference = new Element("reference");
			createObject(x.get(cur), reference);
			Pfield.addContent(reference);
		}
	}


	private void addArrayValue(Element object, Object[] array, Object cur) throws IllegalArgumentException, IllegalAccessException
	{
		for (Object x: array)
		{
			Element value = new Element("value");
			if(x.getClass().isPrimitive()|| x instanceof Integer)
			{
				value.setText(x.toString());
				object.addContent(value);
			}
			else
			{
				createObject(x, object);
			}
		}
	}


	private void output()
	{
		XMLOutputter outputer = new XMLOutputter();
		outputer.setFormat(Format.getPrettyFormat());
		try
		{
			outputer.output(document, new FileWriter("serialized.xml"));
			System.out.println("XML File Created");
		} 
		catch (IOException e)
		{
			System.out.println("Something went wrong :(");
			e.printStackTrace();
		}
	}
	
	
}
