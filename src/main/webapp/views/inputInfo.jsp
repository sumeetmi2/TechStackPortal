<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Tech Stack Portal</title>
<script type="text/javascript">
var fieldCount =0;
	function addFields(){
		fieldCount++;
		var table = document.getElementById("projTable");
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		
		rowCount = rowCount+1;
		
		var cell1 = row.insertCell(0);
		cell1.innerHTML = "Project";
		
		var cell2 = row.insertCell(1);
		cell2.innerHTML += '<input type="text" name="projects['+fieldCount+'].name"/>';
		
		var row1 = table.insertRow(rowCount);
		rowCount++;
		var cell3 = row1.insertCell(0);
		cell3.innerHTML = "Technologies used";
		
		var cell4 = row1.insertCell(1);
		cell4.innerHTML += '<input type="text" name="projects['+fieldCount+'].techStr"/>';
	}
	
</script>
</head>
<body>
<h1>Please enter your details</h1>
	<form:form method="POST" action="./addEmployee" id="form0">
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="id">Emp Code</form:label></td>
				<td><form:input path="id" /></td>
			</tr>
			<tr>
				<td>
					<table style="border: thin;" id="projTable">
						<tr>
							<td><form:label path="projects[0].name">Project</form:label></td>
							<td><form:input path="projects[0].name" /><input type="button" onclick="addFields()" value="Add"/></td>
						</tr>
						<tr>
							<td>Technologies used</td>
							<td><input type="text" name="projects[0].techStr"/></td>				
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="submit"/>
	</form:form>
</body>
</html>