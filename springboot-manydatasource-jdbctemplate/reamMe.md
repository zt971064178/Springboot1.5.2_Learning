在spring 中使用注解，常使用@Autowired， 默认是根据类型Type来自动注入的。但有些特殊情况，对同一个接口，可能会有几种不同的实现类，而默认只会采取其中一种的情况下 @Primary  的作用就出来了。下面是个简单的使用例子。

http://www.yihaomen.com/article/java/581.htm