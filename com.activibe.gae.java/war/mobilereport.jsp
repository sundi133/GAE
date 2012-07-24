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
		<script type="text/javascript" src="./js/jqplot.json2.min.js"></script>
		

		<link rel="stylesheet" type="text/css" href="./css/jquery.jqplot.min.css" />
		
	 
	</head>
	<body>
		<% String userid= request.getParameter("userid"); %>
		<div id="images">
  		</div>
    	<div id="chart1" style="width:300px; height:400px"></div>
		<div><span>Time : </span><span id="info1"></span> <span id="info2"></span> <span id="info3"></span></div>
    	<pre class="code brush:js"></pre>
    	
    	<a href="javascript:rightslide();">
			<img src="./image/right.png" />
		</a>
		
		<a href="javascript:leftslide();">
			<img src="./image/left.png" />
		</a>

		<script type="text/javascript">
	
		var feel = [];
    	var energy = [];
    	var time = [];
    	var ticks = [];
    	var place = [];
    	var placetype = [];
    	var lat = [];
    	var lon = [];
    	var levels =[0,2,4,6,8,10];
     	var pnum=0; 
	
		$(document).ready(function(){
		
		var ajaxDataRenderer = function(url, plot) {
    	
    	var ret = null;
    	var pnum=0;
        $.ajax({
            // have to use synchronous here, else returns before data is fetched
            async: false,
            url: url,
            dataType:'json',
            success: function(data) {
                $.each(data, function(i,item){
            		 $.each(item, function(property, value) {
       			    	feel.push(value.feel)
						energy.push(value.energy)
						time.push(value.time)
						ticks.push(value.time)
						place.push(value.location)
						placetype.push(value.locationType)
						lat.push(value.lat)
						lon.push(value.lon)
       			  
    				});
    				ret=[feel,energy];
    				
          		});
            }
        });
        return ret;
    	};
		
		var jsonurl = "https://activibealpha.appspot.com/status?opcode=6268&userid="+"<%=userid%>"+"&devicetype=iose&pnum="+pnum;
		var plot1 = $.jqplot('chart1',jsonurl, {
        // The "seriesDefaults" option is an options object that will
        // be applied to all series in the chart.
        dataRenderer: ajaxDataRenderer,
        seriesDefaults:{
            renderer:$.jqplot.BarRenderer,
            rendererOptions: {fillToZero: true}
        },
        seriesColors: ["#ffd732","#c0c0c0"],
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
                	fontSize: '10px',
                	formatString: '%d'
        		}
                
            },
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            yaxis: {
                pad: 1.2,
                tickOptions: {formatString: '%d'},
                min:0, 
                max:10,
                ticks: levels,
                
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


	function rightslide(){
	
	
	}
	
	function leftslide(){
	 	pnum++;
		jsonurl = "https://activibealpha.appspot.com/status?opcode=6268&userid="+"<%=userid%>"+"&devicetype=iose&pnum="+pnum;
		$.ajax({
            // have to use synchronous here, else returns before data is fetched
            async: false,
            url: url,
            dataType:'json',
            success: function(data) {
                $.each(data, function(i,item){
            		 $.each(item, function(property, value) {
       			    	feel.push(value.feel)
						energy.push(value.energy)
						time.push(value.time)
						ticks.push(value.time)
						place.push(value.location)
						placetype.push(value.locationType)
						lat.push(value.lat)
						lon.push(value.lon)
       			  
    				});
    				ret=[feel,energy];
    				
          		});
            }
        });
        return ret;
    	};
		
		
		plot1 = $.jqplot('chart1',jsonurl, {
        // The "seriesDefaults" option is an options object that will
        // be applied to all series in the chart.
        dataRenderer: ajaxDataRenderer,
        seriesDefaults:{
            renderer:$.jqplot.BarRenderer,
            rendererOptions: {fillToZero: true}
        },
        seriesColors: ["#ffd732","#c0c0c0"],
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
                	fontSize: '10px',
                	formatString: '%d'
        		}
                
            },
            // Pad the y axis just a little so bars can get close to, but
            // not touch, the grid boundaries.  1.2 is the default padding.
            yaxis: {
                pad: 1.2,
                tickOptions: {formatString: '%d'},
                min:0, 
                max:10,
                ticks: levels,
                
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
			
	}
	</script>
	</body>
</html>	
	