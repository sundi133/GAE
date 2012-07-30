
<!DOCTYPE html>
<html>
  <head>
    <title>Google Maps JavaScript API v3 Example: Map Simple</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">

    <style>
      html, body, #map_canvas {
        margin: 0;
        padding: 0;
        height: 100%;
      }
      
	.bubble{ 
	font:18px sans-serif bold; 
	color:#600; 
	background:#ffc;
	}
    </style>
    <link href="https://developers.google.com/maps/documentation/javascript/examples/default.css" rel="stylesheet">
   
    <script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <script>
      var map;
      function initialize() {

      <% String lat= request.getParameter("lat"); %>
	  <% String lon= request.getParameter("lon"); %>
      <% String place= request.getParameter("place"); %>
      <% String zoomlevel= request.getParameter("zoomlevel"); %>
      <% String type= request.getParameter("type"); %>
	  <% String time= request.getParameter("time"); %>
      
        var mapOptions = {
          zoom: <%=zoomlevel%>,
          center: new google.maps.LatLng(<%=lat%> , <%=lon%> ),
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        
        map = new google.maps.Map(document.getElementById('map_canvas'),mapOptions);
      	
      	var latlng = new google.maps.LatLng(<%=lat%> , <%=lon%> );
      	
        var marker = new google.maps.Marker({
            position: latlng,
            map: map,
            title: 'Activibe'
        });

		var coordInfoWindow = new google.maps.InfoWindow();
        coordInfoWindow.setContent("<span class='bubble'>" +"Name: "+"<%=place%><br/>" + "Type: " + "<%=type%><br/>" +"Time: " + "<%=time%><br/></span>");
        coordInfoWindow.setPosition(latlng);
        coordInfoWindow.open(map);
		
      }
    </script>
  </head>
  <body onload="initialize()">
  	<div style="position: relative; left: 0; top: 0; "  align="center">
  	<img src="./images/backmaps.png"/ onclick="history.go(-1);return false;">
  	</div>

    <div id="map_canvas"></div>
    
  </body>
</html>

	