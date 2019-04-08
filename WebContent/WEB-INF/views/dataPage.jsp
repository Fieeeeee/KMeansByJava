<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>随机数据页面</title>
</head>
<body>
 <form method="post" action="${pageContext.request.contextPath}/gDataCalculate">   
       <p>请输入参数K值</p>    
       <input type="text"  name="len" id="len"/> <br/>      
       <p>输入所需的初始聚类中心点(提示：需要与K值相同的中心点的位置，每个位置点用空格隔开，最后一位数据位置为1000)</p>              
       <input type="text" name="mat" id="mat"/>     
       <p>输入所需的${length}个初始聚类中心点位置(提示：每个位置点用空格隔开，最后一位数据位置为1000)</p>              
       <input type="text" name="matt" id="matt"/>     
       <input type="hidden" name="lent" id="lent" value="${length}"/>                             
       <input type="submit"  value="计算" />                     
 </form>
</body>
</html>