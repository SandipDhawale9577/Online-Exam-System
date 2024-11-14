
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
table,th,td{
border:1px solid magenta;
}
table{
margin:auto;

}
th,td{
padding:10px;
}
</style>
<script>
sessionStorage.removeItem('timeleft');
</script>
</head>

<body>
<table>
<tr>
<th>qno</th>
<th>question</th>
<th>submittedAnswer</th>
<th>originalAnswer</th>
</tr>
<c:forEach var="answer" items="${allanswer}">
<tr>

<td>${answer.qno}</td>
<td>${answer.question}</td>
<td>${answer.submittedAnswer}</td>
<td>${answer.originalAnswer}</td>
</tr>
</c:forEach>
</table>
<h1> final score  ${score} </h1>
</body>
</html>