BUG1:
	remove spring.2.5.6 from war to aviod duplicate jars
BUG2:
activation.jar and mail.jar jave to be in systems JAVA library under the path /lib/ext

BUG3:
	java running version must be 1.7 or higher


to build war file: mvn compile War:war
to run we need jetty web server so we use the standalone version


to make it easier we created a bat file!!