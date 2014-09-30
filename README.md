TechStackPortal
===============

Tech Stack Portal will aim to address the need of 'who has worked on what technology' in the company. The idea is to model a graph for every employee and his technology stack. The usefulness would be:


1.	Search using the technology name or by an employee or by a project.
2.	Different stats like how many projects use a particular technology or what are the rare technologies that arent much used in our company etc.
3.	Exposure for a developer to showcase his unused skills by querying for a project with his desired skill
4.	Analyzing the data over the db could help company manage resources across teams.Also could help company maintain trending         technologies and provide estimates for projects by easily looking into resources with skill set to do so.

As a part of future plan, we can consider extending the graph model to incorporate all the sessions, papers, articles of an employee

After deploying the application, you can use below urls

For searching the graph:   

<b>http://localhost:8080/TechStackPortal/search </b>


For adding an Employee info:

<b>http://localhost:8080/TechStackPortal/addEmployee</b>


Note: The attributes in the add info page are to be added dynamically. <b>Property Type</b> is the attribute type (eg. project,technology,experience) and <b>Property value</b> is the value corresponding to it.(eg.nitman,java,redshift,3)
