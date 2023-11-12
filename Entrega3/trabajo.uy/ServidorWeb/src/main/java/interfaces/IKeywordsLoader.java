package interfaces;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IKeywordsLoader {

	void cargarKeywords(HttpServletRequest request, HttpServletResponse response);
}
