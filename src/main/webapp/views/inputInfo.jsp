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
		cell4.innerHTML += '<input type="text" name="technologies'+fieldCount+'" id="technologies'+fieldCount+'"/>';
	}
	
	function onSubmit(){
		for(var i=0;i<=fieldCount;i++){
			var techstr = document.getElementById("technologies"+i).value;
			var tech = techstr.split(",");
			for(var j=0;j<tech.length;j++){
				var ele1 = document.createElement("input");
				ele1.setAttribute("type","hidden");
				ele1.setAttribute("name","projects["+i+"].technologies["+j+"]");
				ele1.setAttribute("value",tech[j]);	
				document.getElementById("form0").appendChild(ele1);
			}
		}
		document.forms[0].submit();
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
							<td><input type="text" id="technologies0"/></td>				
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="button" onclick="onSubmit()" value="Submit"/>
	</form:form>
</body>
</html>