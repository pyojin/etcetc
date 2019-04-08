<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<!-- DataTables -->
<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css" rel="stylesheet" >
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js" charset="utf8" ></script>


<title>Excel</title>
</head>
<body>
<div style="width:100%">
	<h1>엑셀</h1>
        <div>
              <table id="table_id">
   				 <thead>
      			 	<tr>
            			<th>보내는 사람 이름</th>
            			<th>보내는 사람 전화번호</th>
            			<th>보내는 사람 우편번호</th>
            			<th>보내는 사람 주소</th>
            			<th>보내는 사람 상세주소</th>
            			<th>받는 사람 이름</th>
            			<th>받는 사람 전화번호</th>
            			<th>받는 사람 우편번호</th>
            			<th>받는 사람 주소</th>
            			<th>받는 사람 상세주소</th>
            			<th>물품명</th>
            			<th>물품가격</th>
            			<th>픽업 요청일</th>
            			<th>주문일자</th>
        			</tr>
    			</thead>
    			<tbody>
        			<tr>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>
        				<td></td>		
        			</tr>
    			</tbody>
			</table>
		</div>
</div>


</body>
</html>

<script type="text/javascript">
selectAllOrderList();


function selectAllOrderList(){ //전체목록 불러오기
	$('#table_id').DataTable({
		'pageLength': 100,
		'destroy': true,
		'ordering':true,
		'paging': true,
		'language': {
			'url': '//cdn.datatables.net/plug-ins/1.10.19/i18n/Korean.json'},
		'processing': true,
		'deferRender': true,
		'ajax': {
			url : '${pageContext.request.contextPath}/selectAllOrderList',
			data: 'JSON',
			type : 'POST',
			dataSrc:''
		},
		'columns':[
			{data: "send_name"},
			{data: "send_tel"},
			{data: "send_post"},
			{data: "send_addr1"},
			{data: "send_addr2"},
			{data: "receive_name"},
			{data: "receive_tel"},
			{data: "receive_post"},
			{data: "receive_addr1"},
			{data: "receive_addr2"},
			{data: "goods_name"},
			{data: "goods_price"},
			{data: "pickup_date"},
			{data: "order_date"}
		]
	});
}
</script>