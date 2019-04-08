<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iris数据页面</title>
</head>
<body>
 <form method="post" action="${pageContext.request.contextPath}/gIrisCalculate"> 
       <h3>K-means算法：</h3>  
       <p>输入所需的3个初始聚类中心点位置(提示：每个位置点用空格隔开，最后一位数据位置为${size})</p>   
       <input type="text"  name="len" id="len"/> <br/>  
       <h3>优化K-means算法：</h3>    
       <p>输入所需的${length}个初始聚类中心点位置(提示：每个位置点用空格隔开，最后一位数据位置为${size})</p>              
       <input type="text" name="mat" id="mat"/>                                   
       <input type="submit"  value="计算" />                     
 </form>
</body>
</html>