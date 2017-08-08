<head>
    <meta charset="UTF-8">
    <title>Tephra Script Validator Debug</title>


    <style type="text/css">
        input, textarea {
            width: 95%;
        }
    </style>
</head>
Hi! 
<br>


<#if data.users2?exists>  
	存在！！
<br>
    <#list data.users2 as users>

			name:${users.name}<span>____</span>
			password:${users.password}<span>____</span>
			id:${users.id}<br>

    </#list>


<#else >
	不存在！！
</#if>
		<h2>
			<a id="cb_post_title_url" href="http://www.cnblogs.com/dashi/p/3597937.html">Java</a>
		</h2>
		<div class="postText"><div id="cnblogs_post_body"><div>1、java.util.Collection 是一个<span><strong>集合接口（集合类的一个顶级<span>接口）</span></strong></span>。它提供了对集合对象进行基本操作的通用接口方法。Collection接口在Java 类库中有很多具体的实现。Collection接口的意义是为各种具体的集合提供了最大化的统一操作方式，其直接继承接口有List与Set。</div>
<div>&nbsp;<span>Collection&nbsp;&nbsp;&nbsp;<br />├List&nbsp;&nbsp;&nbsp;<br />│├LinkedList&nbsp;&nbsp;&nbsp;<br />│├ArrayList&nbsp;&nbsp;&nbsp;<br />│└Vector&nbsp;&nbsp;&nbsp;<br />│　└Stack&nbsp;&nbsp;&nbsp;<br />└Set&nbsp;</span></div>
<div>&nbsp;</div>
<div>2、java.util.Collections 是一个包装类（工具类/帮助<span>类</span>）。它包含有各种有关集合操作的<span><strong>静态多态方法</strong></span>。此类<span><strong>不能实例化</strong></span>，就像一<span><strong>个工具类</strong></span>，用于对集合中元素进行排序、搜索以及线程安全等各种操作，服务于Java的Collection框架。</div>
<p>&nbsp;</p>
<p><strong>代码示例：&nbsp;</strong></p>
