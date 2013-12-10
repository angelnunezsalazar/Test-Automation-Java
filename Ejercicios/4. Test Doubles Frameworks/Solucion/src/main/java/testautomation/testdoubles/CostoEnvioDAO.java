package testautomation.testdoubles;

public interface CostoEnvioDAO {

	public abstract void actualizar(String pais, double costo);

	public abstract double obtener(String pais);

}