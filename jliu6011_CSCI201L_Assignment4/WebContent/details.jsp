<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="style.css" rel="stylesheet">
<link href="./assets/fonts/css/all.css" rel="stylesheet">
<style>
.checked {
  color: orange;
}
</style>
<%@ page
	import="csci201.*, java.io.*, java.util.*, org.json.simple.parser.ParseException"%>
</head>

<body>
	<%
		String ID = request.getParameter("restaurantID");
	%>
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

	<div class="container">
		<div class="form-row">
			<div id="search" class="col-7">
				<form action="/action_page.php">
					<input class="form-control mr-sm-2" type="text"
						placeholder="Restaurant Name">
				</form>
			</div>
			<div class="col-1">
				<button type="button" class="btn btn-danger btn-block">
					<i class="fas fa-search"></i>
				</button>
			</div>
			<div class="col-2 text-center">
				<input type="radio" id="bestmatch" name="sortingoption"
					value="bestmatch"> <label for="bestmatch">Best
					Match</label><br> <input type="radio" id="rating" name="sortingoption"
					value="rating"> <label for="radio">Rating</label>
			</div>
			<div class="col-2 text-center">
				<input type="radio" id="reviewcount" name="sortingoption"
					value="reviewcount"> <label for="bestmatch">Review
					Count</label><br> <input type="radio" id="rating" name="sortingoption"
					value="rating"> <label for="radio">Distance</label>
			</div>
		</div>
		<br>
		<div class="form-row">
			<div class="col-4">
				<input class="form-control" type="number" value="latitude"
					id="latitude" placeholder="Latitude">
			</div>
			<div class="col-4">
				<input class="form-control" type="number" value="longitude"
					id="longitude" placeholder="Longitude">
			</div>
			<div class="col-4">
				<button type="button" class="btn btn-primary btn-block">
					<i class="fas fa-map-marker-alt"></i>
					Google Maps (Drop a pin!)
				</button>
			</div>
		</div>
	</div>
	<br>
	
	<!-- Restaurant Holder */ -->
	<%
		try {
			DetailedRestaurant d = YelpWrapper.getDetailedRestaurant(ID);
			String restaurantName = d.getName();
			String img_url = d.getImageURL();
			String restaurantAddress = d.getAddress();
			String restaurantPhone = d.getPhone();
			String restaurantCuisine = d.getCuisine();
			String restaurantPrice = d.getPrice();
			int restaurantRating = d.getRating();
	%>
	<div class="container">
		<h3 class="py-3">
			<%=restaurantName%>
		</h3>
		<hr>
	</div>
	<div class="container">
		<div class="row py-3">
			<div class="col-4 px-4">
				<img src=<%=img_url%>
					id="restaurantThumbnail" class="img-fluid rounded center-block"
					alt="Restaurant image" class="rounded">
			</div>
			<div class="col-8">
				<h5>Address: <%=restaurantAddress%></h5>
				<br>
				<h5>Phone No: <%=restaurantPhone%></h5>
				<br>
				<h5>Cuisine: <%=restaurantCuisine%></h5>
				<br>
				<h5>Price: <%=restaurantPrice%></h5>
				<br>
				<h5>Rating: 
					<% for (int i = 0; i < restaurantRating; i++) {%>
						<i class="fas fa-star checked"></i>
					<% }
						if (restaurantRating < 5) {
							for (int i = 0; i < (5 - restaurantRating); i++) { %>
								<i class="far fa-star"></i>
					<%		}
						}
					%>
					
				</h5>
				
			</div>
		</div>
	</div>
	<%
		} catch (IOException ioe) {

		} catch (ParseException pe) {

		}
	%>
	<br>
	<!-- Favorites & Reservation Buttons -->
	<div class="container">
		<div class="row pb-2">
			<button type="button" class="btn btn-warning btn-lg btn-block"><i class="fas fa-star"></i> Add to Favorites</button>
		</div>
		<div class="row">
			<button type="button" class="btn btn-danger btn-lg btn-block"><i class="far fa-calendar-check"></i> Add Reservation</button>
		</div>
	</div>
	<br><br>
	
	<!-- Reservation Form -->
	<div class="container">
		<div class="form-row">
			<div class="col-6"><input type="date" class="form-control" id="reservationDate" placeholder="Date"></div>
			<div class="col-6"><input type="time" class="form-control" id="reservationTime" placeholder="Time"></div>
		</div>
		<br>
		<div class="form-row">
			<textarea class="form-control" rows="5" id="resNotes" placeholder="Reservation Notes"></textarea>
		</div>
		<br>
		<div class="row">
			<button type="button" class="btn btn-danger btn-lg btn-block"><i class="far fa-calendar-check"></i> Submit Reservation</button>
		</div>
	</div>
	
	<div class="container mt-5">
		<p class="text-center pt-5">Jocelyn Liu Assignment #4</p>
	</div>
	
</body>

</html>