<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script
        src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous" type="text/javascript">
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>가입</h3>
<form method="post" id="join" class="join">

  <input type="email" id="email" name="email" placeholder="이메일">
  <input type="button" id="add" value="가입">

</form>

<!--<script>-->
<!--    $(function(){-->
<!--        $('#main').on('click', function(e) {-->
<!--            $(location).attr('href','/Park/main.jsp');-->
<!--        });-->
<!--    });-->
<!--</script>-->

<script>
    $(function(){

        $('#add').on('click', function(e) {
            var str = $("#join").serialize();
            $.ajax({

                url:'./findid',
                type:'POST',
                data : str,
                dataType : 'text',
                success : function(data){
                    console.log(data);



                },
                error:function(data){
                    console.log(data);
                }

            });
        });
    });


</script>

</body>
</html>