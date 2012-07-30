<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.activibe.gae.java.dao.ActivibeDataAccessObject" %>
<%@ page import="com.activibe.gae.java.model.ActivibeUpdates" %>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>


<html>
	<head>
		<title>Visualization</title>
		<link rel="stylesheet" type="text/css" href="css/main.css"/>
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

		<link rel="stylesheet" type="text/css" href="./css/jquery.jqplot.min.css" />
		
	 
	</head>
	<body>
		<% String vizcode= request.getParameter("code"); %>
		
    	<div id="chart1" style="width:100%; height:100%"></div>

		<div><span>Time : </span><span id="info1"></span> <span id="info2"></span> <span id="info3"></span></div>
    	
    	<pre class="code brush: js"></pre>
			
		<script type="text/javascript">
		/*
		dynamic add example
		var cosPoints = []; 
  		for (var i=0; i<2*Math.PI; i+=0.1){ 
     		cosPoints.push([i, Math.cos(i)]); 
  		} 
		*/
		$(document).ready(function(){
		
		var feel = [];
    	var energy = [];
    	var time = [];
    	var ticks = [];
    	var place = [];
    	var placetype = [];
    	var lat = [];
    	var lon = [];
     	// Can specify a custom tick Array.
    	// Ticks should match up one for each y value (category) in the series.
   		
		<%
			ActivibeDataAccessObject dao = ActivibeDataAccessObject.INSTANCE;
			List<ActivibeUpdates> updates = dao.getUpdatesForReport(vizcode);
		%>
		<% for( int k=0; k < updates.size(); k++ ){ %>
			feel.push(<%=updates.get(k).getMood_level()%>)
			energy.push( <%=updates.get(k).getEnergy_level()%>)
			time.push("<%=updates.get(k).getUpdate_time()%>")
			ticks.push("<%=updates.get(k).getDate()%>")
			place.push("<%=updates.get(k).getLocation()%>")
			placetype.push("<%=updates.get(k).getLocationType()%>")
			lat.push("<%=updates.get(k).getLatitude()%>")
			lon.push("<%=updates.get(k).getLongitude()%>")
		<% }%>
     
    	var plot1 = $.jqplot('chart1', [feel, energy], {
        // The "seriesDefaults" option is an options object that will
        // be applied to all series in the chart.
        seriesDefaults:{
            renderer:$.jqplot.BarRenderer,
            rendererOptions: {fillToZero: true}
        },
        // Custom labels for the series are specified with the "label"
        // option on the series option.  Here a series option object
        // is specified for each series.
        series:[
            {label:'Feel'},
            {label:'Energy'},
            
        ],
        // Show the legend and put it outside the grid, but inside the
        // plot container, shrinking the grid to accomodate the legend.
        // A value of "outside" would not shrink the grid and allow
        // the legend to overflow the container.
        legend: {
            show: true,
            placement: 'outsideGrid'
        },
        axesDefaults: {
        	tickRenderer: $.jqplot.CanvasAxisTickRenderer ,
        	tickOptions: {
          		//angle: -30,
          		//fontSize: '10pt'
        	}
    	},
        axes: {
            // Use a category axis on the x axis and use our custom ticks.
            xaxis: {
                renderer: $.jqplot.CategoryAxisRenderer,
                ticks: ticks,
                tickOptions:{
                	fontSize: '10px'
        		}
                
            },
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            yaxis: {
                pad: 1.2,
                tickOptions: {formatString: '$%d'}
            }
        }
    });
    
    $('#chart1').bind('jqplotDataHighlight', 
            function (ev, seriesIndex, pointIndex, data) {
                $('#info1').html(time[pointIndex]);
                $('#info2').html(place[pointIndex]); 
                $('#info3').html('('+placetype[pointIndex]+')' + ' - ' + lat[pointIndex] + ', ' + lon[pointIndex]);
                
            }
        ); 
    
});
</script>
	</body>
</html>	
	