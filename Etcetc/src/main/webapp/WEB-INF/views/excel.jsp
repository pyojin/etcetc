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
<!-- jQuery 파일 다운로드 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.fileDownload/1.4.2/jquery.fileDownload.js" charset="utf8" ></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<title>Excel</title>
</head>
<body>
<div style="width:100%">
	<h1 style="margin-bottom:2rem">엑셀</h1>
        <div>
        	<!-- 검색 -->
        	<div class="searchCondition">
        		날짜조건: <select id="searchDate">
        					<option value="all">--전체--</option>
        					<option value="order">주문일자</option>
        					<option value="pickup">픽업일자</option>
        				</select>
        		<input type="date" id="startDate"> ~ <input type="date" id="endDate">
        		&nbsp;<input type="text" id="keyword">
        		&nbsp;<button id="searchBtn">검색</button>
        		&nbsp;<button id="selectExcelList">엑셀 다운로드</button>
        	
        		<!-- 파일 생성중 보여질 진행막대를 포함하고 있는 다이얼로그 입니다. -->
	        	<div title="Data Download" id="preparing-file-modal" style="display: none;">
	        		<div id="progressbar" style="width: 100%; height: 22px; margin-top: 20px;"></div>
	        	</div>
	        	<!-- 에러발생시 보여질 메세지 다이얼로그 입니다. -->
	        	<div title="Error" id="error-modal" style="display: none;">
	        		<p>생성실패.</p>
	        	</div>
        		
        	</div>
        	<!-- 엑셀 업로드/다운로드 -->
        	<div class="btnDiv" style="margin-top:2rem;margin-bottom:2rem">
        		<button id="updateExcelList">엑셀 업로드</button>
 
        		

        	</div>
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
//전체목록 불러오기
selectAllOrderList();

var searchDate;
var startDate;
var endDate;
var keyword;
var data;

//검색버튼
$('#searchBtn').click(function(){
	searchDate = $('#searchDate').val();
	startDate = $('#startDate').val();
	endDate = $('#endDate').val();
	keyword = $('#keyword').val();
	
	data = {searchDate:searchDate, startDate:startDate, endDate:endDate, keyword:keyword};
	
	console.log(data);

})


//엑셀 다운로드 버튼 누르면 다운로드
$("#selectExcelList").click(function () {
	var $preparingFileModal = $("#preparing-file-modal"); 
	$preparingFileModal.dialog({ modal: true }); 
	$("#progressbar").progressbar({value: false}); 
	$.fileDownload("/plants/selectExcelList", { 
		successCallback: function (url) { 
			$preparingFileModal.dialog('close'); }, 
		failCallback: function (responseHtml, url) { 
			$preparingFileModal.dialog('close'); 
			$("#error-modal").dialog({ modal: true }); },
		httpMethod: 'POST',
		contentType: 'application/json;charset=UTF-8',
		data: data
			}); 
	// 버튼의 원래 클릭 이벤트를 중지 시키기 위해 필요합니다. 
	return false; });



//날짜 검색조건에 오늘을 기본값으로 설정
var now = new Date();
var month = ("0" + (now.getMonth()+1)).slice(-2);
var day = ("0" + (now.getDate())).slice(-2);
var today = now.getFullYear()+"-"+ month +"-"+ day;

$('#startDate').val(today);
$('#endDate').val(today);



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