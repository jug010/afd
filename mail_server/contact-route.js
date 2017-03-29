



var express = require('express');
var router = express.Router();
var nodemailer = require('nodemailer');

/* GET home page */
router.get('/', function(req, res, next) {
	res.render('contact', {title: 'Contact'});
});

router.post('/send', function(req, res, next){
	var transporter = nodemailer.createTransport({
		service: 'Gmail',
		auth: {
			user: 'username@gmail.com',
			pass: 'something'
		}
	});

	var mailOptions = {
		from: 'Ryan Rush <ryan.rush@gmail.com>',
		to: 'username@gmail.com',
		subject: 'Website Submission',
		html: req.body.name + "<br />" + req.body.email + "<br />" + req.body.phone + "<br /><br />" + req.body.message
	};

	transporter.sendMail(mailOptions, function(error, info){
		if(error){
			console.log(error);
			res.redirect('/');
		}
		else {}
			console.log("Message Sent:" + info.response);
			res.redirect('/');
		}
	});
});

module.exports = router;