<%--
  Created by IntelliJ IDEA.
  User: erika
  Date: 2017/12/21
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>汉字输入预测</title>
  <!-- Custom Theme files -->
  <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
  <!-- Custom Theme files -->
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="keywords" content="Flat Search Box Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
  <!--Google Fonts-->
  <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
  <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="js/pre.js"></script>
  <!--Google Fonts-->
</head>
<body >
<!--search start here-->

<div class="search">
  <i> </i>
  <text style="font-size:35px;color:darkolivegreen;font-weight:800">猜猜你想说什么</text>
  <br ><br>
  <div class="s-bar">
      <input id="ip" type="text" oninput="autopredict()"/>
      <ul id="predict_list" >
        <script>
            $("ul#predict_list").on("click","li",function(){      //只需要找到你点击的是哪个ul里面的就行

                var x=document.getElementById("ip");
                var y=x.value;
                var sel=$(this).text()
                var one="预";
                var two="分";
                if(sel[0]==one[0]||sel[0]==two[0]);
                else {
                    if (sel[1] == '0')
                        x.innerText = y + sel.substr(3);
                    else
                        x.innerText = y + sel.substr(2);
                }
            });
        </script>
      </ul>
    </form>
  </div>

</div>
<!--search end here-->

</body>
</html>
