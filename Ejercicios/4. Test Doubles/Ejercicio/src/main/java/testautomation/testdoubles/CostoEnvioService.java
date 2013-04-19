package testautomation.testdoubles;

import java.security.InvalidParameterException;

public class CostoEnvioService {
	static final int ENVIO_GRATIS = 0;

	private CostoEnvioDAO costoEnvioDAO;

	public CostoEnvioService() {
		costoEnvioDAO = new CostoEnvioDAO();
	}

	public double calcular(String pais) {
		if (pais == "USA")
			return ENVIO_GRATIS;

		return costoEnvioDAO.obtener(pais);
	}

	public void actualizarCosto(String pais, double costo) {
		if (costo == 0)
			throw new InvalidParameterException("Costo Envio no puede ser 0");

		costoEnvioDAO.actualizar(pais, costo);
	}}
