package simple.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CurrencyConverterServlet
 */
public class CurrencyConverterServlet extends HttpServlet {

	private double cadConversionRate = 1.0d;

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		String cadConversionRateAsString = servletConfig
				.getInitParameter("USD_CAD");
		if (cadConversionRateAsString == null) {
			log("Missing USD/CAD conversion rate. Using default value: "
					+ cadConversionRate);
			return;
		}

		try {
			cadConversionRate = Double.parseDouble(cadConversionRateAsString);
		} catch (final NumberFormatException e) {
			log("Invalid USD/CAD conversion rate. Using default value: "
					+ cadConversionRate, e);
		}
	}

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CurrencyConverterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		response.setContentType("text/html");
		final PrintWriter out = response.getWriter();
		final String amountInUsdAsString = request.getParameter("amountInUsd");

		try {
			final double amountInUsd = Double.parseDouble(amountInUsdAsString);
			final double amountInCad = cadConversionRate * amountInUsd;

			out.println("<html>");
			out.println("<head><title>Currency Converter</title></head>");
			out.println("<body>");
			out.println("<p>" + amountInUsd
					+ " in US dollars is equivalent to " + amountInCad
					+ " Canadian dollars eh!</p>");
			out.println("</body></html>");
		} catch (final NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			log("USD amount of '" + amountInUsdAsString + "' is not a number.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
