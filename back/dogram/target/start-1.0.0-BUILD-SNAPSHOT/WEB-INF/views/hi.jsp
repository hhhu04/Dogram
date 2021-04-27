<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id="joinform" method="post">
<input type="text" id="id" name="id" placeholder="id"><br>
<input type="text" id="password" name="password" placeholder="password"><br>

<input type="button" id="add" value="버튼">
</form>
<script
        src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous" type="text/javascript">
</script>

<script>
    $(document).ready(function(){

        $( "#add" ).click(function(e) {
            e.preventDefault();
			
            var str = $("#joinform").serialize();

            $.ajax({
                type: "POST",
                url: "/sample",
                contentType : 'application/json; charset=utf-8',    
        		data : JSON.stringify(str),  
                datatype : 'json',
                success: function(data) {
                	alert(data);
                },
                error: function(err) {
                    console.log("error!");
                }
            });
        });
    });

</script>
        
        
</body>
</html>