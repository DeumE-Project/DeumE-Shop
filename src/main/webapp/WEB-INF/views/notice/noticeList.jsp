<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<html>
<head>
    <title>공지사항</title>
    <style>
        .wider-column {
            width: 70%;
        }
        .float-end {
            float: right;
        }
        a{
            color: black;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <br>
    <h1>공지사항</h1><br>

    <div class="d-flex justify-content-end mb-3">
        <a href="${pageContext.request.contextPath}/notice/save" class="btn btn-primary float-end">글작성</a>
    </div>

    <div class="d-flex justify-content-center">
        <form class="text-center" action="/notice/search" method="get">
            <input type="hidden" name="page" id="page" value="1">
            <div class="input-group">
                <select class="form-select" name="searchField" id="searchField" style="width: 25%">
                    <option class="title" value="title" selected>제목</option>
                    <option class="content" value="content">내용</option>
                </select>
                <input type="text" class="form-control" name="searchWord" id="searchWord" style="width: 55%"
                       value="<c:out value="${searchWord}"/>">&nbsp
                <button type="submit" class="btn btn-primary" style="width: 17%">검색</button>
            </div>
        </form>
    </div>

    <br>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr align="center">
            <th style="width: 5%">번호</th>
            <th class="wider-column">제목</th>
            <th style="width: 10%">작성일</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${noticeList}" var="notice" varStatus="loop">
            <tr>
                <td align="center">${paging.totalPage - ((paging.page - 1) * paging.pageLimit + loop.index)}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/notice?idx=${notice.noticeIdx}&page=${paging.page}"
                       class="text-decoration-none"><c:out value="${notice.noticeTitle}"/> </a>
                </td>
                <td align="center">${notice.noticeDateStr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="d-flex justify-content-center mt-3">
        <ul class="pagination">

            <%--검색 전 페이징--%>
            <c:if test="${searchWord eq null}">
                <c:choose>
                    <c:when test="${paging.page <= 1}">
                        <li class="page-item disabled">
                            <span class="page-link">이전</span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/notice/?page=${paging.page - 1}" aria-label="Previous">이전</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
                    <c:choose>
                        <c:when test="${i eq paging.page}">
                            <li class="page-item active" aria-current="page">
                                <span class="page-link">${i}</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/notice/?page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${paging.page >= paging.maxPage}">
                        <li class="page-item disabled">
                            <span class="page-link">다음</span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/notice/?page=${paging.page + 1}" aria-label="Next">다음</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <%--검색 후 페이징--%>
            <c:if test="${searchWord ne null}">
                <c:choose>
                    <c:when test="${paging.page <= 1}">
                        <li class="page-item disabled">
                            <span class="page-link">이전</span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="/notice/search?page=${paging.page - 1}&searchField=${searchField}&searchWord=${searchWord}" aria-label="Previous">이전</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
                    <c:choose>
                        <c:when test="${i eq paging.page}">
                            <li class="page-item active" aria-current="page">
                                <span class="page-link">${i}</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="/notice/search?page=${i}&searchField=${searchField}&searchWord=${searchWord}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${paging2.page >= paging2.maxPage}">
                        <li class="page-item disabled">
                            <span class="page-link">다음</span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="/notice/search?page=${paging2.page + 1}&searchField=${searchField}&searchWord=${searchWord}" aria-label="Next">다음</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:if>

        </ul>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>