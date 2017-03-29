var express = require('express');
var nodemailer = require('nodemailer');
var app = express();

app.listen(3000,function(){
	console.log("Express Started on Port 3000");
});

app.get('/',function(req,res){
	res.sendfile('index.html');
});