package wmq.fly.design.patterns;

public class Test{
	public static void main(String[] args) {
		VisitorT m = new MyVisitorT();
		SubjectT ms = new MySubjectT(m);
		ms.accept();
		
	}
}

interface VisitorT {
	public void visit(SubjectT sub);
}

class MyVisitorT implements VisitorT {

	@Override
	public void visit(SubjectT sub) {
		System.out.println("fasfa: "+ sub.getSubject());
	}
}

interface SubjectT {
	public void accept();
	public String getSubject();
}

class MySubjectT implements SubjectT{
	
	private VisitorT visitorT;

	public MySubjectT(VisitorT visitorT) {
		super();
		this.visitorT = visitorT;
	}

	@Override
	public void accept() {
		visitorT.visit(this);
	}

	@Override
	public String getSubject() {
		return "love";
	}
	
}

