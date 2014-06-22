beetl-crossmvc
==============


Beetl-Cross 尝试解决MVC中 m与VC的分离，这样，能让富余的js前端人员毫无困难的参与后端模板的开发，也可以让全栈开发人员先集中精力开发V层技术
Beetl-Cross  利用Beetl模板引擎中类似js语法，且能独立支持V测试来构建一个标准的规范和流程，使得后端模板能较为独立的被多个前端开发人员协作开发和测试，并无缝的集成到后端MVC框架里

~~~~~xml

如：前端人员开发一个登录界面，需要添加一个简单的controller配置

配置文件，前端人员必须写好对于前端一个Service请求，将返回什么样的后端数据
<domain base="/ctr" />
        <controller requestURL="/login"  responseURL="/index.html" responseValue="/login.value">
	<controller requestURL="/user?name=${1}&"  responseURL="user${1}" responseValue="/cccc_value.html">
		<request name="" value="">
		<response tt="" value="">
		<template name="/commont.value">
        </controller>
</domain>

~~~~~


~~~~~javascript
//login.value

var user ={name:'joeli',age:12,lastLogin:date()};
var logHistor = []
var cookies = {}

~~~~~

这样，前端人员就可以开发index.html,并使用 long.value 定义的变量，如:


~~~~~html

<div > 欢迎 ${user.name},上次登陆时间 ${user.lastLogin,'yy-MM-dd'} </div>


~~~~~

前端开发人员需要 配置 Beetl-Cross 模板跟目录即可，并启动一个webserver，它读取domain.xml 文件以模拟MVC中的C和M，前端人员在浏览器中可以按照配置进行测试和开发模板

如下是通常一个典型的目录结构

[ROOT]
   [resource]
         [images]  
   [pages]
         login.html
         index.html
   [value]
         long.value
   domain.xml