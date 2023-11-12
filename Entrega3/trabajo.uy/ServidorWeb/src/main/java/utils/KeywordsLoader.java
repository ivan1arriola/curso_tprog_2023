package utils;

import java.util.Set;

import interfaces.IKeywordsLoader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class KeywordsLoader implements IKeywordsLoader{

	@Override
	public void cargarKeywords(HttpServletRequest request, HttpServletResponse response) {

		Set<String> keys = FabricaWeb.getLogica().listarKeywords();
		request.setAttribute("keys", keys);
		
	}

}
