<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<json:object>
    <c:forEach items="${donazioni}" varStatus="loop" var="donazione">
        <json:property name="${loop.count}" value="${donazione.orario}//*-*-*//${donazione.quantita}//*-*-*//${donazione.sessione.data}//*-*-*//${donazione.note}"/>
    </c:forEach>
</json:object>
