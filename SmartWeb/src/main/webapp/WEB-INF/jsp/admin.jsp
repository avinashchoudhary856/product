<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.js"></script>
<style>
body {
  font-family: Arial;
  font-size: 17px;
  padding: 8px;
}

* {

  box-sizing: border-box;
}

.row {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
  margin: 0 -16px;
}

.col-25 {
  -ms-flex: 25%; /* IE10 */
  flex: 25%;
}

.col-50 {
  -ms-flex: 50%; /* IE10 */
  flex: 50%;
}

.col-75 {
  -ms-flex: 75%; /* IE10 */
  flex: 75%;
}

.col-25,
.col-50,
.col-75 {
  padding: 0 16px;
}

select {
  width: 100%;
  margin-bottom: 20px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 3px;
  background-color: white;
  font-size: 17px;
}

.container {
  background-color: #f2f2f2;
  padding: 5px 20px 15px 20px;
  border: 1px solid lightgrey;
  border-radius: 3px;
}

input[type=text] {
  width: 100%;
  margin-bottom: 20px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 3px;
  font-size: 17px;
}

input[type=password] {
  width: 100%;
  margin-bottom: 20px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 3px;
  font-size: 17px;
}

label {
  margin-bottom: 10px;
  display: block;
}

.icon-container {
  margin-bottom: 20px;
  padding: 7px 0;
  font-size: 24px;
}

.btn {
  background-color: #4CAF50;
  color: white;
  padding: 12px;
  margin: 10px 0;
  border: none;
  width: 100%;
  border-radius: 3px;
  cursor: pointer;
  font-size: 17px;
}

.btn:hover {
  background-color: #45a049;
}

a {
  color: #2196F3;
}

hr {
  border: 1px solid lightgrey;
}

span.price {
  float: right;
  color: grey;
}

.header
{
background-color : #1a1a00;
padding:20px 5px 5px 25px;
background-image : url(/WEB-INF/img/icon.png) no-repeat top left;
color : white;
margin : 10px;
height :150px;
}

.tagline
{
color :#ffff80;
font-weight: 900;
font-stretch: expanded;
  font-family: Arial, Helvetica, sans-serif;
}


/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
@media (max-width: 800px) {
  .row {
    flex-direction: column-reverse;
  }
  .col-25 {
    margin-bottom: 20px;
  }
}
</style>

<script type="text/javascript" language="javascript">
$(function() {
   /*  Submit form using Ajax */
   $('button[type=submit]').click(function(e) {

      //Prevent default submission of form
      e.preventDefault();

      //Remove all errors
      $('input').next().remove();
      var n = $('input[name=name]').val();
      var f = $('input[name=fname]').val();
      var t = $('select[name=type]').val();
      var p = $('input[name=pwd]').val();
      $.post({
         url : 'saveuser',
         data : {
            userName: n,
            password:  p,
            fullName:f,
            userType:t
         },
         success: function(result) {
            alert(result.status);
            var frm = document.getElementsByName('saveUser')[0];
            frm.reset();
         }
      })
   });
});
</script>

</head>
<body>
 <h1>User Input Form</h1>
  <hr />

  <form action="saveUser" method="post" name="saveUser">
   <table>
           <tr>
               <td>User Name:</td>
               <td><input type="text" name="name" value=""></td>
           </tr>
           <tr>
               <td>Full Name:</td>
               <td><input type="text" name="fname" value=""></td>
           </tr>
           <tr>
                <label for="type">Choose a userType:</label>

                <select name="type" id="type">
                  <option value="ADMIN">ADMIN</option>
                  <option value="STUDENT">STUDENT</option>
                  <option value="TEACHER">TEACHER</option>
                </select>
           <tr>
           <tr>
               <td>Password:</td>
               <td><input type="password" name="pwd" value=""></td>
           </tr>
           <tr>
               <td>ConfirmPassword:</td>
               <td><input type="password" name="confirmpassword" /></td>
           </tr>
           <tr>
             <td></td>
                   <td><button type="submit">Submit</button></td>
           </tr>
   </table>
  </form>

</body>
</html>