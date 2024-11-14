<html>
<head>
<!-- for applying css to button -->
<link href="css/common.css" rel="stylesheet"></link>

<style>

div{
text-align:center;
margin-left:100px;
padding-top:200px;

}

</style>
</head>
<div>
<form>
<!--   here username and password must same as validat method paratmeter name and password-->
		<input size=45  style="border-radius:10px" name="username"> <br><br>
		
		<input size=45 style="border-radius:10px"  type="password" name="password" style="padding:10px"> <br><br>
		
		<!-- class btn contain css  tag file -->
		<input type="submit" value="sign in" formaction="validate" class="btn"> 
				<input type="submit" value="sign up" formaction="register" class="btn"> 
		
		
	
		
		<span style="color:red;font-size:larger"> ${message} </span>
		
</form>
	</div>
	</html>