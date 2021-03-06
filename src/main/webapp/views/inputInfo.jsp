<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<link rel="stylesheet" href="<c:url value="/resources/css/formoid-solid-green.css"/>" type="text/css" />
<head>
<title>Add Employee Info</title>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/formoid-solid-green.js"/>"></script>
<script type="text/javascript">
	window.onload = onload;
	
	$(document).ready(function(){
	    $(".formoid-solid-green input[type='text']").val(""); 
	});
</script>
</head>
<body class="blurBg-false" style="background-color:#EBEBEB">
<b>${duplicateEmpCode}</b>
<form:form class="formoid-solid-green" style="background-color:#FFFFFF;font-size:14px;font-family:'Roboto',Arial,Helvetica,sans-serif;color:#34495E;max-width:480px;min-width:150px" method="post">
	<div class="title"><h2>Add Employee Info</h2></div>
	<div class="element-name">
		<form:label path="firstName" class="title">
			<span class="required">*</span>
		</form:label>
		<span class="nameFirst">
			<form:input path="firstName" id="firstName" placeholder="Name First" type="text" size="8" required="required"/>
			<span class="icon-place"></span>
		</span><span class="nameLast">
			<form:input path="lastName" id="lastName" placeholder="Name Last" type="text" size="14" required="required"/>
			<span class="icon-place"></span>
		</span>
	</div>
	<div class="element-input" title="Employee Id">
		<form:label path="id" class="title">
			<span class="required">*</span>
		</form:label>
		<div class="item-cont">
			<form:input path="id" class="large" type="text" name="input" required="required" placeholder="Emp Id"/>
			<span class="icon-place"></span>
		</div>
	</div>
	
	<div class="composite_field">
		<a style="float:right;" lang="addDomainBoxes" href="javascript:;" class="addElement addCreative addCreativeTextArea creative_element_add_link">Add</a>&nbsp;
		<!--a style="float:right;" lang="deleteDomainBoxes" href="javascript:;" class="deleteElement addCreative deleteCreativeTextArea creative_element_delete_link" style="margin-left: 10px;">Delete</a-->
		
		<fieldset>
			<div class="element-input" title="Properties">
				<label class="title">
				</label>
				<div class="item-cont">
					<form:input path="props[0].name" class="large" type="text" name="input1" required="required" placeholder="Property type"/>
					<span class="icon-place"></span>
				</div>
			</div>
			<div class="element-input" title="Values">
				<label class="title"></label>
				<div class="item-cont">
					<form:input path="props[0].value" class="large" type="text" name="input2" placeholder="Property Value"/>
					<span class="icon-place"></span>
				</div>
			</div>
			
		</fieldset>
	</div>
	<div class="submit"><input onclick="javascript:onsubmitemployee();" type="submit" value="Submit"/>
	</div>	
</form:form>

</body>
</html>