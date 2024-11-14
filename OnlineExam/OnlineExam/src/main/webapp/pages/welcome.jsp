<html>

<head>

<script>
<!--   Sessionstorage is used for setting time which is js object   we can never settime manually we must have to take the help of  setItem function-->
sessionStorage.setItem("timeleft",181);
</script>
</head>

<body>

<form>
		<select name="selectedSubject" id="selectedSubject">
		
			<option value="maths">maths</option>
			<option value="gk">general knowledge</option>
			
		</select>
		
		<br><br><br><br>
		
		<input type=submit value="startExam" formaction="startExam">
</form>
	
</body>

</html>