package wmq.fly.genericType;

public class Book extends BigBook {
	
	private String name ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + "]"+"[type=" + super.getType() + "]";
	}
	
	

}
