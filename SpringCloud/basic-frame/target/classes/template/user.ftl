<html>
<head>
    <title>列表</title>
</head>
<body>
<p>Our latest product:

<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>age</th>
    </tr>
<#list users as user>
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${ user.age }</td>
    </tr>
</#list>
</table>
</body>
</html>