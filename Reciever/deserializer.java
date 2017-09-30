package Reciever;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;


public class deserializer
{
	public org.jdom2.Document makeDoc() throws JDOMException, IOException
	{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("deserializer.xml");
		Document document = (Document) builder.build(xmlFile);
		return document;
	}
	
	public Object deserialize(org.jdom2.Document document) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		Element rootNode = document.getRootElement();
		return  handleObject(rootNode.getChild("object"));
	}
	
	private Object handleObject(Element element) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		// Dynamically loading Class //
		Object obj = null;
		try
		{
			Class A = Class.forName(element.getAttributeValue("class"));
			//Create Instance // 
			Constructor cons = A.getConstructor();
			cons.setAccessible(true);
			obj = cons.newInstance();
			//object.hashCode = element.getAttribute("id");
			List list = element.getChildren();
			for (int i = 0; i < list.size(); i++) 
			{
				handleField((Element) list.get(i), obj);
			}
			return obj;
		}
		catch(Exception e)
		{
			
		}
		return obj;
	}

	private Object[] arrayHandler(Element element)
	{
		String str = element.getAttribute("length").toString();
			str = str.replaceAll( "[^\\d]", "" );
			int length = Integer.parseInt(str);
			List list = element.getChildren();
			Object[] array = (Object[]) Array.newInstance(Object.class, length);
			for(int x = 0; x<array.length; x++ )
			{
				array[x] = ((Element) list.get(x)).getValue();
				System.out.println(array[x]);
			}

			return array;
	}
	private void handleField(Element element, Object obj) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException
	{
		//Dynamically fields class// 
		Class.forName(element.getAttributeValue("declaringclass"));
		String name = element.getAttributeValue("name");
		Field field = obj.getClass().getDeclaredField(name);
		field.setAccessible(true);
		if(field.getType().isPrimitive())
		{
			Object item = toObject(field.getType(), element.getValue().trim());	
			field.set(obj,item);
		}
		else if(field.getType().isArray()) 
		{
			Object[] array= arrayHandler(element.getChild("object"));
			System.out.println(array[0]);
			field.set(obj, array);
		}
		else
		{
			List list = element.getChildren();
			for (int i = 0; i < list.size(); i++) 
			{
				handleObject(((Element) list.get(i)).getChild("object"));
			}	
		}
	}
	
	
	public static Object toObject( Class clazz, String value )
	{
	    if( boolean.class == clazz ) return Boolean.parseBoolean( value );
	    if( byte.class == clazz ) return Byte.parseByte( value );
	    if( short.class == clazz ) return Short.parseShort( value );
	    if( int.class == clazz ) return Integer.parseInt( value );
	    if( long.class == clazz ) return Long.parseLong( value );
	    if( float.class == clazz ) return Float.parseFloat( value );
	    if( double.class == clazz ) return Double.parseDouble( value );
	    return value;
	}
	
}
