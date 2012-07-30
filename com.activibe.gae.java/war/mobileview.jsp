<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.activibe.gae.java.dao.ActivibeDataAccessObject" %>
<%@ page import="com.activibe.gae.java.model.ActivibeUpdates" %>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>


<html>
	<head>
		<title>Visualization</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		
		<meta charset="utf-8">
		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/jquery.jqplot.min.js"></script>
		<script type="text/javascript" src="./js/jqplot.barRenderer.min.js"></script>
		<script type="text/javascript" src="./js/jqplot.categoryAxisRenderer.min.js"></script>
		<script type="text/javascript" src="./js/jqplot.pointLabels.min.js"></script>
		<script type="text/javascript" src="./js/jqplot.barRenderer.min.js"></script>
		<script type="text/javascript" src="./js/jqplot.categoryAxisRenderer.min.js"></script>
		<script type="text/javascript" src="./js/jqplot.pointLabels.min.js"></script>
		<script type="text/javascript" src="./js/jqplot.canvasTextRenderer.min.js"></script>
		<script type="text/javascript" src="./js/jqplot.canvasAxisTickRenderer.min.js"></script>
		<script type="text/javascript" src="./js/jqplot.dateAxisRenderer.min.js"></script>
		<script type="text/javascript" src="./js/jqplot.json2.min.js"></script>
		

		<link rel="stylesheet" type="text/css" href="./css/jquery.jqplot.min.css" />
		
		<style type="text/css">
  			body {
 			   padding: 0;
    		   margin: 0;
			}

			html, body, #chart1 {
    			height: 94%;
			}
  		</style>

	<style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map_canvas { height: 100% }
    </style>
    
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?v=3&key=AIzaSyB3-U75Mygad4Mcw388vscovRAiIEbCXOo&sensor=false">
    </script>	 
    
		<meta charset="utf-8">
		<meta name="viewport" content="width=1024">
		<title>Example 3 - Animated Bar Chart via jQuery</title>
		<link rel="stylesheet" href="css/common.css">
		<link rel="stylesheet" href="css/03.css">

	 <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0a1/jquery.mobile-1.0a1.min.css" />
  	 <script src="http://code.jquery.com/jquery-1.4.3.min.js"></script>
  	 <script src="http://code.jquery.com/mobile/1.0a1/jquery.mobile-1.0a1.min.js"></script>
	
			    
	</head>
	<body>
		
		<% String userid= request.getParameter("userid"); %>
		
		<div data-role="page" id="home">
 
  		<div data-role="header">
    		<h1>Report</h1>
  		</div>
 
  <div data-role="content"> 
    <div class="chart">
				<table id="data-table" border="1" cellpadding="10" cellspacing="0" summary="Activibe Report">
					<thead>
						<tr>
							<td>&nbsp;</td>
							<th scope="col">2012</th>
							<th scope="col">2013</th>
							<th scope="col">2014</th>
							<th scope="col">2015</th>
							<th scope="col">2016</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">Carbon Tiger</th>
							<td onclick="#about">4080</td>
							<td>6080</td>
							<td>6240</td>
							<td>3520</td>
							<td>2240</td>
						</tr>
						<tr>
							<th scope="row">Blue Monkey</th>
							<td>5680</td>
							<td>6880</td>
							<td>5760</td>
							<td>5120</td>
							<td>2640</td>
						</tr>
						
					</tbody>
				</table>
			</div>
        
  </div>
 
</div>
 
<div data-role="page" id="about">
 
  <div data-role="header">
    <h1>About This App</h1>
  </div>
 
  <div data-role="content"> 
    <p>This app rocks! <a href="#home">Go home</a></p>    
  </div>
 
</div>
  
    			

		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
		<script src="js/03.js"></script>
	</body>
	
</html>	
	