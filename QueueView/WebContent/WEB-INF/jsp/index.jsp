<%@page contentType="text/html" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE HTML>
<html>
<head>
<title>QueueView</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css" />
<!--   -->
<script>

var x,y,n=0,ny=0,rotINT,rotYINT
function rotateDIV()
{
x=document.getElementById("rotate2D")
clearInterval(rotINT)
rotINT=setInterval("startRotate()",10)
}
function rotateYDIV()
{
y=document.getElementById("CallerBox")
clearInterval(rotYINT)
rotYINT=setInterval("startYRotate()",10)
}
function startRotate()
{
n=n+1
x.style.transform="rotate(" + n + "deg)"
x.style.webkitTransform="rotate(" + n + "deg)"
x.style.OTransform="rotate(" + n + "deg)"
x.style.MozTransform="rotate(" + n + "deg)"
if (n==180 || n==360)
{
clearInterval(rotINT)
if (n==360){n=0}
}
}
function startYRotate()
{
ny=ny+1
y.style.transform="rotateY(" + ny + "deg)"
y.style.webkitTransform="rotateY(" + ny + "deg)"
y.style.OTransform="rotateY(" + ny + "deg)"
y.style.MozTransform="rotateY(" + ny + "deg)"
if (ny==180 || ny>=360)
{
clearInterval(rotYINT)
if (ny>=360){ny=0}
}
}
//-->
</script>
</head>

<body onload=callCol()>

	<div class="header">
		<c:out value="${name}"> Test</c:out>
	</div>

	<%
		response.setIntHeader("Refresh", 5);
	%>
	<form id="queueForm">

		<div class="topArea">

			<div id="HoldBox" class="topRow">
				<p>Average Wait Time:</p>
				<p id="hold" class="dynVal">
					<c:out value="${holdTime}">0 </c:out>
				</p>
			</div>
			<div onmouseover="rotateYDIV()" id="CallerBox" class="topRow">
				<p># of Callers in Queue:</p>
				<p id="call" class="dynVal">
					<c:out value="${numCalls}">0 </c:out>
				</p>
			</div>
			<div id="ALogBox" class="topRow">
				<p>Agents Logged in:</p>
				<p id="alog" class="dynVal">
					<c:out value="${numAgentsLog}"> 0</c:out>
				</p>
			</div>
			<div id="AAvaBox" class="topRow">
				<p>Agents Not On a Call:</p>
				<p id="aava" class="dynVal">
					<c:out value="${numAgentsAva}"> 0</c:out>
				</p>
			</div>

		</div>

		<div class="bottomArea">
			<table border="1">
				<%
					String[] AgentsArray = (String[]) request.getAttribute("Agents");
					for (int i = 0; i < AgentsArray.length; i++) {
				%>
				<tr>
					<td><%=AgentsArray[i]%></td>
				</tr>
				<%
					}
				%>
			</table>

		</div>

	</form>

	<script type="text/javascript">
	callCol = function(){
		var callVal = ${numCalls};//document.getElementById("CallerVal").value;
		var callBox = document.getElementById("CallerBox");
		
		if (callVal <=3){
			callBox.style.backgroundColor = '#3ADD35';
		}else if (callVal > 3 && callVal <= 6){
			callBox.style.backgroundColor = '#FFE83F';
		}else if (callVal > 6){
			callBox.style.backgroundColor = '#FC3737';
		}
	}
	</script>
</body>
</html>