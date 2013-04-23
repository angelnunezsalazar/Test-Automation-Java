package diapositivas.designfortesteability;
@SuppressWarnings("unused")
public class Inheritance {

	public class Vehicle {
		protected Engine engine;
	}

	public class Car extends Vehicle {
		private String manufacturer;
	}

	public class Engine {
	}
}
