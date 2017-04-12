******* What I built: *********
I challenged myself to learn several new technologies so I could craft together a new responsive website for Advanced Floor Design (AFD). Years of Google Analytics data told me that more and more users were accessing advancedfloor.net using mobile devices, so I needed to ensure they had a positive experience.

First thing first, I found a fantastic free responsive template from HTML5 UP which had a great look and feel, slick JQuery functionality, and all necessary CSS & media query framework built in. I started with this template and then made several modifications/additions so it would work for AFD.

There is one dynamic component to the site - the Contact Us form. I could have easily re-implemented PHPMailer used on the previous version of the site, but I wanted to learn something new. Also, I wanted to build up a general familiarity with Java/JSP development since it's an enterprise standard and useful knowledge for my career.

I spun up a Java Web application in NetBeans, using GlassFish Server 4.1.1, Java EE 7 Web, and Spring Web MVC (4.0.1). The HTML contact form (view) is setup to send a POST request to the MailDispatcherServlet (controller), which passes the data to the MailSenderBean (model) to package, sends an email via SMTP using an existing email account on the advancedfloor.net domain, then finally the MailDispatcherServlet prints out the confirmation to the user.

I then built and deployed the WAR file for this application to an AWS Elastic Beanstalk GlassFish environment.

AWS: http://sample-env-1.5yx5gbtqyy.us-east-1.elasticbeanstalk.com/index.jsp

A Java EE application for a simple single-page portfolio-type website was too heavy weight, so I decided to redesign using NodeJS and Express. But I'm thankful I took this path through Java-land first because I now have a rough understanding of how a Java EE MVC application is setup, how it's built into a WAR file, and how it can be deployed to a production environment like AWS.


******* Giving Credit Where Credit is Due *******

Responsive Site Template: https://html5up.net/strata
Navigation: https://www.w3schools.com/howto/howto_js_topnav.asp
Image Gallery: http://www.menucool.com/responsive-slider
JavaMail:
- https://www.youtube.com/watch?v=gy2eEZhLihk
- https://www.tutorialspoint.com/javamail_api/javamail_api_authentication.htm
- http://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
- https://www.tutorialspoint.com/java/java_sending_email.htm
