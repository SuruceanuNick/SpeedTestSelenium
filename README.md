# SpeedTestSelenium

An example of Selenium Web Automation.

- Language: *Java*
- Build tool: *Maven*
- Framework: *TestNG*
- Driver: *ChromeDriver 88*
- IDE: *IntelliJ IDEA* 
- OS: *Fedora 33*

## How to run

`cd {repo folder}`

`mvn clean test`

## How to generate report

Install Allure

`sudo npm i -g allure-commandline`

Generate report

`cd {repo folder}`

`allure serve -h localhost allure-results`


## Report screenshots

![TestFailed](screenshots/reportExampleFail.png)

![TestPassed](screenshots/reportExamplePass.png)

###### *NOTE: Don't bother about the IP from the screenshot. Test was run via VPN.* 😁 