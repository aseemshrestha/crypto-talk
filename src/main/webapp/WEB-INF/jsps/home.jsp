<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>News</h2>

	<c:if test="${not empty news}">
 <h1>${news.status}</h1>
		<ul>
		  
			<c:forEach var="listValue" items="${news.articles}">
			 
			</c:forEach>
		</ul>

	</c:if>
</body>
</html>