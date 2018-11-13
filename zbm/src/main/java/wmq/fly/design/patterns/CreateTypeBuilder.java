package wmq.fly.design.patterns;
/**
 * 建造者模式:创建型模式
 * 好处：
 * 		可以自由组合各种属性。
 *
 */

//实体类
class Student{
	
	private String name;
	private Integer age;
	private String gender;
	
	public Student(StudentBuilder studentBuilder) {
		this.name = studentBuilder.name;
		this.age = studentBuilder.age;
		this.gender = studentBuilder.gender;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
}
//实体类的建造者
class StudentBuilder{
	
	String name;
	Integer age;
	String gender;
	
	public StudentBuilder() {
		
	}
	
	public StudentBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public StudentBuilder setAge(Integer age) {
		this.age = age;
		return this;
	}
	
	public StudentBuilder setGender(String gender) {
		this.gender = gender;
		return this;
	}
	//创建一个Student对象
	public Student build() {
		return new Student(this);
	}
}


public class CreateTypeBuilder {
	public static void main(String[] args) {
		Student stu = new StudentBuilder().setAge(12).setGender("F").setName("小宁").build();
		System.out.println(stu.toString());
	}

}
