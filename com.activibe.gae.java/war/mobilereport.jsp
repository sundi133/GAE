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
    			height: 70%;
			}
			
  		</style>
	

	<style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
    </style>
    
    
	</head>
	<body>
	
		 <% 
		 if(request.getScheme().equalsIgnoreCase("http") || !request.isSecure()){
			
		%>
		<center>	<h3> Sorry, Request cannot be served. </h3> </center>	
		<% } else if(request.isSecure()) 
		{ 
		%>
		<table width="100%">
		<td align="left">
		<a href="javascript:rightslide();" id="right">
			<img src="./images/Left_Arrow.png" height="50" width="50"/>
		</a>
		</td>
		
		<td align="center">
		<div id="date"></div>
		</td>
		
		<td align="right">
		<a href="javascript:leftslide();" id="left">
			<img src="./images/Right_Arrow.png" height="50" width="50"/>
		</a>
		</td>
		</table>
		<% 
		String userid= request.getParameter("userid"); 
		%>
		
		
    	
		<div><span id="info1"></span> <span id="info2"></span> <span id="info3"></span></div>
    	
    	<pre class="code brush:js"></pre>

		<div id="chart1" style="width:100%;"></div>
		
		
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
						document.getElementById('date').innerHTML = dateFunction(value.time)
						ticks.push(value.day_time)
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
		
		var jsonurl = "./status?opcode=6268&userid="+"<%=userid%>"+"&devicetype=iose&pnum="+pnum;
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
            {label:'F'},
            {label:'E'},
            
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
					fontSize: '12px',
                	mark: 'outside',    // Where to put the tick mark on the axis
            		showMark: true,
            		showGridline: true, // wether to draw a gridline (across the whole grid) at this tick,
            		markSize: 4,        // length the tick will extend beyond the grid in pixels.  For
	                show: true,         // wether to show the tick (mark and label),
            		showLabel: true,    // wether to show the text label at the tick,
            		formatString: '',   // format string to use with the axis tick formatter
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
                //$('#info1').html(time[pointIndex]);
                //$('#info2').html(place[pointIndex]); 
                //$('#info3').html('('+placetype[pointIndex]+')' + ' - ' + lat[pointIndex] + ', ' + lon[pointIndex]);
                
            }
     ); 

		    
     $('#chart1').bind('jqplotDataClick', 
            function (ev, seriesIndex, pointIndex, data) {
                //alert(lat[pointIndex] + ', ' + lon[pointIndex])
            	//displayMap(lat[pointIndex],lon[pointIndex],place[pointIndex],10,placetype[pointIndex],ticks[pointIndex]);
            	
            	displayMap(lat[pointIndex],lon[pointIndex],place[pointIndex],10,placetype[pointIndex],ticks[pointIndex]);
            	
            	
            }
      );
	
	});


	function rightslide(){
	
		pnum--;
		cleardata();
	 	var ajaxDataRenderer = function(url, plot) {
    	var ret = null;
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
						document.getElementById('date').innerHTML = dateFunction(value.time)
						ticks.push(value.day_time)
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
		
		var jsonurl = "./status?opcode=6268&userid="+"<%=userid%>"+"&devicetype=iose&pnum="+pnum;
		
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
            {label:'F'},
            {label:'E'},
            
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
        	tickOptions:{
                	fontSize: '12px',
                	mark: 'outside',    // Where to put the tick mark on the axis
            		showMark: true,
            		showGridline: true, // wether to draw a gridline (across the whole grid) at this tick,
            		markSize: 4,        // length the tick will extend beyond the grid in pixels.  For
	                show: true,         // wether to show the tick (mark and label),
            		showLabel: true,    // wether to show the text label at the tick,
            		formatString: '',   // format string to use with the axis tick formatter
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
                //$('#info1').html(time[pointIndex]);
                //$('#info2').html(place[pointIndex]); 
                //$('#info3').html('('+placetype[pointIndex]+')' + ' - ' + lat[pointIndex] + ', ' + lon[pointIndex]);
                
                
            }
     ); 
     
      $('#chart1').bind('jqplotDataClick', 
            function (ev, seriesIndex, pointIndex, data) {
                
                //alert(lat[pointIndex] + ', ' + lon[pointIndex])
            	displayMap(lat[pointIndex],lon[pointIndex],place[pointIndex],10,placetype[pointIndex],ticks[pointIndex]);
            }
      );
	
    
	
	
	
	}
	
	function dateFunction(str){
	// str format should be mm/dd/yyyy - hr:mm. Separator can be anything e.g. / or -. It wont effect
	var mon   = parseInt(str.substring(0,2),10)-1;
	var dat  = parseInt(str.substring(3,5),10);
	var yr   = parseInt(str.substring(6,10),10);
	var date = new Date(yr, mon, dat);
	return date.toDateString();
	}
	
	function cleardata(){
		jQuery("#chart1").html('');
		feel = [];
    	energy = [];
    	time = [];
    	ticks = [];
    	place = [];
    	placetype = [];
    	lat = [];
    	lon = [];
      
	}
	
	function leftslide(){
	 	pnum++;
		cleardata();
	 	var ajaxDataRenderer = function(url, plot) {
    	var ret = null;
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
						document.getElementById('date').innerHTML = dateFunction(value.time)
						ticks.push(value.day_time)
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
		
		var jsonurl = "./status?opcode=6268&userid="+"<%=userid%>"+"&devicetype=iose&pnum="+pnum;
		
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
            {label:'F'},
            {label:'E'},
            
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
                	fontSize: '12px',
                	mark: 'outside',    // Where to put the tick mark on the axis
            		showMark: true,
            		showGridline: true, // wether to draw a gridline (across the whole grid) at this tick,
            		markSize: 4,        // length the tick will extend beyond the grid in pixels.  For
	                show: true,         // wether to show the tick (mark and label),
            		showLabel: true,    // wether to show the text label at the tick,
            		formatString: '',   // format string to use with the axis tick formatter
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
                //$('#info1').html(time[pointIndex]);
                //$('#info2').html(place[pointIndex]); 
                //$('#info3').html('('+placetype[pointIndex]+')' + ' - ' + lat[pointIndex] + ', ' + lon[pointIndex]);
                
            }
     ); 
    
     $('#chart1').bind('jqplotDataClick', 
            function (ev, seriesIndex, pointIndex, data) {
            	
            	displayMap(lat[pointIndex],lon[pointIndex],place[pointIndex],10,placetype[pointIndex],ticks[pointIndex]);
            			
            }
      );
	
			
	}
	
	function back(){
	 		
	}
	 
	function displayMap(lat,lon,place,zoomlevel,type,time){
	     		location.href="./maps.jsp?lat="+lat+"&lon="+lon+"&place="+place+"&zoomlevel="+zoomlevel+"&type="+type+"&time="+time;
    }
	 
	</script>
	<% } %>
	</body>
</html>	
	