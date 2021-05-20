<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(function(){
		
		$("#registerBtn").on("click", function(){
			
			let id = $("input[name=id]").val();
			if($.isEmptyObject(id)){
				alert("id를 입력해주세요");
				return;
			}
			
			// 중복체크가 성공적으로 진행되면 datavalue = true
			// 중복 체크가 제대로 되어있지 않은경우 중복체크 메세지를 출력 dataValue = false
			// 등록 가능한 아이디 이면 datavalue = true
			if(!$("input[name=id]").prop("dataValue")){
				alert("id 중복검사를 해주세요");
				return false;
			}
			if(checkPassword()){
				return false;
			};
			
			$("#registerForm").submit();
		});
		
		// #idCheck : 태그 <>~</>의 id 값이 idCheck => 중복체크 버튼
		$("#idCheck").on("click", function(){
			
			// 변수 선언 var -> let var는 메모리 누수가 있다고 함.
			// id값 #id name id 선택하는 명령어.
			let id = $("input[name=id]").val();
			if($.isEmptyObject(id)){
				alert("id를 입력해주세요");
				return;
			}
			
			//아이디 체크 여부
			//초기화
			$("input[name=id]").prop("dataValue",false);
			
			$.ajax({
				url : '/checkId/'+id,
				method : 'get',
				dataType : 'json',
				success : function(data){
					//등록 가능한 아이디 인 경우
					if(data){
						console.log(data);
						
						// 중복체크가 성공 처리 -> 회원가입 버튼 클릭시 dataValue값 확인
						// 회원가입 버튼 클릭시 중복처리를 했다고 저장.
						// 속성값 추가
						$("input[name=id]").prop("dataValue",true);
						alert("사용 가능한 아이디 입니다");
						
					} else{//이미 등록된 아이디인 경우
						alert("id가 중복되었습니다");
					}
				}
			});
		});	
		
		
	});

	function checkPassword(){
		if(!($("input[name=pwd]").val() === $("input[name=pwdCheck]").val())){
			 alert("비밀번호가 일치하지 않습니다.");
		} 
	}
</script>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="/registerMemeber" method="post">
                            <fieldset>
                                <div class="form-group">
                                	<p id="errorMsgArea"></p>
                                	<label>ID</label>
                                    <input class="form-control" placeholder="id" name="id" autofocus
                                    pattern = "[0-9A-Za-z]{5,12}" title="5자 이상 12자 이하로 만들어 주세요.">
                                    <button class="form-control" id="idCheck" type="button" >중복 확인</button>
                                </div>
                                <div class="form-group">
                                <label>PASSWORD</label>
                                    <input class="form-control" placeholder="Password" name="pwd" type="password"
                                    pattern = "[0-9A-Za-z]{5,12}" maxlength="12" title="5자 이상 12자 이하로 만들어 주세요.">
                                </div>
                                <div class="form-group">
                                <label>이름</label>
                                    <input class="form-control" placeholder="name" name="name">
                                </div>
                                <div class="form-group">
                                <label>EMAIL</label>
                                    <input class="form-control" placeholder="email" name="email" type="email">
                                </div>
                                
                                <!-- Change this to a button or input when using this as a form -->
                                <button type=submit class="btn btn-lg btn-success btn-block">회원가입</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/resources/dist/js/sb-admin-2.js"></script>

</body>

</html>
