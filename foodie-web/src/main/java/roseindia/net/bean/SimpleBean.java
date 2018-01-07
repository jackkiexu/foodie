package roseindia.net.bean;

public class SimpleBean {
	private DependencyBean dependency;

	public void test() {
		dependency.sayHi();
		dependency.greet();
	}

	public void setDependency(DependencyBean dependency) {
		this.dependency = dependency;
	}
}
