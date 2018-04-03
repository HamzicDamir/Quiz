        var dialog = document.querySelector(".editmember");
        dialogPolyfill.registerDialog(dialog);
        dialog.querySelector('.close').addEventListener('click', function() {
            dialog.close();
        });
    function addNewMemberContent()
    {
       $('#createMemberContent').append('<div id="divNewUsername" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"></div>');
       $('#divNewUsername').append('<input id="newUsername" class="mdl-textfield__input" pattern="[A-Za-z]*([A-Za-z]+)?" id="NewUserName">');
       $('#divNewUsername').append('<label class="mdl-textfield__label" for="NewUserName">User Name</label>');
       $('#divNewUsername').append('<span class="mdl-textfield__error">Username is not right formated!</span>');
       
       $('#createMemberContent').append('<div id="divNewFirstname" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"></div>');
       $('#divNewFirstname').append('<input id="newFirstname" class="mdl-textfield__input" pattern="[A-Za-z]*([A-Za-z]+)?" id="NewFirstname">');
       $('#divNewFirstname').append('<label class="mdl-textfield__label" for="NewFirstName">First Name</label>');
       $('#divNewFirstname').append('<span class="mdl-textfield__error">Firstname is not right formated!</span>');
       
       $('#createMemberContent').append('<div id="divNewLastname" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"></div>');
       $('#divNewLastname').append('<input id="newLastname" class="mdl-textfield__input" pattern="[A-Za-z]*([A-Za-z]+)?" id="NewLastname">');
       $('#divNewLastname').append('<label class="mdl-textfield__label" for="NewLastName">Last Name</label>');
       $('#divNewLastname').append('<span class="mdl-textfield__error">Lastname is not right formated!</span>');
       
       $('#createMemberContent').append('<div id="divNewEmail" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"></div>');
       $('#divNewEmail').append('<input id="newEmail" class="mdl-textfield__input" pattern="[A-Za-z]*([A-Za-z0-9]+)?" id="NewEmail">');
       $('#divNewEmail').append('<label class="mdl-textfield__label" for="NewEmail">E-Mail</label>');
       $('#divNewEmail').append('<span class="mdl-textfield__error">E-Mail is not right formated!</span>');
       
       $('#createMemberContent').append('<div id="divNewPassword" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">');
       $('#divNewPassword').append('<input id="newPassword" class="mdl-textfield__input" pattern="[A-Za-z]*([A-Za-z0-9]+)?" id="NewPassword">');
       $('#divNewPassword').append('<label class="mdl-textfield__label" for="NewPassword">Password</label>');
       $('#divNewPassword').append('<span class="mdl-textfield__error">Password is not right formated!</span>'); 
       componentHandler.upgradeAllRegistered();
    }
    var NewUser;
    class User{
        constructor(username,firstname,lastname,email,password)
        {
        this._username=username;
        this._firstname=firstname;
        this._lastname=lastname;
        this._email=email;
        this._password=password;
        }
    }
    var dialog1 = document.querySelector(".createMember");
    dialogPolyfill.registerDialog(dialog1);
    dialog1.querySelector('.close2').addEventListener('click', function() {
      dialog1.close();
    });
     dialog1.querySelector('.save2').addEventListener('click', function() {
      var newUsername = $("#newUsername").val();
      var newFirstname = $("#newFirstname").val();
      var newLastname = $("#newLastname").val();
      var newEmail = $("#newEmail").val();
      var newPassword = $("#newPassword").val();
       
         if(newUsername=="" || newFirstname=="" || newLastname=="" || newEmail=="" || newPassword=="")
             {
                alert("Error");
                 return;
             }
    NewUser=new User(newUsername,newFirstname,newLastname,newEmail,newPassword);
    console.log(NewUser);
    NewUser=null;
        dialog1.close();
    });
    $( ".create" ).click(function() {
        $( "#createMemberContent").empty();
        addNewMemberContent();  
        dialog1.showModal();
    });

    var dialog2 = document.querySelector(".editQuiz");
    dialogPolyfill.registerDialog(dialog2);
    dialog2.querySelector('.close1').addEventListener('click', function() {
      dialog2.close();
    });
   
    var dialog3 = document.querySelector(".newQuiz");
    dialogPolyfill.registerDialog(dialog3);
    dialog3.querySelector('.close3').addEventListener('click', function() {
    dialog3.close();
    });

   var dialog4 = document.querySelector(".newQuiz");
    dialogPolyfill.registerDialog(dialog4);
    dialog4.querySelector('.close4').addEventListener('click', function() {
        dialog4.close();   
    });
    dialog4.querySelector('.close5').addEventListener('click', function() {
        quiz.print();
        dialog4.close();   
    });
    dialog4.querySelector('.close6').addEventListener('click', function() {
        quiz.print();
        dialog4.close();   
    });
        
   function addQuizName()
    {
       $('#newQuizContent').empty();
       $('#newQuizContent').append('<div id="quizHeader" class="quizHeader"></div>');
       $('#quizHeader').append('<div id="newQuiz" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"></div>');
       $('#newQuiz').append('<input class="mdl-textfield__input" pattern="[A-Z]*([A-Za-z0-9]+)?" id="NewNameQuiz">');
       $('#newQuiz').append('<label class="mdl-textfield__label" for="NewNameQuiz">Quiz name</label>');
       $('#newQuiz').append('<span class="mdl-textfield__error">Quiz name is not right formated!</span>');
        componentHandler.upgradeAllRegistered();
    }
    function addQuestionDIV()
    {
       $('#newQuizContent').empty();
       $('#newQuizContent').append('<div id="quizHeader" class="quizHeader"></div>');
       $('#quizHeader').append('<div id="questionDiv" class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"></div>');
       $('#questionDiv').append('<input class="mdl-textfield__input" id="nameOfQuestion">');
       $('#questionDiv').append('<label class="mdl-textfield__label" for="nameOfQuestion">Question:</label>');
       $('#questionDiv').append('<span class="mdl-textfield__error">Question is not right formated!</span>');
       $('#quizHeader').append('<div id="timeDiv" class="timePoints" style="text-align:center;margin-top:5px;"></div>');
       $('#timeDiv').append('<p>Time for question</p>'); 
       $('#timeDiv').append('<input id="timeInput" class="mdl-slider mdl-js-slider" type="range" min="5" max="60" value="30" step="5" tabindex="0" oninput="showTimeMessage(this.value)" onchange="showTimeMessage(this.value)" >');
       $('#timeDiv').append('<div id="TimeValue"></div>'); 
       $('#quizHeader').append('<div id="PointsDiv" class="timePoints" style="text-align:center;margin-top:5px;"></div>');
       $('#PointsDiv').append('<p>Points for question</p>'); 
       $('#PointsDiv').append('<input id="pointsInput" class="mdl-slider mdl-js-slider" type="range" min="1" max="10" value="5" step="1" tabindex="0" oninput="showPointsMessage(this.value)" onchange="showPointsMessage(this.value)">');
       $('#PointsDiv').append('<div id="PointsValue"></div>'); 
       $('#quizHeader').append('<button class="mdl-button mdl-js-button mdl-button--accent" style="margin-left:23%" onclick="addQuestion()">Add answer</button>');
       $('#quizHeader').append('<div id="answer" ></div>');            
        componentHandler.upgradeAllRegistered();
    }
    function showTimeMessage(value){
         document.getElementById("TimeValue").innerHTML = "Value: "+value +" s";
      }
    function showPointsMessage(value){
         document.getElementById("PointsValue").innerHTML = "Value: "+value +" s";
      }
    $(".prikazi").click(function() {
        $(".first").css('display','block');
        $(".second").css('display','none');
        $(".third").css('display','none');
        dialog4.showModal();
        addQuizName();
    });
    var divAnswerId=0;
    function addQuestion()
    {
        if(divAnswerId==5){
            alert("ID=5");
            return;
        }
       var divID="div"+divAnswerId; 
       $('#answer').append('<div id="'+divID+'"></div>');
       $('#'+divID+'').append('<label id="checkLabel'+divAnswerId+'" class="cLabel mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="checkbox'+divAnswerId+'" style="width:auto"></label>');
       $('#checkLabel'+divAnswerId+'').append('<input type="checkbox" id="checkbox'+divAnswerId+'" class="mdl-checkbox__input">');
       $('#'+divID+'').append('<div id="inputDiv'+divAnswerId+'" class="iDiv mdl-textfield mdl-js-textfield" style="padding-top:0px"></div>');
        $('#inputDiv'+divAnswerId+'').append(' <input class="mdl-textfield__input" id="inputAnswer'+divAnswerId+'">');
       $('#inputDiv'+divAnswerId+'').append('<label class="iLabel mdl-textfield__label" for="inputAnswer'+divAnswerId+'"></label>');         
       componentHandler.upgradeAllRegistered();
       divAnswerId=divAnswerId+1;
    }
    function getAnswers()
    {
        var answers=new Array();
        var correct_answers=0;
        for(i=0;i<divAnswerId;++i)
        {
            var nameOfanswer=$('#inputAnswer'+i+'').val();
            var isCorrect;
            if(nameOfanswer==""){
                alert("You have add a answer but not insert in him")
                return false;    
               }
            if($('#checkbox'+i+'').is(':checked')==true){
                isCorrect=true;
                ++correct_answers;
            }
            else
                isCorrect=false;
            ans=new Answer(nameOfanswer,isCorrect);
            question.addAnswer(ans);
        }
        if(correct_answers==0){
            alert("You must select at least one correct answer");        
            return false;
        }
        if(correct_answers==divAnswerId){
            alert("All answers can not be corect");        
            return false;
        }
        var points=$('#pointsInput').val();
        var time=$('#timeInput').val();
        question.setTimeAndPoints(time,points);
        //question.printAnswers();
        quiz.addQuestion(question);
    }
    $(".next1").click(function() {
        var newQuestion = $("#nameOfQuestion").val();
        if(newQuestion ==""){
            alert("Please name of Question");
            return;
        }
        question=new Question(newQuestion);
        if(divAnswerId<2 || divAnswerId>5){
                alert("Question can only have answers bettwen 2 and 5!!!");
                return;
        }
        if(getAnswers()==false){
                return;
        }
        divAnswerId=0;
        addQuestionDIV();
    });
    $(".next").click(function() {
        var newQuiz = $("#NewNameQuiz").val();
        if(newQuiz==""){
            alert("Please insert name of Quiz");
            return;
        }
         quiz=new Quiz(newQuiz);
        $(".first").css('display','none');
        $(".second").css('display','block');
        addQuestionDIV();
    });
        class Answer{
    constructor(name,isCorrect) {
        this._name=name;
        this._isCorrect=isCorrect;
 }
    printAnswer()
    {
        console.log(this._name+" "+ this._isCorrect);
    }
}
class Question{
    
    constructor(name)
    {
        this._name = name;
        this._answers = new Array();
        this._points = 5;
        this._time=30;
    }
    setName(name)
     {
         this._name=name;
     }
    setTimeAndPoints(time,points)
    {
        this._time=time;
        this._points=points;
    }
    addAnswer(answer)
    {
        this._answers.push(answer);
    }
    printAnswers()
    {
        console.log(this._name);
        console.log(this._answers);
        console.log(this._time);
        console.log(this._points);
    }
    setPoints(points)
    {
        this._poents=0;
    }
}
    var question;
    var quiz;
        
    function updateQuiz()
    {
        $( "#tbodyQuiz").empty();
        for(i=0;i<5;++i){ 
            $('#tbodyQuiz').append('<tr id="trquiz'+i+'"></tr>');
            $('#trquiz'+i+'').append('<td class="mdl-data-table__cell--non-numeric">'+i+'</td>');
            $('#trquiz'+i+'').append('<td class="mdl-data-table__cell--non-numeric">Servlet</td>');
            $('#trquiz'+i+'').append('<td class="mdl-data-table__cell--non-numeric">10</td>');
            $('#trquiz'+i+'').append('<td class="mdl-data-table__cell--non-numeric">100</td>');
            $('#trquiz'+i+'').append('<td class="mdl-data-table__cell--non-numeric"><span class="icons editquiz"><i class="material-icons">create</i></span></td>');
            $('#trquiz'+i+'').append('<td class="mdl-data-table__cell--non-numeric"><span class="icons deletequiz"><i class="material-icons">clear</i></span></td>');
        }
        componentHandler.upgradeAllRegistered();
    }
    updateQuiz();
        function updateUsers(){
            $( "#tbodyUsers").empty();
            for(i=0;i<5;++i){ 
                $('#tbodyUsers').append('<tr id="truser'+i+'"></tr>');
                $('#truser'+i+'').append('<td class="mdl-data-table__cell--non-numeric">'+i+'</td>');
                $('#truser'+i+'').append('<td class="mdl-data-table__cell--non-numeric">DAMIR</td>');
                $('#truser'+i+'').append('<td class="mdl-data-table__cell--non-numeric">HAMZIC</td>');
                $('#truser'+i+'').append('<td class="mdl-data-table__cell--non-numeric">damir.hamzic@fet.ba</td>');
                $('#truser'+i+'').append('<td class="mdl-data-table__cell--non-numeric"><span class="icons edit"><i class="material-icons">create</i></span></td>');
                $('#truser'+i+'').append('<td class="mdl-data-table__cell--non-numeric"><span class="icons delete"><i class="material-icons">clear</i></span></td>');
            }
            componentHandler.upgradeAllRegistered();
    }
    updateUsers();
        
    function updateInbox(){
            $( "#tbodyInbox").empty();
            for(i=0;i<5;++i){ 
                $('#tbodyInbox').append('<tr id="trinbox'+i+'"></tr>');
                $('#trinbox'+i+'').append('<td class="mdl-data-table__cell--non-numeric">'+i+'</td>');
                $('#trinbox'+i+'').append('<td class="mdl-data-table__cell--non-numeric">DAMIR</td>');
                $('#trinbox'+i+'').append('<td class="mdl-data-table__cell--non-numeric">HAMZIC</td>');
                $('#trinbox'+i+'').append('<td class="mdl-data-table__cell--non-numeric">damir.hamzic@fet.ba</td>');
                $('#trinbox'+i+'').append('<td class="mdl-data-table__cell--non-numeric">0</td>');
                $('#trinbox'+i+'').append('<td class="mdl-data-table__cell--non-numeric">OOP</td>');
            }
            componentHandler.upgradeAllRegistered();
    }
    updateInbox();
        
        
   $(".finish").click(function(){
        $(".questionHeader").css('display','none');
        $(".first").css('display','none');
        $(".second").css('display','block');
        $(".quizList").css('display','block');
        var list = document.createElement("ol");
        list.setAttribute("id", "listId");
        var divPitanje = document.createElement("div");
        var divOdgovori = document.createElement("div");
        divPitanje.setAttribute("id","divQ");
        divOdgovori.setAttribute("id","divA");
        var el = document.createElement("text");
        var el1 = document.createElement("ol");
        var el2 = document.createElement("li");
        var el3 = document.createElement("li");
        var el4 = document.createElement("li");
        el.innerHTML = "ovo je pitanje";
        el2.innerHTML = "ovo je odgovor1";
        el3.innerHTML = "ovo je odgovor2";
        el4.innerHTML = "ovo je odgovor3";
        document.getElementById("quizList").appendChild(divPitanje);
        document.getElementById("divQ").appendChild(el);
        document.getElementById("quizList").appendChild(divOdgovori);
        document.getElementById("divA").appendChild(el1);
        document.getElementById("divA").appendChild(el2);
        document.getElementById("divA").appendChild(el3);
        document.getElementById("divA").appendChild(el4);
    });
        
        var b=1;
        var divId=0;
    $(".add" ).click(function() {
        if(divId==5){
            alert("ID=5");
            return;
        }
        var divID="div"+divId;
       $('#answer').append('<div id="'+divID+'"></div>');
       $('#'+divID+'').append('<label id="checkLabel'+divId+'" class="cLabel mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="checkbox'+divId+'"></label>');
       $('#checkLabel'+divId+'').append('<input type="checkbox" id="checkbox'+divId+'" class="mdl-checkbox__input">');
       $('#'+divID+'').append('<div id="inputDiv'+divId+'" class="iDiv mdl-textfield mdl-js-textfield" style="padding-top:0px"></div>');
       $('#inputDiv'+divId+'').append(' <input class="mdl-textfield__input" id="inputLabel'+divId+'">');
       $('#inputDiv'+divId+'').append('<label class="iLabel mdl-textfield__label" for="inputLabel'+divId+'"></label>');         
       componentHandler.upgradeAllRegistered();
        divId=divId+1;
    });
    
    var list = document.createElement("ol");
    
    $("#createNew").click(function() {
        dialog3.showModal();
    });

    $("#memberCreateNew").click(function() {
        dialog4.showModal();
    });

    $("#signIN").click(function(){
	var user = document.getElementById("username").value;
	if(user == "superadmin")
	{	
		$("#login").hide();
        	$("#superadmin").show();
	}
	else
	{	
		/*document.getElementById("memberName").innerHTML = user + " | " + "Home";	
		$("#login").hide();
		$("#member").show();
	   */
        $("#login").hide();
        	$("#superadmin").show();
    }
    });
     $("#exit").click(function(){
         $(".Edit").fadeOut();
    });    
    $("#signOUT").click(function(){
        $("#superadmin").hide();
        $("#login").show();
    });
    $("#signOUTMember").click(function(){
        $("#member").hide();
        $("#login").show();
    });
    $( ".edit" ).click(function() {
          dialog.showModal();
    });

    $( ".delete" ).click(function() {
        alert( "Handler for .click() delete called." );
    });
    $( ".editquiz" ).click(function() {
          dialog2.showModal();
    });
    $( ".deletequiz" ).click(function() {
        alert( "Handler for .click() delete called." );
    });
     $( ".doneButton" ).click(function() {
        $("#doneList").show();
    });

    $( ".memberdoneButton" ).click(function() {
        $("#doneList1").show();
    });
       function ispisi(v)
     {
         alert(v);
     }
     
     var updateOutput = function(e)
    {
        var list   = e.length ? e : $(e.target),
            output = list.data('output');
        if (window.JSON) {
            output.val(window.JSON.stringify(list.nestable('serialize')));//, null, 2));
        } else {
            output.val('JSON browser support required for this demo.');
        }
    };

    // activate Nestable for list 1
    $('#nestable').nestable({
        group: 1
    })
    .on('change', updateOutput);

    // activate Nestable for list 2
    $('#nestable2').nestable({
        group: 1
    })
    .on('change', updateOutput);

    // output initial serialised data
    updateOutput($('#nestable').data('output', $('#nestable-output')));

    $('#nestable-menu').on('click', function(e)
    {
        var target = $(e.target),
            action = target.data('action');
        if (action === 'expand-all') {
            $('.dd').nestable('expandAll');
        }
        if (action === 'collapse-all') {
            $('.dd').nestable('collapseAll');
        }
    });

    $('#nestable3').nestable();