package diapositivas.designfortesteability;

@SuppressWarnings("unused")
public class Composition {

	public class Car {
		private String manufacturer;
		private Engine engine;

		public Car(String manufacturer, 
				   Engine engine) {
			this.manufacturer = manufacturer;
			this.engine = engine;
		}
	}

	public class Engine {

	}

}
