<!DOCTYPE html>
<html>
    <head>
        <title>Quiz</title>
        <link rel="stylesheet" href="icon.css">
	    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.teal-pink.min.css" />
	    <script src="material.min.js"></script>
        <link rel="stylesheet" href="quiz_id.css">
        <script src="jquery-3.2.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="dialog-polyfill.css" />
        <script src="dialog-polyfill.js"></script>
        <script src="jquery.nestable.js"></script>
    </head>
    
<body>

<div class="mdl-layout mdl-js-layout">
  <header class="mdl-layout__header">
    <div class="mdl-layout-icon"></div>
    <div class="mdl-layout__header-row">
      <span class="mdl-layout__title title"><h4>QUIZZES</h4></span>
      <div class="mdl-layout-spacer"></div>
      <nav class="mdl-navigation">
      	
      </nav>
    </div>
  </header>
    <main class="mdl-layout__content">
    <div class="quizzes">
	
		<div class="demo-card-wide mdl-card mdl-shadow--2dp quiz1">
			<div class="playQuiz1" style="display:none">
                <div id="pquiz1"></div>
				<button class="next1 mdl-button mdl-js-button mdl-button--accent">NEXT</button>
				<button class="skip1 mdl-button mdl-js-button mdl-button--accent">SKIP</button>
			</div>
			<div id="dataDiv1" style="display:none"></div>
			<div id="quizInfo1"></div>
		</div>

      
		
    </div>
  </main>
</div>

<script>


makeQuizInfo1();

var quiz={name:"",questions:new Array(),player:""};
var question = 0;
/********************FIRST QUIZ*******************************************/
function makeQuizInfo1(){
	var id=<%=request.getAttribute("quiz_id")%>;
	$('#quizInfo1').empty();
		question1 =  0;
    	quiz={name:"",questions:new Array(),player:""};
		$.getJSON("admin",{getQuizById:"quizes",id:id}, function(result){
        if(result.exists[0]==false){
        	$("#quizInfo1").append('<div style="text-align:center; margin-top:250px; font-size:25px;color:deeppink; font-weight:bold">There is no quiz with that id!!!</div>');
			
        }
        else{
			$("#quizInfo1").append('<div class="mdl-card__title quizPhoto1"></div>');
			$("#quizInfo1").append('<div class="mdl-card__actions mdl-card--border" id="qi1"></div>');
			$("#qi1").append('<div id="nameOfQuiz"></div>');
			$("#nameOfQuiz").append('<p>Quiz name:</p>');
			$("#nameOfQuiz").append('<span id="quizname1">'+result.name[0]+'</span>');
			$("#qi1").append('<div id="numQ1"></div>');
			$("#numQ1").append('<p>Number of questions:</p>');
			$("#numQ1").append('<span id="quiznum1">'+result.questions[0].length+'</span>');
			$("#qi1").append('<div id="points1"></div>');
			$("#points1").append('<p>Max points:</p>');
			$("#points1").append('<span id="quizPoints1">'+result.max_points+'</span>');
			$("#qi1").append('<div id="active1"></div>');
			$("#active1").append('<p>Active:</p>');
			$("#active1").append('<span id="quizActive1">no</span>');
			$("#quizInfo1").append('<button class="button-p1 mdl-button mdl-js-button mdl-button--raised mdl-button--accent">PLAY</button>');
			$('#quizInfo1').on('click','button', reloadQuiz);
			$("#quizInfo1").fadeIn();
		
			componentHandler.upgradeAllRegistered();
			quiz.name=result.name[0];
			for(var i=0;i<result.questions[0].length;++i)
				{
					var question={name:result.questions[0][i].name[0],points:result.questions[0][i].points[0],time:result.questions[0][i].time[0],answers:new Array(),done:"false"};
						for(var ii=0;ii<result.questions[0][i].answers[0].length;++ii){
							var answer={name:result.questions[0][i].answers[0][ii].name[0], isCorrect:"",number:ii};
							question.answers.push(answer);
						}
					quiz.questions.push(question);
				}
		}
	});		    	
}
function reloadQuiz(){ 
    $("#quizInfo1").hide();
  makeQuestion1();
}
function makeQuestion1(){
    var index=question+1;
    $("#pquiz1").empty();
    $("#pquiz1").append('<div id="quiz1header" class="mdl-card__title question" style="color:rgb(0,128,128);font-size:18px;">'+index+'. '+quiz.questions[question].name+'</div>');
    $("#pquiz1").append('<div class="mdl-card__actions mdl-card--border"></div>');
    $("#pquiz1").append('<div id="quiz1answers" class="answers"></div>');
    for(i = 0; i<quiz.questions[question].answers.length; ++i)
    {
        $("#quiz1answers").append('<div id="quiz1answer'+i+'" class="answer1"></div>');
        $('#quiz1answer'+i+'').append('<div class="answerText">'+(quiz.questions[question].answers[i].number+1)+'. '+quiz.questions[question].answers[i].name+'</div>');
        $('#quiz1answer'+i+'').append('<div id="quiz1check'+i+'" class="quiz1check"></div>');
        $('#quiz1check'+i+'').append('<label class="check1 mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" id="check1Label'+i+'" for="checkbox1'+i+'"></label>');
        $('#check1Label'+i+'').append('<input type="checkbox" id="checkbox1'+i+'" class="mdl-checkbox__input">');
    }
    $("#pquiz1").append('<div id="errorQuestion" style="display:none"></div>');
    $("#pquiz1").append('<div id="clockdiv1"></div');
    $("#clockdiv1").append('<div id="divspan1"></div>');
    $("#divspan1").append('<span class="seconds1" id="secondsQuiz1"></span>');	
    $(".playQuiz1").fadeIn();
    
    function getTimeRemaining(endtime) {
        var t = Date.parse(endtime) - Date.parse(new Date());
        var seconds = Math.floor((t / 1000) % quiz.questions[question].time);
    if(seconds < 0)
        seconds = 0;
        return {
        'seconds': seconds
        };
    }
    function initializeClock(id, endtime) {
        var clock = document.getElementById(id);
        var secondsSpan = clock.querySelector('.seconds1');
    function updateClock() {
        var t = getTimeRemaining(endtime);
        secondsSpan.innerHTML = ('0' + t.seconds).slice(-2);
    }

    updateClock();
    var timeinterval = setInterval(updateClock, 1000);
    }

    var deadline = new Date(Date.parse(new Date()) + (quiz.questions[question].time-1) * 1000);
    initializeClock('clockdiv1', deadline);

    componentHandler.upgradeAllRegistered();
     
}
function makeDataCard1()
{
	$("#dataDiv1").empty();
    $("#dataDiv1").append('<h4 id="dataHeader1"style="color:rgb(0,128,128);text-align:center; font-weight:bold;">Player info</h4>');
    $("#dataDiv1").append('<div id="nameDiv1"class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"></div>');
    $("#nameDiv1").append('<input class="mdl-textfield__input" id="name1">');
    $("#nameDiv1").append('<label class="mdl-textfield__label" for="name1">First name</label>');
    $("#dataDiv1").append('<div id="lastNameDiv1"class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"></div>');
    $("#lastNameDiv1").append('<input class="mdl-textfield__input" id="lastName1">');
    $("#lastNameDiv1").append('<label class="mdl-textfield__label" for="lastName1">Last name</label>');
    $("#dataDiv1").append('<div id="emailDiv1"class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"></div>');
    $("#emailDiv1").append('<input class="mdl-textfield__input" id="email1">');
    $("#emailDiv1").append('<label class="mdl-textfield__label" for="email1">E-mail</label>');
    $("#dataDiv1").append('<div id="errorDataCard1" style="display:none">Some fields are empty</div>');
    $("#dataDiv1").append('<button class="button-send1 mdl-button mdl-js-button mdl-button--accent" id="send1">SEND</button>');
    $("#dataDiv1").on('click','button', reloadSend1);
    $("#dataDiv1").fadeIn();
    componentHandler.upgradeAllRegistered();
}
	
function reloadSend1(){
	 var PlayerFirstname = $("#name1").val();
	 var PlayerLastname = $("#lastName1").val();
	 var PlayerEmail = $("#email1").val();
	 if(PlayerFirstname=="" || PlayerLastname=="" || PlayerEmail=="")
	 {
		 $("#errorDataCard1").show();
		 return;
	 }
	 var Player={first_name_:PlayerFirstname,last_name_:PlayerLastname,e_mail_:PlayerEmail};
	 quiz.player=Player;
	 console.log(quiz);
	 var json=JSON.stringify(quiz);
	 $.post( "admin", { PlayerResults: json });
     console.log(json);
     $("#dataDiv1").hide();
     makeQuizInfo1();
     $("#quizInfo1").fadeIn();
    
}
function getAnswers()
{
    var correct_answers=0;
    var answers=new Array();
    for(i=0;i<quiz.questions[question].answers.length;++i)
    {
        var IsCorrect;
        if($('#checkbox1'+i+'').is(':checked')==true){
            IsCorrect="true";
            ++correct_answers;
        }
        else
            IsCorrect="false";
        var answer = quiz.questions[question].answers[i];
        answer.isCorrect=IsCorrect;
        answers.push(answer);
    }
    if(correct_answers==0){
        document.getElementById("errorQuestion").innerHTML="You must select at least one answer";        
        return false;
    }
    if(correct_answers==quiz.questions[question].answers.length){
        document.getElementById("errorQuestion").innerHTML="All answers can not be corect";        
        return false;
    }
    quiz.questions[question].answers=answers;
    quiz.questions[question].done="true";
}
function findQuestion(){
    if(question+1<quiz.questions.length){ 
        console.log("veci index");
         for(var i=question+1; i<quiz.questions.length; ++i){
             if(quiz.questions[i].done=="false")
             {
                  console.log(i);
                 return i;
             }
         }
    }
   else{ 
       console.log("manji index");
        for(var i=0;i<question;++i){
             if(quiz.questions[i].done=="false"){
                 console.log(i);
                 return i;
             }
         }
   }
    if(quiz.questions[question].done=="false")
    {
        console.log("isti");
        return question;
    }
    console.log("gotovo");
     return false;
 }

$(".next1").click(function(){
    var time;
    time = document.getElementById("secondsQuiz1").innerHTML;
    quiz.questions[question].time = time;
    if(getAnswers()==false){
        $("#errorQuestion").show();
        return;
    }
    var findquestion = findQuestion();
    if(findquestion==false){
    	findquestion=0;
        $(".playQuiz1").hide();
        makeDataCard1();
        return;
    } 
    question=findquestion;
    makeQuestion1();    
});

$(".skip1").click(function(){
    var time;
    time = document.getElementById("secondsQuiz1").innerHTML;
    quiz.questions[question].time = time;
    question = findQuestion();
    makeQuestion1();
});
        </script>
  </body>
</html>

