var map, infoWindow, circle, userLatLng, json;
      
function initMap() {

  map = new google.maps.Map($("#map")[0], {
    zoom: 14,
    streetViewControl: false,
    mapTypeControl: false
  });

  map.controls[google.maps.ControlPosition.TOP_LEFT].push($("#radius-input")[0]);        

  $("#radius-input").blur(function () {
    console.log('new radius: ' + $( this ).val())
    radius = $( this ).val();
  }); 

  $("#radius-input").on("keypress keyup blur",function (event) {    
     $(this).val($(this).val().replace(/[^\d].+/, ""));
      if ((event.which < 48 || event.which > 57)) {
          event.preventDefault();
      }
  });

  infoWindow = new google.maps.InfoWindow;

  // Try HTML5 geolocation.
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      pos = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };
      infoWindow.setPosition(pos);
      map.setCenter(pos);
      
      userLatLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
      console.log('userLatLng: ' + userLatLng);

      searchMedicalCenters();

      createCircle(userLatLng, getRadius(), map);

      // DEBUG

      console.log('latitude: ' + position.coords.latitude);
      console.log('longitude: ' + position.coords.longitude);
      console.log('radius: ' + getRadius());

    }, function() {
      handleLocationError(true, infoWindow, map.getCenter());
    });
  } else {
    // Browser doesn't support Geolocation
    handleLocationError(false, infoWindow, map.getCenter());
  }        

  map.addListener('click', function(e) {
    var lat = e.latLng.lat();
    var lng = e.latLng.lng();
    console.log('radius ' + getRadius());
    userLatLng = new google.maps.LatLng(lat, lng);
    console.log('new position ' + userLatLng);
    refreshMap(this, userLatLng);
    map.panTo(e.latLng);
  });
}

 function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(browserHasGeolocation ?
                        'Error: The Geolocation service failed.' :
                        'Error: Your browser doesn\'t support geolocation.');
  infoWindow.open(map);
}

function createMarker(place) {
  var placeLoc = place.geometry.location;
  var marker = new google.maps.Marker({
    map: map,
    position: place.geometry.location
  });

  google.maps.event.addListener(marker, 'click', function() {
    infoWindow.setContent(place.name);
    infoWindow.open(map, this);
  });
} 

function createCircle(latLng, radius, map) {
  circle = new google.maps.Circle({
    strokeColor: '#FF0000',
    strokeOpacity: 0.8,
    strokeWeight: 2,
    fillColor: '#FF0000',
    fillOpacity: 0.35,
    map: map,
    center: latLng,
    radius: radius          
  });        
}

function searchMedicalCenters() {
  var service = new google.maps.places.PlacesService(map);
  service.textSearch({
    location: userLatLng,
    radius: getRadius(),
    query: "medical center"
  }, processResults);
}

function processResults(results, status, pagination) {
  if (status === google.maps.places.PlacesServiceStatus.OK) {
    console.log('RESULTS: ' + results.length);
    for (var i = 0; i < results.length; i++) {
      createMarker(results[i]);            
    }
    createRequest(results);
  }
}
  
function refreshMap() {
  circle.setMap(null);
  searchMedicalCenters(map, userLatLng);        
  createCircle(userLatLng, getRadius(), map);
}

function getRadius() {
  radius = parseInt($("#radius-input").val());
  return radius;
}

function createRequest(results) {

  let points = results.map((val, index, arr) => {
    var result = new Object();
    var location = new Object();
    location.lat = val.geometry.location.lat();
    location.lng = val.geometry.location.lng();
    result.location = location;          
    result.distance = calculateDistance(val.geometry.location);
    result.name = val.name;
    return result;
  });  

  var resultsRequest = new Object();
  resultsRequest.points = points;
  resultsRequest.radius = getRadius();
  resultsRequest.status = "OK";
  var user_location = new Object();
  user_location.lat = userLatLng.lat();
  user_location.lng = userLatLng.lng();
  resultsRequest.user_location = user_location;

  json = JSON.stringify(resultsRequest);

  console.log('json: ' + json);
}          

function calculateDistance(location) { 
    var distance = google.maps.geometry.spherical.computeDistanceBetween(location, userLatLng);
    return distance;
}

function callRestService() {

    var postData = [{"logintype":"1","user":"Administrator","password":"12345","controlid":"999","host":"192.168.2.164"}
    ];

    $.ajax({
        url: '//127.0.0.1:8080/process/',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify( postData ), 
        success: function() {
           alert('hello');
        },
        error: function() {
            alert('error');
        }
    });
};
