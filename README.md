Wicket-tutorial-examples
========================

This repository contains the example projects used in the [Wicket user guide](http://wicket.apache.org/learn/#guide).

## Building the project

The project is a multi-module Maven project. To compile it run 'mvn compile' from the root directory. To run the examples locally follows these steps:

* run 'mvn install' from the root directory
* go into project StarterExamples and run 'mvn jetty:run' or 'mvn tomcat:run'

To run a single example project you have to install first project BootstrapCommon ('mvn install'). Then go into the folder of the project you want to run, type 'mvn jetty:run' or 'mvn tomcat:run' and then point your browser to http://localhost:8080

## See the examples on line

The examples can be explored on line at https://wicket-guide.herokuapp.com/. 
NOTE: examples are hosted on a free account, so you might need to wait few seconds before server responds.

## Contributing to this guide

If you want to contribute to the guide with corrections or new contents, you can find how to do it [here](http://wicket.apache.org/contribute/userguide.html).

## The author
My name is Andrea Del Bene and I'm a passionate enterprise web developer and an advocate of Apache Wicket. I started programming in Java since version 1.2 and I'm a strong supporter of open source technologies.
If you like this project and want to support me, you can offer me a beer :-) :

<p> <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&amp;hosted_button_id=RGHPTV2QDK8VN" rel="nofollow"><img src="https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif"></a>  </p>
