package com.alkanza.distance;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.alkanza.AlkanzaSpringApplication;
import com.alkanza.config.H2TestProfileJPAConfig;
import com.alkanza.delegate.IProcessDelegate;
import com.alkanza.model.Location;
import com.alkanza.model.PlacesRequest;
import com.alkanza.model.Point;
import com.alkanza.model.ProcessResult;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AlkanzaSpringApplication.class, H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
public class AlkanzaSpringApplicationTests {
	
	@Autowired
	private IProcessDelegate processDelegate;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testBalancedPoints() {
		PlacesRequest request = new PlacesRequest();
		request.setPoints(createPoints());
		List<Point> balancedPoints = processDelegate.getBalancedPoints(request);

		assertThat(balancedPoints.size(), is(2)); 
				
	}
	
	@Test
	public void testProcess() {
		PlacesRequest request = new PlacesRequest();
		request.setPoints(createPoints());
		Location userLocation = new Location();
		userLocation.setLat(5.0);
		userLocation.setLng(5.0);
		request.setUserLocation(userLocation);
		request.setRadius(50);
		List<ProcessResult> results = processDelegate.process(request);
		assertFalse(results.isEmpty());
		assertThat(results.get(0).getCalculation(), is(500.0));
	}
	
	private List<Point> createPoints() {
		List<Point> points = new ArrayList<Point>();
		points.add(createPoint(10.0, 10.0, 50.0, "Point 1"));
		points.add(createPoint(20.0, 20.0, 10.0, "Point 2"));
		points.add(createPoint(30.0, 30.0, 150.0, "Point 3"));
		points.add(createPoint(40.0, 40.0, 200.0, "Point 4"));
		points.add(createPoint(50.0, 50.0, 250.0, "Point 5"));
		return points;
	}
	
	private Point createPoint(Double latitud, Double longitude, Double distance, String name) {
		Location location = new Location();
		location.setLat(latitud);
		location.setLng(longitude);
		return Point.builder()
				.location(location)
				.distance(distance)
				.name(name)
				.build();
	}

}
