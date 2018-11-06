package wmq.fly.design.patterns;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class PrototypeT implements Cloneable, Serializable{
	String name;
	
	protected Object shalledClone() throws CloneNotSupportedException {
		PrototypeT prototype = (PrototypeT) super.clone();
		return prototype;
	}
	
	public Object deepClone() throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        ObjectOutputStream oos = new ObjectOutputStream(bos);  
        oos.writeObject(this);  
        bos.close();
        oos.close();
  
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
        ObjectInputStream ois = new ObjectInputStream(bis); 
        bis.close();
        ois.close();
     
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oost = new ObjectOutputStream(baos);
        oost.writeObject(this);
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream oist = new ObjectInputStream(bais);
        return oist.readObject();  
	}
}



public class Test {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		PrototypeT prototypeT = new PrototypeT();
		prototypeT.name = "123";
		PrototypeT deepClone = (PrototypeT) prototypeT.deepClone();
		System.out.println(deepClone.name);
	}
}
