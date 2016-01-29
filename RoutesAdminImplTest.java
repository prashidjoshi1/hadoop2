package biz.neustar.omx.admin.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import biz.neustar.omx.util.RouteTypeList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/resources/META-INF/spring/springContext.xml",
		"file:src/main/resources/META-INF/spring/camelContext.xml" })
public class RoutesAdminImplTest {

	@Autowired
	ApplicationContext ctx;
	
	private String endpointUrl = "http://localhost:9000/atmos/admin/";

	@Test
	public void testGetAllRoutes() {

		WebClient client = WebClient.create(endpointUrl
				+ "/routes");
		Response r = client.accept("text/plain").get();
		assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
		String value = "";
		try {
			value = IOUtils.toString((InputStream)r.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Response from web service is : " + value);
		//assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
	}

	@Test
	public void testStartRoutes() {

		List<Object> providers = new ArrayList<Object>();
        providers.add(new org.apache.cxf.jaxrs.provider.json.JSONProvider());
         
        ObjectMapper mapper = new ObjectMapper();
        RouteTypeList routeTypeList = null;
        //JSON from file to Object
        try {
			routeTypeList = mapper.readValue(new File(getClass().getClassLoader().getResource("file.json").getFile()), RouteTypeList.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
         
        WebClient client = WebClient.create(endpointUrl + "/routes/start", providers);
        Response r = client.accept("application/json")
            .type("application/json")
            .post(routeTypeList);
         
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
	}
	
	@Test
	public void testStopRoutes() {

		List<Object> providers = new ArrayList<Object>();
        providers.add(new org.apache.cxf.jaxrs.provider.json.JSONProvider());
         
        List<String> routeList = new ArrayList<String>();
        routeList.add("ggg");
        routeList.add("vvvv");
        RouteTypeList routeTypeList = new RouteTypeList();
        routeTypeList.setRouteId(routeList);
         
        WebClient client = WebClient.create(endpointUrl + "/routes/stop", providers);
        Response r = client.accept("application/json")
            .type("application/json")
            .post(routeTypeList);
         
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
	}
	
	@Test
	public void testRestartRoutes() {

		List<Object> providers = new ArrayList<Object>();
        providers.add(new org.apache.cxf.jaxrs.provider.json.JSONProvider());
         
        List<String> routeList = new ArrayList<String>();
        routeList.add("ggg");
        routeList.add("vvvv");
        RouteTypeList routeTypeList = new RouteTypeList();
        routeTypeList.setRouteId(routeList);
         
        WebClient client = WebClient.create(endpointUrl + "/routes/restart", providers);
        Response r = client.accept("application/json")
            .type("application/json")
            .post(routeTypeList);
         
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
	}

	@Test
	public void testUpdateRoutes() {
  
        WebClient client = WebClient.create(endpointUrl + "/routes/update");
        Response r = client.accept("application/xml")
            .type("application/xml")
            .post("<routes></routes>");
         
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
	}

}
