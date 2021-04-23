<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id="joinform" name="joinform" method="POST">
<input type="text" id="id" name="id" placeholder="id"><br>
<input type="text" id="password" name="password" placeholder="password"><br>
<input type="text" id="email" name="email" placeholder="email"><br>
<input type="text" id="address" name="address" placeholder="address"><br>
<input type="text" id="phoneNumber" name="phoneNumber" placeholder="phone"><br>
<input type="text" id="petName" name="petName" placeholder="pet"><br>
<input type="text" id="img" name="img" placeholder="img"><br>
<input type="button" id="add" value="버튼">
</form>
<script
        src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous" type="text/javascript">
</script>

<script>
$(function(){
	
           	 $('#add').on('click', function(e) {
           	 	var str = $("#joinform").serialize();
           	 	console.log(str);
           	 	$.ajax({
                type: "POST",
                url: "/register",
                data : JSON.stringify(str),  
                contentType : 'application/json',    
                success: function(data) {
                	alert(data);
                },
                error: function(err) {
                    console.log(err);
                }
            });
        });
    });

</script>
        
        


</body>
</html>