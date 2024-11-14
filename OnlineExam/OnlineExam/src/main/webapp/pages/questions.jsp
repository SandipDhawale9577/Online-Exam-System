<html>
<head>
<!-- we have to apply css for all button -->
<link href="css/common.css" rel="stylesheet"></link>

<script>


function changeColor()
{
	var allspans=document.getElementsByName("answers");
	var allRadioButtons=document.getElementsByName("option");
	var previousAnswer=document.questionForm.previousAnswer.value;
for(i=0;i<allspans.length;i++){
	<!-- we took innerText here not take innerHtml beacuse innertext never create any spce while displaying elements on browser-->
	if(allspans[i].innerText==previousAnswer){
		//style have color property
		allspans[i].style.color="magenta";
		allRadioButtons[i].checked=true;
		<!--  we write break here so no need to check next if answer is found-->
		break;
	}
}

	
	}
function displayTime()
{
	<!-- getitem give value in string we covert it into int using parseInt we have to give this parseInt(sessionStorage.getItem("timeleft"))-1;to timeleft so we set it -->
	sessionStorage.setItem("timeleft",parseInt(sessionStorage.getItem("timeleft"))-1);
	<!-- parseInt(sessionStorage.getItem("timeleft"))-1) this will give 180 first then we have to display it in form of minute second-->
	<!-- here  var timeleft is local varible-->
	var timeleft=sessionStorage.getItem("timeleft");
	<!--  we get value in decimal format so inorder convert it into int we use floor function here and padStart for beacuse we have to display minute ans second into 2 digit and if 1 single digit come the PadStart attach zero value to it-->
	
	<!--padstart will esure if there is 2 digit numnber not present add 0 into it-->
	var minute=Math.floor(timeleft/60).toString().padStart(2,'0');
	<!-- 180-3*60=0 means 0 second remaning -->
	var seconds=(timeleft-(minute*60)).toString().padStart(2,'0');
	
	document.getElementById("timeleft").innerHTML=minute+":"+seconds;
	if(timeleft==0){
		//alert("time over")
		<!--  location  object and href is attribute in and we have to sees sccore so we weite endExam there --> 
		location.href="endexam";
	}
}
	<!-- setintervel is built in function   it will call display time after 1 second=1000milisecond -->
	setInterval(displayTime,1000);
	<!-- timeleft is null whenever we visit the score page-->
	
	function check(){
		if(sessionStorage.getItem("timeleft")==null){
			location.href="/login";
		}
	}





function saveResponse()
{


var qno=document.questionForm.qno.value;
var question=document.questionForm.question.value;
var submittedAnswer=document.questionForm.option.value;
<!--In order to store whole qno question submittedAnswer into single object we take data beacuse we require its reference  -->
var data="qno="+qno+"&question="+question+"&submittedAnswer="+submittedAnswer;
//alert(data);
//xmlhttp is varible var is datatype
var xmlhttp=new XMLHttpRequest();
xmlhttp.open("get","saveresponse?"+data);
xmlhttp.send();

//alert("question no is"+qno);
//alert("question is"+question);
//alert("submited"+submittedAnswer);
}


addEventListener("load",check);
addEventListener("load",changeColor);
</script>
</head>

<body >

welcome ${username}<br><br>
<td><img src="/images/${imagename}" height=100px width=100px></td><br><br>

<div>

<form name="questionForm">
		Remaning Time-<span id="timeleft"> </span><br><br>
	<input type="text"  rows=2 cols=30 name="qno" value="${question.qno}" readonly><br><br>
	
	<textarea  rows=4 cols=60 name="question" readonly> ${question.question} </textarea><br><br>
		<!-- in order display option we use span beacuse radion buttton will unable to store it -->
	<input  type="radio" name="option" value="${question.option1}" onclick="saveResponse()">  <span name="answers"> ${question.option1} </span><br><br>
	
	<input  type="radio" name="option" value="${question.option2}" onclick="saveResponse()">  <span name="answers"> ${question.option2} </span> <br><br>
		
	<input  type="radio" name="option" value="${question.option3}" onclick="saveResponse()"> <span name="answers"> ${question.option3} </span> <br><br>
	
	<input  type="radio" name="option" value="${question.option4}"onclick="saveResponse()"> <span name="answers"> ${question.option4} </span> <br><br>
	
		
	<input type="submit" value="next" formaction="next" class="btn">
	<input  type="submit" value="previous" formaction="previous" class="btn">
	<input  type="submit" value="end exam" formaction="endexam" class="btn">
	 <!--  <input type="text" name="previousAnswer" value="${previousAnswer}"><br><br>-->
	 
	 <!-- for displaying previous answer on question page -->
  <input  style="border:none" type="text" name="previousAnswer" value="${previousAnswer}"><br><br>

<br><br>

<!--  <input  style="border:none;display:none" type="text" name="previousAnswer" value="${previousAnswer}"><br><br>-->

</form>
</div>
 <span> ${message} </span><br>

</body>
