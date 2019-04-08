<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结果页面</title>
</head>
<body>
<p>基本K-mean算法</p> 
<P ALIGN="CENTER">     
    <img src="${StudentKURLBar}" border=0 usemap="#imgMap">    
 </P> 
  <p>优化K-mean算法</p>  
 <P ALIGN="CENTER">     
    <img src="${StudentCKURLBar}" border=0 usemap="#imgMap">   
 </P> 
        <table align="center" valign="center">
            <thead>
            <tr>
                <th>算法名称</th>
                <th>迭代次数</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
                <tr>
                    <td>K-means算法</td>
                    <td>${number}</td>
                </tr>
                <tr>
                    <td>优化算法</td>
                    <td>${cnumber}</td>
                </tr>
        </table>
</body>
</html>