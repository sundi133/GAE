<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.activibe.gae.java.model.Todo" %>
<%@ page import="com.activibe.gae.java.dao.Dao" %>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<head>
  <meta name="msvalidate.01" content="73FBA04BA3B5DA1A28909E7CE8916F80" />
  <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
  <script type="text/javascript" src="http://www.google.com/jsapi"></script>
  
  <script type="text/javascript">
  google.load("gdata", "1.x");
  google.setOnLoadCallback(initFunc);
  
  var contactsService;

function setupContactsService() {
  contactsService = new google.gdata.contacts.ContactsService('twittercontacts');
}

function logMeIn() {
  var scope = 'https://www.google.com/m8/feeds';
  var token = google.accounts.user.login(scope);
}

function initFunc() {
  setupContactsService();
  logMeIn();
  getMyContacts();
}

function getMyContacts() {
  var contactsFeedUri = 'https://www.google.com/m8/feeds/contacts/default/full';
  var query = new google.gdata.contacts.ContactQuery(contactsFeedUri);
  
  // Set the maximum of the result set to be 5
  query.setMaxResults(5000);
  
  contactsService.getContactFeed(query, handleContactsFeed, handleError);
}

var handleContactsFeed = function(result) {
  var entries = result.feed.entry;

  for (var i = 0; i < entries.length; i++) {
    var contactEntry = entries[i];
    console.log(contactEntry)
    var emailAddresses = contactEntry.getEmailAddresses();
    var name = contactEntry.getTitle().getText()
    for (var j = 0; j < emailAddresses.length; j++) {
      var emailAddress = emailAddresses[j].getAddress();
      //alert('email = ' + emailAddress);
      addToTable(name,emailAddress)
    }    
  }
}

function addToTable(name,email){
 var table = document.getElementById("dataTable");
 
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
 
            var cell1 = row.insertCell(0);
            //var element1 = document.createElement("input");
            //element1.type = "checkbox";
            cell1.innerHTML = rowCount ;
 
            var cell2 = row.insertCell(1);
            cell2.innerHTML = name + ", " + "(" + email + ")";
 
            var cell3 = row.insertCell(2);
            var element2 = document.createElement("a");
            element2.setAttribute('href', 'javascript:showTwitterHandle(' +"'"+ name +"'"+ ',' +"'"+ email +"'"+ ')' );
            var linkText=document.createTextNode('get Twitter Handle');
  			element2.appendChild(linkText);
            cell3.appendChild(element2);
 
}
function logMeOut() {
  google.accounts.user.logout();
}
function handleError(e) {
  alert("There was an error!");
  alert(e.cause ? e.cause.statusText : e.message);
}


  </script>
  
  <script type="text/javascript">

function createRequestObject() {
    var tmpXmlHttpObject;
    
    //depending on what the browser supports, use the right way to create the XMLHttpRequest object
    if (window.XMLHttpRequest) { 
        // Mozilla, Safari would use this method ...
        tmpXmlHttpObject = new XMLHttpRequest();
	
    } else if (window.ActiveXObject) { 
        // IE would use this method ...
        tmpXmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    return tmpXmlHttpObject;
}

//call the above function to create the XMLHttpRequest object
var http = createRequestObject();

function showTwitterHandle(name,email) {
	document.getElementById('description').innerHTML = "Fetching...";
    //make a connection to the server ... specifying that you intend to make a GET request 
    //to the server. Specifiy the page name and the URL parameters to send
    http.open('get', 'new?userTweet=' + name+"," + email);
	
    //assign a handler for the response
    http.onreadystatechange = processResponse;
	
    //actually send the request to the server
    http.send(null);
}

function processResponse() {
    //check if the response has been received from the server
    if(http.readyState == 4){
	
        //read and assign the response from the server
        var response = http.responseText;
		
        //do additional parsing of the response, if needed
		
        //in this case simply assign the response to the contents of the <div> on the page.
         
        document.getElementById('description').innerHTML = response;
        document.getElementById('description').display = "block";
        document.getElementById('clodeb').display = "block";
        //alert(response)
		
        //If the server returned an error message like a 404 error, that message would be shown within the div tag!!. 
        //So it may be worth doing some basic error before setting the contents of the <div>
    }
}

function closeDiv(){
	document.getElementById('description').display = "none";
	document.getElementById('clodeb').display = "none";
}
</script>
  
</head>
<body>
  <!-- Google requires an image to be on the page -->
  <img src="./google.png">
  
  
  <input id="numContacts" placeholder="number of contacts" type="text" style="visibility:hidden">
  
  <!--button type="button" onclick="initFunc()">Click Me!</button-->
  <div id="description" style="width:200; margin:0 auto;position:fixed;top:50%;
    left:35%;  background:#eee;z-index:100;
    border:1px solid #ddd;">  </div>
    
  <a href="javascript:closeDiv();" style="width:200; margin:0 auto;position:fixed;top:40%;
    left:25%;  background:#eee;z-index:101;
    border:1px solid #ddd;display:block;"
    id="clodeb">close</a>  
  <TABLE id="dataTable" width="350px" border="1">
        <TR>
        	<TD>Number</TD>
            <TD>Email Id</TD>
            <TD>Click to get TwitterHandle </TD>
        </TR>
    </TABLE>
 
</body>
</html>