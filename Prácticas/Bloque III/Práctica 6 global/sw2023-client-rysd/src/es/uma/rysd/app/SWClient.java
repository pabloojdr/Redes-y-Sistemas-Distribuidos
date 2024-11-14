package es.uma.rysd.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import es.uma.rysd.entities.*;

public class SWClient {
	// TODO: Complete el nombre de la aplicación
	private final String app_name = "StarWars Trivia";
	private final int year = 2023;

	private final String url_api = "https://swapi.dev/api/";

	// Métodos auxiliares facilitados

	// Obtiene la URL del recurso id del tipo resource
	public String generateEndpoint(String resource, Integer id) {
		return url_api + resource + "/" + id + "/";
	}

	// Dada una URL de un recurso obtiene su ID
	public Integer getIDFromURL(String url) {
		String[] parts = url.split("/");

		return Integer.parseInt(parts[parts.length - 1]);
	}

	// Consulta un recurso y devuelve cuántos elementos tiene
	public int getNumberOfResources(String resource) throws IOException {
		// TODO: Trate de forma adecuada las posibles excepciones que pueden producirse

		// TODO: Cree la URL correspondiente: https://swapi.dev/api/{recurso}/
		// reemplazando el recurso por el parámetro
		URL servicio = new URL("https://swapi.dev/api/" + resource + "/");

		// TODO: Cree la conexión a partir de la URL
		HttpsURLConnection connection = (HttpsURLConnection) servicio.openConnection();

		// TODO: Añada las cabeceras User-Agent y Accept (vea el enunciado)
		connection.setRequestProperty("User-Agent", app_name);
		connection.setRequestProperty("Accept", "application/json");

		// TODO: Indique que es una petición GET
		connection.setRequestMethod("GET");

		// TODO: Compruebe que el código recibido en la respuesta es correcto
		if (connection.getResponseCode() / 100 != 2) {
			System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
			return 0;
		}

		// TODO: Deserialice la respuesta a ResourceCountResponse
		Gson parser = new Gson();
		InputStream in = connection.getInputStream(); // TODO: Obtenga el InputStream de la conexión
		ResourceCountResult c = parser.fromJson(new InputStreamReader(in), ResourceCountResult.class);

		// TODO: Devuelva el número de elementos
		return c.count;
	}

	public Person getPerson(String urlname) throws IOException {
		Person p = null;
		// Por si acaso viene como http la pasamos a https
		urlname = urlname.replaceAll("http:", "https:");

		// TODO: Trate de forma adecuada las posibles excepciones que pueden producirse

		// TODO: Cree la conexión a partir de la URL recibida
		URL servicio = new URL(urlname);
		HttpsURLConnection connection = (HttpsURLConnection) servicio.openConnection();

		// TODO: Añada las cabeceras User-Agent y Accept (vea el enunciado)
		connection.setRequestProperty("User-Agent", app_name);
		connection.setRequestProperty("Accept", "application/json");

		// TODO: Indique que es una petición GET
		connection.setRequestMethod("GET");

		// TODO: Compruebe que el código recibido en la respuesta es correcto
		if (connection.getResponseCode() / 100 != 2) {
			System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
			return null;
		}

		// TODO: Deserialice la respuesta a Person
		Gson parser = new Gson();
		InputStream in = connection.getInputStream();
		p = parser.fromJson(new InputStreamReader(in), Person.class);

		// TODO: Para las preguntas 2 y 3 (no necesita completar esto para la pregunta
		// 1)
		// TODO: A partir de la URL en el campo homeworld obtenga los datos del planeta
		// y almacénelo en atributo homeplanet
		p.homeplanet = getWorld(p.homeworld);
		

		return p;
	}

	public World getWorld(String urlname) throws IOException {
		World p = null;
		// Por si acaso viene como http la pasamos a https
		urlname = urlname.replaceAll("http:", "https:");

		// TODO: Trate de forma adecuada las posibles excepciones que pueden producirse

		// TODO: Cree la conexión a partir de la URL recibida
		URL servicio = new URL(urlname);
		HttpsURLConnection connection = (HttpsURLConnection) servicio.openConnection();

		// TODO: Añada las cabeceras User-Agent y Accept (vea el enunciado)
		connection.setRequestProperty("User-Agent", app_name);
		connection.setRequestProperty("Accept", "application/json");

		// TODO: Indique que es una petición GET
		connection.setRequestMethod("GET");

		// TODO: Compruebe que el código recibido en la respuesta es correcto
		if (connection.getResponseCode() / 100 != 2) {
			System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
			return null;
		}

		// TODO: Deserialice la respuesta a Planet
		Gson parser = new Gson();
		InputStream in = connection.getInputStream();
		p = parser.fromJson(new InputStreamReader(in), World.class);

		return p;
	}
	
	public Movie getMovie(String urlname) throws IOException {
		Movie p = null;
		// Por si acaso viene como http la pasamos a https
		urlname = urlname.replaceAll("http:", "https:");

		// TODO: Trate de forma adecuada las posibles excepciones que pueden producirse

		// TODO: Cree la conexión a partir de la URL recibida
		URL servicio = new URL(urlname);
		HttpsURLConnection connection = (HttpsURLConnection) servicio.openConnection();

		// TODO: Añada las cabeceras User-Agent y Accept (vea el enunciado)
		connection.setRequestProperty("User-Agent", app_name);
		connection.setRequestProperty("Accept", "application/json");

		// TODO: Indique que es una petición GET
		connection.setRequestMethod("GET");

		// TODO: Compruebe que el código recibido en la respuesta es correcto
		if (connection.getResponseCode() / 100 != 2) {
			System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
			return null;
		}

		// TODO: Deserialice la respuesta a Planet
		Gson parser = new Gson();
		InputStream in = connection.getInputStream();
		p = parser.fromJson(new InputStreamReader(in), Movie.class);

		return p;
	}

	public Person search(String name) throws IOException {
		Person p = null;
		QueryResponse q = null;
		// TODO: Trate de forma adecuada las posibles excepciones que pueden producirse

		// TODO: Cree la conexión a partir de la URL (url_api + name tratado - vea el
		// enunciado)
		URL servicio = new URL("https://swapi.dev/api/people/?search=" + URLEncoder.encode(name, "utf-8"));
		HttpsURLConnection connection = (HttpsURLConnection) servicio.openConnection();

		// TODO: Añada las cabeceras User-Agent y Accept (vea el enunciado)
		connection.setRequestProperty("User-Agent", app_name + "-" + Integer.toString(year));
		connection.setRequestProperty("Accept", "application/json");

		// TODO: Indique que es una petición GET
		connection.setRequestMethod("GET");

		// TODO: Compruebe que el código recibido en la respuesta es correcto
		if (connection.getResponseCode() / 100 != 2) {
			System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
			return null;
		}

		// TODO: Deserialice la respuesta a QueryResponse -> Use la primera posición
		// del array como resultado
		Gson parser = new Gson();
		InputStream in = connection.getInputStream();
		q = parser.fromJson(new InputStreamReader(in), QueryResponse.class);
		if (q.results.length == 0) {
			return null;
		} else {
			p = q.results[0];
		}

		// TODO: Para las preguntas 2 y 3 (no necesita completar esto para la pregunta
		// 1)
		// TODO: A partir de la URL en el campo homreworld obtenga los datos del planeta
		// y almacénelo en atributo homeplanet
		p.homeplanet = getWorld(p.homeworld);

		return p;
	}

}
