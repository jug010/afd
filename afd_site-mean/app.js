

// Declare dependencies
var express = require('express');
var nodemailer = require("nodemailer");
var http = require('http');


// Defining SMTP mail server details to use my gmail to send the email
/*
var smtpTransport = nodemailer.createTransport({
    service: "gmail",
    host: "smtp.gmail.com",
    auth: {
        user: "ryan.rush@gmail.com",
        pass: "L!NNEAFA!th"
    }
});
*/

// Version 2 from http://stackoverflow.com/questions/24973965/sending-emails-using-nodemailer
/*
var smtpTransport = nodemailer.createTransport(smtpTransport({
  service: 'Gmail',
  auth: { user: 'abc@gmail.com',
        pass: 'pass' }
  }));
  */


// Declare application to be an express application
var app = express();

// Ensure index.html is served when users hit the domain
app.get('/',function(req,res){
    res.sendfile('views/index.html');
});

// Handles route request for /send, which is triggered by the email form
app.get('/send',function(req,res){
	var mailOptions={
	   to : req.query.to,
	   name : req.query.name,
	   email : req.query.email,
	   phone : req.query.phone,
	   message : req.query.message
	}

	console.log(mailOptions);

	smtpTransport.sendMail(mailOptions, function(error, response){
		if(error){
			console.log(error);
			res.end("error");
		}else{
			console.log("Message sent: " + response.message);
			res.end("sent");
		}
	});

});


// Have server listen at a certain port
app.listen(3000,function(){
    console.log("Express Started on Port 3000");
});



//WHAT DOES THIS DO?
var routes = require('./routes/index');




/*------------------Serve Static Files Started-----------------------------*/

app.use("/public", express.static(__dirname + '/public'));

// view engine setup if using ejs or Jade
// app.set('views', path.join(__dirname, 'views'));
// app.set('view engine', 'ejs');


/*------------------Serve Static Files Over-----------------------------*/







