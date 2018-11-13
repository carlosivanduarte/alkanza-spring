package com.alkanza.delegate;

import static com.alkanza.model.Response.OK;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkanza.algorithm.Data;
import com.alkanza.algorithm.IAlgorithm;
import com.alkanza.model.Location;
import com.alkanza.model.PlacesRequest;
import com.alkanza.model.Point;
import com.alkanza.model.Response;
import com.alkanza.model.entity.Place;
import com.alkanza.model.entity.Result;
import com.alkanza.repository.PlaceRepository;
import com.alkanza.repository.ResultRepository;
import com.google.common.collect.FluentIterable;

@Service
public class ProcessDelegate implements IProcessDelegate {

	private ResultRepository resultRepository;
	
	private PlaceRepository placeRepository;
	
	private IAlgorithm algorithm;

	@Autowired
	public ProcessDelegate(
			ResultRepository resultRepository,
			PlaceRepository placeRepository,
			IAlgorithm algorithm) {
		this.resultRepository = resultRepository;
		this.placeRepository = placeRepository;
		this.algorithm = algorithm;
	}

	@Override
	public Response process(PlacesRequest placesRequest) {
		Result result = buildResult(placesRequest);
		Iterable<Place> placesDB = placeRepository.findAll();
		
		List<Point> pointsBalanced = placesRequest.getPoints().stream().filter(new Predicate<Point>() {

			@Override
			public boolean test(Point point) {
				
				Location location = point.getLocation();
				for (Place place: placesDB) {
					boolean found = place.getLatitude().equals(location.getLat()) && place.getLongitude().equals(location.getLng());
					if(found) {
						return false;
					}
				}
				return true;
			}
		})
		.collect(Collectors.toList());
		
		Data data = new Data(
				getDistances(placesRequest.getPoints()),
				getDistances(pointsBalanced));
		
		result.setCalculation(algorithm.process(data));
		
		resultRepository.save(result);
		
		placesRequest.getPoints().forEach(new Consumer<Point>() {
			public void accept(Point point) {
				Place place = buildPlace(point);
				place.setResult(result);
				placeRepository.save(place);
			}
		});
		
		return OK;
	}
	
	private Result buildResult(PlacesRequest placesRequest) {
		return Result.builder()
				.userLatitude(placesRequest.getUserLocation().getLat())
				.userLongitude(placesRequest.getUserLocation().getLng())
				.radius(placesRequest.getRadius())
				.build();
	}
	
	private Place buildPlace(Point point) {
		return Place.builder()
				.latitude(point.getLocation().getLat())
				.longitude(point.getLocation().getLng())
				.name(point.getName())
				.distance(point.getDistance())
				.build();		
	}
	
	private List<Double> getDistances(List<Point> points) {
		return FluentIterable.from(points).transform(point -> point.getDistance()).toList();
	}
}