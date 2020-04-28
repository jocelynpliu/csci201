<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
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
					<svg class="bi bi-search" width="1em" height="1em"
						viewBox="0 0 16 16" fill="currentColor"
						xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd"
							d="M10.442 10.442a1 1 0 011.415 0l3.85 3.85a1 1 0 01-1.414 1.415l-3.85-3.85a1 1 0 010-1.415z"
							clip-rule="evenodd" />
							<path fill-rule="evenodd"
							d="M6.5 12a5.5 5.5 0 100-11 5.5 5.5 0 000 11zM13 6.5a6.5 6.5 0 11-13 0 6.5 6.5 0 0113 0z"
							clip-rule="evenodd" />
						</svg>
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
				<button type="button" class="btn btn-primary btn btn-block">
					<svg class="bi bi-geo-alt" width="1em" height="1em"
						viewBox="0 0 16 16" fill="currentColor"
						xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd"
							d="M8 16s6-5.686 6-10A6 6 0 002 6c0 4.314 6 10 6 10zm0-7a3 3 0 100-6 3 3 0 000 6z"
							clip-rule="evenodd" />
						</svg>
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

				
	%>
	<div class="container">
		<div class="row py-3">
			<div class="col-4 px-4">
				<img src="https://image.shutterstock.com/image-vector/default-ui-image-placeholder-wireframes-260nw-1037719192.jpg"
					id="restaurantThumbnail" class="img-fluid rounded center-block"
					alt="Restaurant image" class="rounded">
			</div>
			<div class="col-8">
				<h4><%=restaurantName%></h4>
				<h5 class="py-3">ADDRESS PLACEHOLDER</h5>
			</div>
		</div>
	</div>
	<%
		} catch (IOException ioe) {

		} catch (ParseException pe) {

		}
	%>

	<!-- Bootstrap core JavaScript
	    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')
	</script>
	<script src="../../assets/js/vendor/popper.min.js"></script>
	<script src="../../dist/js/bootstrap.min.js"></script>
	<script src="../../assets/js/vendor/holder.min.js"></script>
	<script>
		Holder.addTheme('thumb', {
			bg : '#55595c',
			fg : '#eceeef',
			text : 'Thumbnail'
		});
	</script>
</body>

</html>