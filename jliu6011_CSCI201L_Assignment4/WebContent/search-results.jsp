<%@ page language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Results</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<link href="./assets/fonts/css/all.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>
<script>
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function() {
		'use strict';
		window.addEventListener('load', function() {
			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.getElementsByClassName('needs-validation');
			// Loop over them and prevent submission
			var validation = Array.prototype.filter.call(forms, function(form) {
				form.addEventListener('submit', function(event) {
					if (form.checkValidity() === false) {
						event.preventDefault();
						event.stopPropagation();
					}
					form.classList.add('was-validated');
				}, false);
			});
		}, false);
	})();
</script>
<%@ page import="csci201.*, java.io.*, java.util.*, org.json.simple.parser.ParseException"%>
</head>

<body>
	<%
		String name = request.getParameter("name");
		String sortingoption = request.getParameter("sortingoption");
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));
	%>
	<!-- Nav bar -->
	<div class="bg-white border-bottom box-shadow">
		<div
			class="container d-flex flex-column flex-md-row align-items-center py-4">
			<h1 class="display-4 mr-md-auto font-weight-normal">
				<a id="logo" href="index.html">SalEats!</a>
			</h1>
			<nav class="my-2 my-md-0 mr-md-3">
				<a class="p-2 text-dark" href="index.html">Home</a> <a
					class="p-2 text-dark" href="login-sign-up.html">Login / Signup</a>
			</nav>
		</div>
	</div>

	<br>
<!-- INPUTS -->
	<form class="container needs-validation" novalidate method="POST"
		action="search-results.jsp">
		<!-- Search Bar -->
		<div class="form-row">
			<div id="search" class="col-7">
				<input name="name" class="form-control mr-sm-2" type="text"
					placeholder="Restaurant Name" value="<%=name%>" required="true">
				<div class="invalid-feedback">Please enter a restaurant name.</div>
			</div>
			<div class="col-1">
				<button type="submit" class="btn btn-danger btn-block">
					<i class="fas fa-search"></i>
				</button>
			</div>
			<!-- Radio Buttons -->
			<div class="col-2 text-center">
				<input type="radio" id="best_match" name="sortingoption"
					value="best_match"> <label for="best_match" >
					Best Match</label><br> <input type="radio" id="rating"
					name="sortingoption" value="rating"> <label
					for="radio">Rating</label>
			</div>
			<div class="col-2 text-center">
				<input type="radio" id="review_count" name="sortingoption"
					value="review_count"> <label for="distance">Review
					Count</label><br> <input type="radio" id="distance"
					name="sortingoption" value="distance"> <label
					for="radio">Distance</label>
			</div>
			

		</div>
		<br>
		<!-- Latitude Longitude -->
		<div class="form-row">
			<div class="col-4">
				<input class="form-control" name="latitude" type="number"
					step="0.000000000000001" value="<%=latitude%>" id="latitude"
					placeholder="Latitude" required="true">
				<div class="invalid-feedback">Please enter a latitude.</div>
			</div>
			<div class="col-4">
				<input class="form-control" name="longitude" type="number"
					step="0.000000000000001" value="<%=longitude%>" id="longitude"
					placeholder="Longitude" required="true">
				<div class="invalid-feedback">Please enter a longitude.</div>
			</div>
			<div class="col-4">
				<button id="mapbutton" type="button"
					class="btn btn-primary btn btn-block">
					<i class="fas fa-map-marker-alt"></i>
					Google Maps (Drop a pin!)
				</button>
			</div>
		</div>
	</form>
	<br>
	<!-- Results for "{restaurant name}" -->
	<div class="container">
		<h3 class="py-3">
			Results for "<%=name%>"
		</h3>
		<hr>
	</div>

	<!-- Map Overlay -->
	<div id="overlay">
		<div id="map"></div>
		<script>
			function initMap() {
				var myLatLng = {
					lat : <%=latitude%>,
					lng : <%=longitude%>
				};

				var map = new google.maps.Map(document.getElementById('map'), {
					zoom : 8,
					center : myLatLng
				});

				var marker = new google.maps.Marker({
					position : myLatLng,
					map : map,
					draggable : true,
					title : "Drag me!"
				});

				map.addListener('mousemove', function() {
					document.getElementById("latitude").value = marker
							.getPosition().lat();
					document.getElementById("longitude").value = marker
							.getPosition().lng();

				})

				marker.addListener('click', function() {
					map.setZoom(8);
					map.setCenter(marker.getPosition());

				});
			}
		</script>
	</div>

	<!-- Map JavaScript -->
	<script>
		$("#mapbutton").on('click', function(event) {
			$("#overlay").css('display', 'block');
			event.stopPropagation();
		});

		$(document).on('click', function() {
			$("#overlay").css('display', 'none');
		});

		$("#overlay").on('click', function(event) {
			event.stopPropagation();
		});
	</script>

	<!-- Restaurant Holder */ -->
	<%
		try {
			Vector<Restaurant> restaurants = new Vector<Restaurant>();
			restaurants = YelpWrapper.getRestaurants(name, latitude, longitude, sortingoption);
			for (int i = 0; i < restaurants.size(); i++) {
				Restaurant r = restaurants.get(i);
				String restaurantName = r.getName();
				String restaurantAddress = r.getAddress();
				String restaurantUrl = r.getUrl();
				String restaurantImgUrl = r.getImageUrl();
				String restaurantID = r.getID();
	%>
	<form class="container" novalidate method="POST" action="details.jsp">
		<div class="row py-3">
			<div class="col-4 px-4">
			 	<input type="hidden" id="restaurantID" name="restaurantID" value=<%=restaurantID%>>
				<input type="image" id="restaurantThumbnail" class="img-fluid rounded center-block"
						src="<%=restaurantImgUrl%>" alt="Restaurant image" class="rounded">
			</div>
			<div class="col-8">
				<h4><%=restaurantName%></h4>
				<h5 class="py-3"><%=restaurantAddress%></h5>
				<a href="<%=restaurantUrl%>">
					<h5 class="trimText"><%=restaurantUrl%></h5>
				</a>
			</div>
		</div>
		<hr>
	</form>
	<%
		}
		} catch (IOException ioe) {

		} catch (ParseException pe) {

		}
	%>

	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAspLi8elpRrsi0JEZF7WWeaiwZjffdixw&callback=initMap"
		async defer>
	</script>

</body>

</html>