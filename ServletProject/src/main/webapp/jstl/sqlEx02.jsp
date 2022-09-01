<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- update --%>
<sql:update dataSource="jdbc/myoracle">
update tempmember set passwd=? where id=?
<sql:param value="${2222}"/>
<sql:param value="aaaa"></sql:param>
</sql:update>

<sql:query var="rs" dataSource="jdbc/myoracle">
select * from tempmember
</sql:query>
<table border="1">
	<tr><%-- 필드명 출력 --%>
		<c:forEach var="columnName" items="${rs.columnNames }">
			<th><c:out value="${columnName }"/></th>
		</c:forEach>
	</tr>

<%-- 레코드 수만큼 반복 처리 --%>
<c:forEach var="row" items="${ rs.rowsByIndex}">
	<tr>
		<c:forEach var="column" items="${row}" varStatus="i">
			<td>
				<%-- 해당 필드값이 null이 아니면 --%>
				<c:if test="${column != null }">
					<c:out value="${column }"/>
				</c:if>
				
				<%-- 해당 필드값이 null일떄 --%>
				<c:if test="${column == null }">
					&nbsp;
				</c:if>
			</td>
		</c:forEach>
	</tr>
</c:forEach>

</table>


</body>
</html>