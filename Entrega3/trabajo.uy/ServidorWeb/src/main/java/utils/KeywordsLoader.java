package utils;

import java.util.Set;

import interfaces.IKeywordsLoader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.Fabrica;

public class KeywordsLoader implements IKeywordsLoader{

	@Override
	public void cargarKeywords(HttpServletRequest request, HttpServletResponse response) {

		Set<String> keys = FabricaWeb.getInstance().getLogica().listarKeywords();
		request.setAttribute("keys", keys);
		
	}

}
